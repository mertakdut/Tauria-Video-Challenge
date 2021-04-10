package com.tauria.video.videoserverchallenge.model.response;

import com.tauria.video.videoserverchallenge.model.persistence.domain.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

// TODO: Mapper is required e.g. ModelMapper, to avoid sending sensitive information as response.

@Getter
@RequiredArgsConstructor
public class RegisterUserResponse {

  private final User user;
}
