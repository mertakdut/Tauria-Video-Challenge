package com.tauria.video.videoserverchallenge.service.impl;

import com.tauria.video.videoserverchallenge.model.persistence.domain.ConferenceRoom;
import com.tauria.video.videoserverchallenge.model.persistence.repository.ConferenceRoomRepository;
import com.tauria.video.videoserverchallenge.service.ConferenceRoomQueryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConferenceRoomQueryServiceImpl implements ConferenceRoomQueryService {

  private final ConferenceRoomRepository conferenceRoomRepository;

  @Override
  public List<ConferenceRoom> getAll() {
    return conferenceRoomRepository.findAll();
  }

  @Override
  public ConferenceRoom getById(String id) {
    return conferenceRoomRepository.findById(id).orElse(null);
  }
}
