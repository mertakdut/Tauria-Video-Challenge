package com.tauria.video.videoserverchallenge.service;

import com.tauria.video.videoserverchallenge.model.persistence.domain.Team;
import java.util.List;

public interface TeamQueryService {

  List<Team> getAll();

  Team getById(Long id);
}
