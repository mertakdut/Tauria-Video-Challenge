package com.tauria.video.videoserverchallenge.service;

import com.tauria.video.videoserverchallenge.model.persistence.domain.Team;
import com.tauria.video.videoserverchallenge.model.persistence.domain.User;
import java.util.List;

public interface TeamQueryService {

  List<Team> getAll();

  Team getById(String id);
}
