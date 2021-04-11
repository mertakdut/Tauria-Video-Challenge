package com.tauria.video.videoserverchallenge.service;

import com.tauria.video.videoserverchallenge.model.persistence.domain.ConferenceRoom;
import java.util.List;

public interface ConferenceRoomCommandService {

  ConferenceRoom create(Long userId, List<Long> requiredAttendeesIdList,
      List<Long> optionalAttendeesIdList, boolean allowGuests);

  ConferenceRoom join(Long id, Long userId);

  ConferenceRoom leave(Long id, Long userId);

  boolean checkTotalConferenceTimeExceeded(Long id, int seconds);
}
