package com.tauria.video.videoserverchallenge.api.controller;

import com.tauria.video.videoserverchallenge.api.constant.ApiConstants;
import com.tauria.video.videoserverchallenge.model.request.SetCurrentRegionRequest;
import com.tauria.video.videoserverchallenge.model.response.GetCurrentRegionResponse;
import com.tauria.video.videoserverchallenge.model.response.ListAllRegionsResponse;
import com.tauria.video.videoserverchallenge.model.response.SetCurrentRegionResponse;
import com.tauria.video.videoserverchallenge.service.GatewayQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = ApiConstants.GATEWAY_BASEURL,
    produces = {ApiConstants.RESPONSE_CONTENTTYPE},
    consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.ALL_VALUE})
@RequiredArgsConstructor
public class GatewayController {

  private final GatewayQueryService gatewayQueryService;

  @GetMapping("/region")
  @ResponseStatus(HttpStatus.OK)
  public GetCurrentRegionResponse getCurrentRegion() {
    return new GetCurrentRegionResponse(gatewayQueryService.getCurrentRegion());
  }

  @PutMapping("/region")
  @ResponseStatus(HttpStatus.CREATED)
  public SetCurrentRegionResponse setCurrentRegion(
      @RequestBody SetCurrentRegionRequest setCurrentRegionRequest) {
    return new SetCurrentRegionResponse(
        gatewayQueryService.setCurrentRegion(setCurrentRegionRequest.getRegionName()));
  }

  @GetMapping("/regions")
  @ResponseStatus(HttpStatus.OK)
  public ListAllRegionsResponse listAllRegions() {
    return new ListAllRegionsResponse(gatewayQueryService.listAllRegions());
  }
}
