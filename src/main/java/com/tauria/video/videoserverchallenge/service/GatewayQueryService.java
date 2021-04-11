package com.tauria.video.videoserverchallenge.service;

import com.tauria.video.videoserverchallenge.model.Region;
import java.util.List;

public interface GatewayQueryService {

  List<Region> listAllRegions();

  Region getCurrentRegion();

  Region setCurrentRegion(String regionName);
}
