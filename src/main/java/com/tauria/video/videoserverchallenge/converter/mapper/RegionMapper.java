package com.tauria.video.videoserverchallenge.converter.mapper;

import com.tauria.video.videoserverchallenge.model.Region;
import com.tauria.video.videoserverchallenge.model.dto.RegionResponse;
import org.mapstruct.Mapper;

@Mapper
public interface RegionMapper {

  RegionResponse mapToResponse(Region user);
}
