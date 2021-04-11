package com.tauria.video.videoserverchallenge.model.response;

import com.tauria.video.videoserverchallenge.model.dto.ConferenceRoomResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LeaveConferenceRoomResponse {
  private final ConferenceRoomResponse conferenceRoom;
}
