package com.tauria.video.videoserverchallenge.model.response;

import com.tauria.video.videoserverchallenge.model.dto.RegionResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SetCurrentRegionResponse {
  private final RegionResponse currentRegion;
}
