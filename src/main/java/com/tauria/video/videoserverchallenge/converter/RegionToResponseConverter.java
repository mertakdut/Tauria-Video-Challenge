package com.tauria.video.videoserverchallenge.converter;

import com.tauria.video.videoserverchallenge.converter.mapper.RegionMapper;
import com.tauria.video.videoserverchallenge.model.Region;
import com.tauria.video.videoserverchallenge.model.dto.RegionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegionToResponseConverter implements Converter<Region, RegionResponse> {

  private final RegionMapper regionMapper;

  @Override
  public RegionResponse convert(Region source) {
    return regionMapper.mapToResponse(source);
  }
}
