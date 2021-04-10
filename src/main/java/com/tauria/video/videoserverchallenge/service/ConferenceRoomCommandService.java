package com.tauria.video.videoserverchallenge.service;

import com.tauria.video.videoserverchallenge.model.persistence.domain.ConferenceRoom;
import java.util.List;

public interface ConferenceRoomCommandService {

  ConferenceRoom create(String userId, List<String> requiredAttendeesIdList,
      List<String> optionalAttendeesIdList, boolean allowGuests);

  boolean join(String id, String userId);
}
