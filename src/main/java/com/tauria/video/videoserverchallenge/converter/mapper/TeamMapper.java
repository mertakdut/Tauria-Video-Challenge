package com.tauria.video.videoserverchallenge.converter.mapper;

import com.tauria.video.videoserverchallenge.model.dto.TeamResponse;
import com.tauria.video.videoserverchallenge.model.persistence.domain.Team;
import org.mapstruct.Mapper;

@Mapper
public interface TeamMapper {

  TeamResponse mapToResponse(Team user);
}
