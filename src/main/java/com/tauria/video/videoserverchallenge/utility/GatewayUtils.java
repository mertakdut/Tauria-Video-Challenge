package com.tauria.video.videoserverchallenge.utility;

import com.tauria.video.videoserverchallenge.service.GatewayQueryService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GatewayUtils {

  private final GatewayQueryService gatewayQueryService;

  public HttpUriRequest transformRequest(HttpServletRequest request)
      throws URISyntaxException, IOException {
    RequestBuilder requestBuilder = this.setUrl(request);
    this.setContent(request, requestBuilder);
    this.setHeaders(request, requestBuilder);

    return requestBuilder.build();
  }

  public ResponseEntity<String> transformResponse(HttpResponse response) throws IOException {
    return new ResponseEntity<>(read(response.getEntity().getContent()),
        makeResponseHeaders(response),
        HttpStatus.valueOf(response.getStatusLine().getStatusCode()));
  }

  private void setHeaders(HttpServletRequest request, RequestBuilder requestBuilder) {
    Enumeration<String> headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      String headerName = headerNames.nextElement();
      String headerValue = request.getHeader(headerName);
      if (headerName.equals("x-access-token")) {
        requestBuilder.addHeader(headerName, headerValue);
      }
    }
  }

  private void setContent(HttpServletRequest httpServletRequest,
      RequestBuilder requestBuilder) throws IOException {
    String requestContent = httpServletRequest.getReader().lines().collect(Collectors.joining(""));
    if (!requestContent.isEmpty()) {
      StringEntity entity = new StringEntity(requestContent, ContentType.APPLICATION_JSON);
      requestBuilder.setEntity(entity);
    }
  }

  private RequestBuilder setUrl(HttpServletRequest request) throws URISyntaxException {
    URI uri;
    if (request.getQueryString() != null && !request.getQueryString().isEmpty()) {
      uri = new URI(getServiceUrl(request) + "?" + request.getQueryString());
    } else {
      uri = new URI(getServiceUrl(request));
    }

    RequestBuilder requestBuilder = RequestBuilder.create(request.getMethod());
    requestBuilder.setUri(uri);

    return requestBuilder;
  }

  private String getServiceUrl(HttpServletRequest request) {
    return gatewayQueryService.getCurrentRegion().getUrl() + request.getRequestURI();
  }

  private HttpHeaders makeResponseHeaders(HttpResponse response) {
    HttpHeaders result = new HttpHeaders();
    Header h = response.getFirstHeader("Content-Type");
    result.set(h.getName(), h.getValue());
    return result;
  }

  private String read(InputStream input) throws IOException {
    try (BufferedReader buffer = new BufferedReader(new InputStreamReader(input))) {
      return buffer.lines().collect(Collectors.joining("\n"));
    }
  }
}
