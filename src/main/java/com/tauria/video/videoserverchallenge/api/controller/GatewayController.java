package com.tauria.video.videoserverchallenge.api.controller;

import com.tauria.video.videoserverchallenge.api.constant.ApiConstants;
import com.tauria.video.videoserverchallenge.model.dto.RegionResponse;
import com.tauria.video.videoserverchallenge.model.request.SetCurrentRegionRequest;
import com.tauria.video.videoserverchallenge.model.response.GetCurrentRegionResponse;
import com.tauria.video.videoserverchallenge.model.response.ListAllRegionsResponse;
import com.tauria.video.videoserverchallenge.model.response.SetCurrentRegionResponse;
import com.tauria.video.videoserverchallenge.service.GatewayQueryService;
import com.tauria.video.videoserverchallenge.utility.GatewayUtils;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
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

  private static final Logger logger = LoggerFactory.getLogger(GatewayController.class);

  private final GatewayQueryService gatewayQueryService;
  private final GatewayUtils gatewayUtils;
  private final ConversionService conversionService;

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
    return new GetCurrentRegionResponse(
        conversionService.convert(gatewayQueryService.getCurrentRegion(), RegionResponse.class)
    );
  }

  @PutMapping("/region")
  @ResponseStatus(HttpStatus.CREATED)
  public SetCurrentRegionResponse setCurrentRegion(
      @RequestBody SetCurrentRegionRequest request) {
    return new SetCurrentRegionResponse(
        conversionService.convert(
            gatewayQueryService.setCurrentRegion(request.getRegionName()), RegionResponse.class
        )
    );
  }

  @GetMapping("/regions")
  @ResponseStatus(HttpStatus.OK)
  public ListAllRegionsResponse listAllRegions() {
    return new ListAllRegionsResponse(
        gatewayQueryService.listAllRegions().stream()
            .map(region -> conversionService.convert(region, RegionResponse.class))
            .collect(Collectors.toList())
    );
  }

  @RequestMapping(value = "/**", method = {
      RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE
  })
  @ResponseBody
  public ResponseEntity<String> proxyRequest(HttpServletRequest request)
      throws URISyntaxException, IOException {
    HttpUriRequest proxiedRequest = gatewayUtils.transformRequest(request);
    logger.info("request: {}", proxiedRequest);
    HttpResponse proxiedResponse = httpClient.execute(proxiedRequest);
    logger.info("Response {}", proxiedResponse);
    return gatewayUtils.transformResponse(proxiedResponse);
  }


}
