package com.tauria.video.videoserverchallenge.api.controller;

import com.tauria.video.videoserverchallenge.api.constant.ApiConstants;
import com.tauria.video.videoserverchallenge.model.request.SetCurrentRegionRequest;
import com.tauria.video.videoserverchallenge.model.response.GetCurrentRegionResponse;
import com.tauria.video.videoserverchallenge.model.response.ListAllRegionsResponse;
import com.tauria.video.videoserverchallenge.model.response.SetCurrentRegionResponse;
import com.tauria.video.videoserverchallenge.service.GatewayQueryService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = ApiConstants.GATEWAY_BASEURL,
    produces = {ApiConstants.RESPONSE_CONTENTTYPE},
    consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.ALL_VALUE})
@RequiredArgsConstructor
public class GatewayController {

  private final GatewayQueryService gatewayQueryService;

  private HttpClient httpClient;

  @PostConstruct
  public void init() {
    PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();

    httpClient = HttpClients.custom()
        .setConnectionManager(cm)
        .build();
  }

  @GetMapping("/region")
  @ResponseStatus(HttpStatus.OK)
  public GetCurrentRegionResponse getCurrentRegion() {
    return new GetCurrentRegionResponse(gatewayQueryService.getCurrentRegion());
  }

  @PutMapping("/region")
  @ResponseStatus(HttpStatus.CREATED)
  public SetCurrentRegionResponse setCurrentRegion(
      @RequestBody SetCurrentRegionRequest request) {
    return new SetCurrentRegionResponse(
        gatewayQueryService.setCurrentRegion(request.getRegionName()));
  }

  @GetMapping("/regions")
  @ResponseStatus(HttpStatus.OK)
  public ListAllRegionsResponse listAllRegions() {
    return new ListAllRegionsResponse(gatewayQueryService.listAllRegions());
  }

  @RequestMapping(value = "/api/**", method = {
      RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE
  })
  @ResponseBody
  public ResponseEntity<String> proxyRequest(HttpServletRequest request) {

  }

  private HttpHeaders makeResponseHeaders(HttpResponse response) {
    HttpHeaders result = new HttpHeaders();
    Header h = response.getFirstHeader("Content-Type");
    result.set(h.getName(), h.getValue());
    return result;
  }

  private HttpUriRequest createHttpUriRequest(HttpServletRequest request) throws URISyntaxException, NoSuchRequestHandlingMethodException, IOException {
    URLRequestTransformer urlRequestTransformer = new URLRequestTransformer(apiGatewayProperties);
    ContentRequestTransformer contentRequestTransformer = new ContentRequestTransformer();
    HeadersRequestTransformer headersRequestTransformer = new HeadersRequestTransformer();
    headersRequestTransformer.setPredecessor(contentRequestTransformer);
    contentRequestTransformer.setPredecessor(urlRequestTransformer);

    return headersRequestTransformer.transform(request).build();
  }

  private String read(InputStream input) throws IOException {
    try (BufferedReader buffer = new BufferedReader(new InputStreamReader(input))) {
      return buffer.lines().collect(Collectors.joining("\n"));
    }
  }
}
