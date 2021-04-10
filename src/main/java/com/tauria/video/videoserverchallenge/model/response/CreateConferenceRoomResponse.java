package com.tauria.video.videoserverchallenge.model.response;

// TODO: Mapper is required e.g. ModelMapper, to avoid sending sensitive information as response.

import com.tauria.video.videoserverchallenge.model.persistence.domain.ConferenceRoom;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateConferenceRoomResponse {

  private final ConferenceRoom conferenceRoom;
}
