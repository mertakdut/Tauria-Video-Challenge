package com.tauria.video.videoserverchallenge.service;

import com.tauria.video.videoserverchallenge.model.persistence.domain.ConferenceRoom;
import java.util.List;

public interface ConferenceRoomCommandService {

  ConferenceRoom create(Long userId, List<Long> requiredAttendeesIdList,
      List<Long> optionalAttendeesIdList, boolean allowGuests);

  boolean join(Long id, Long userId);
}
