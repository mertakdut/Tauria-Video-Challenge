package com.tauria.video.videoserverchallenge.model.response;

import com.tauria.video.videoserverchallenge.model.Region;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ListAllRegionsResponse {

  private final List<Region> regionList;
}
