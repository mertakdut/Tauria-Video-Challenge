package com.tauria.video.videoserverchallenge.service.impl;

import com.tauria.video.videoserverchallenge.model.persistence.domain.Team;
import com.tauria.video.videoserverchallenge.model.persistence.repository.TeamRepository;
import com.tauria.video.videoserverchallenge.service.TeamQueryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamQueryServiceImpl implements TeamQueryService {

  private final TeamRepository teamRepository;

  @Override
  public List<Team> getAll() {
    return teamRepository.findAll();
  }

  @Override
  public Team getById(String id) {
    return teamRepository.findById(id).orElse(
        null); // TODO: Custom exception with RestErrorHandler: EntityNotFound or TeamNotFound
  }
}
