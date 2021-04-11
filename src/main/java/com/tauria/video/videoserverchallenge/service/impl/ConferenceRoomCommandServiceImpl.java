package com.tauria.video.videoserverchallenge.service.impl;

import com.tauria.video.videoserverchallenge.model.persistence.domain.ConferenceRoom;
import com.tauria.video.videoserverchallenge.model.persistence.domain.Team;
import com.tauria.video.videoserverchallenge.model.persistence.domain.User;
import com.tauria.video.videoserverchallenge.model.persistence.repository.ConferenceRoomRepository;
import com.tauria.video.videoserverchallenge.service.ConferenceRoomCommandService;
import com.tauria.video.videoserverchallenge.service.ConferenceRoomQueryService;
import com.tauria.video.videoserverchallenge.service.UserQueryService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConferenceRoomCommandServiceImpl implements ConferenceRoomCommandService {

  private final UserQueryService userQueryService;
  private final ConferenceRoomQueryService conferenceRoomQueryService;
  private final ConferenceRoomRepository conferenceRoomRepository;

  @Override
  public ConferenceRoom create(Long userId, List<Long> requiredAttendeesIdList,
      List<Long> optionalAttendeesIdList, boolean allowGuests) {
    User user = userQueryService.getById(userId);

    if (user == null) {
      // TODO: Custom business logic exception class.
      throw new RuntimeException("Only users can create conference rooms.");
    }

    Team owner = null;

    if (user.getTeamsEnrolled() != null && !user.getTeamsEnrolled().isEmpty()) {
      owner = user.getTeamsEnrolled().iterator().next();
    }

    Set<User> requiredAttendees = new HashSet<>(
        userQueryService.findAllByIds(requiredAttendeesIdList));
    Set<User> optionalAttendees = new HashSet<>(
        userQueryService.findAllByIds(optionalAttendeesIdList));

    return conferenceRoomRepository
        .save(new ConferenceRoom(user, owner, requiredAttendees, optionalAttendees, allowGuests));
  }

  @Override
  public ConferenceRoom join(Long id, Long userId) {
    ConferenceRoom conferenceRoom = conferenceRoomQueryService.getById(id);
    if (!conferenceRoom.isAllowGuests()) {
      User attemptingUser = userQueryService.getById(userId);
      if (attemptingUser == null) {
        // TODO: Custom business logic exception class.
        throw new RuntimeException("Only users can join this conference room.");
      }

      Set<User> insideUsers = conferenceRoom.getUsersInside();
      insideUsers.add(attemptingUser);
      conferenceRoom.setUsersInside(insideUsers);

      conferenceRoom = conferenceRoomRepository.save(conferenceRoom);
    }

    return conferenceRoom;
  }

  @Override
  public ConferenceRoom leave(Long id, Long userId) {
    ConferenceRoom conferenceRoom = conferenceRoomQueryService.getById(id);
    User leaver = conferenceRoom.getUsersInside().stream().filter(user -> user.getId().equals(id))
        .findFirst().orElse(null);
    conferenceRoom.getUsersInside().remove(leaver);

    return conferenceRoomRepository.save(conferenceRoom);
  }

  @Override
  public boolean checkTotalConferenceTimeExceeded(Long id, int seconds) {
    ConferenceRoom conferenceRoom = conferenceRoomQueryService.getById(id);
    return seconds > conferenceRoom.getTotalConferenceTimeInSeconds();
  }
}
