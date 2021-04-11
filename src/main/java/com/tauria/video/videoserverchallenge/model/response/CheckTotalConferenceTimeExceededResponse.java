package com.tauria.video.videoserverchallenge.model.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CheckTotalConferenceTimeExceededResponse {
  private final boolean exceeded;
}
