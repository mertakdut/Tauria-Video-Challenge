package com.tauria.video.videoserverchallenge.utility;

import com.tauria.video.videoserverchallenge.model.Endpoint;
import com.tauria.video.videoserverchallenge.service.GatewayQueryService;
import java.net.URI;
import java.net.URISyntaxException;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.methods.RequestBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@RequiredArgsConstructor
public class GatewayUtils {

  private final GatewayQueryService gatewayQueryService;

  public RequestBuilder transform(HttpServletRequest request) throws URISyntaxException {
    String requestURI = request.getRequestURI();
    URI uri;
    if (request.getQueryString() != null && !request.getQueryString().isEmpty()) {
      uri = new URI(getBaseUrl(request) + "?" + request.getQueryString());
    } else {
      uri = new URI(getBaseUrl(request));
    }

    RequestBuilder rb = RequestBuilder.create(request.getMethod());
    rb.setUri(uri);
    return rb;
  }

  private String getBaseUrl(HttpServletRequest request) {
    Endpoint endpoint = gatewayQueryService.listAllEndpoints().stream().filter(
        e -> request.getRequestURI().matches(e.getPath())
            && e.getMethod() == RequestMethod
            .valueOf(request.getMethod()))
        .findFirst().orElseThrow(() -> new RuntimeException(
            // // TODO: More reasonable exception type like: NoSuchRequestHandlingMethodException.
            "No path found in endpoint list, request: " + request.toString()));
    return gatewayQueryService.getCurrentRegion().getUrl() + request.getRequestURI();
  }
}
