package com.tauria.video.videoserverchallenge.service;

import com.tauria.video.videoserverchallenge.model.persistence.domain.User;

public interface UserCommandService {

  User save(String firstName, String lastName, String username, String password);

  User joinTeam(String id, String teamId);
}
