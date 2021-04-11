package com.tauria.video.videoserverchallenge.converter;

import com.tauria.video.videoserverchallenge.converter.mapper.TeamMapper;
import com.tauria.video.videoserverchallenge.model.dto.TeamResponse;
import com.tauria.video.videoserverchallenge.model.persistence.domain.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeamToResponseConverter implements Converter<Team, TeamResponse> {

  private final TeamMapper teamMapper;

  @Override
  public TeamResponse convert(Team source) {
    return teamMapper.mapToResponse(source);
  }
}
