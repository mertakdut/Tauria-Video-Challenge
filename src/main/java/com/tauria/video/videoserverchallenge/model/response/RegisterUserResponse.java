package com.tauria.video.videoserverchallenge.model.response;

import com.tauria.video.videoserverchallenge.model.dto.UserResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterUserResponse {
  private final UserResponse user;
}
