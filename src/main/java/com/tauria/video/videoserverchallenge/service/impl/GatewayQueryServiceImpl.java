package com.tauria.video.videoserverchallenge.service.impl;

import com.tauria.video.videoserverchallenge.model.Region;
import com.tauria.video.videoserverchallenge.properties.GatewayProperties;
import com.tauria.video.videoserverchallenge.service.GatewayQueryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GatewayQueryServiceImpl implements GatewayQueryService {

  private final GatewayProperties gatewayProperties;
  private Region currentRegion;

  @Override
  public List<Region> listAllRegions() {
    return this.getAllRegionsFromProperties();
  }

  public Region getCurrentRegion() {
    if (currentRegion != null) {
      return currentRegion;
    } else {
      return this.getAllRegionsFromProperties().get(0);
    }
  }

  public Region setCurrentRegion(String regionName) {
    currentRegion = this.getAllRegionsFromProperties().stream()
        .filter(region -> region.getName().equals(regionName))
        .findFirst().orElse(null);
    return currentRegion;
  }

  private List<Region> getAllRegionsFromProperties() {
    return gatewayProperties.getRegions();
  }
}
