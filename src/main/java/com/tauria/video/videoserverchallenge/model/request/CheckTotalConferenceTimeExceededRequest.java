package com.tauria.video.videoserverchallenge.model.request;

import lombok.Getter;

@Getter
public class CheckTotalConferenceTimeExceededRequest {
  private Long conferenceRoomId;
  private int totalConferenceTime;
}
