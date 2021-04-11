package com.tauria.video.videoserverchallenge.model.response;

import com.tauria.video.videoserverchallenge.model.dto.ConferenceRoomResponse;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GetAllConferenceRoomsResponse {
  private final List<ConferenceRoomResponse> conferenceRoomList;
}
