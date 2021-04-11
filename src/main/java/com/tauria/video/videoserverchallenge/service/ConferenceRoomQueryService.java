package com.tauria.video.videoserverchallenge.service;

import com.tauria.video.videoserverchallenge.model.persistence.domain.ConferenceRoom;
import java.util.List;

public interface ConferenceRoomQueryService {

  List<ConferenceRoom> getAll();

  ConferenceRoom getById(Long id);
}
