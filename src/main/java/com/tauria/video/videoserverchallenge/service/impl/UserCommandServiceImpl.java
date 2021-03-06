package com.tauria.video.videoserverchallenge.service.impl;

import com.tauria.video.videoserverchallenge.model.persistence.domain.Team;
import com.tauria.video.videoserverchallenge.model.persistence.domain.User;
import com.tauria.video.videoserverchallenge.model.persistence.repository.TeamRepository;
import com.tauria.video.videoserverchallenge.model.persistence.repository.UserRepository;
import com.tauria.video.videoserverchallenge.service.TeamQueryService;
import com.tauria.video.videoserverchallenge.service.UserCommandService;
import com.tauria.video.videoserverchallenge.service.UserQueryService;
import com.tauria.video.videoserverchallenge.utility.PasswordUtils;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

  private final UserQueryService userQueryService;
  private final TeamQueryService teamQueryService;
  private final UserRepository userRepository;
  private final PasswordUtils passwordUtils;

  @Override
  public User save(String firstName, String lastName, String username, String password) {
    String encodedPassword = passwordUtils.encodePassword(password);
    return userRepository.save(new User(firstName, lastName, username, encodedPassword));
  }

  @Override
  public User joinTeam(Long id, Long teamId) {
    User user = userQueryService.getById(id);
    Team team = teamQueryService.getById(teamId);

    Set<Team> enrolledTeams = user.getTeamsEnrolled();
    enrolledTeams.add(team);

    user.setTeamsEnrolled(enrolledTeams);

    return userRepository.save(user);
  }
}
