package com.tauria.video.videoserverchallenge.converter;

import com.tauria.video.videoserverchallenge.converter.mapper.UserMapper;
import com.tauria.video.videoserverchallenge.model.dto.UserResponse;
import com.tauria.video.videoserverchallenge.model.persistence.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserToResponseConverter implements Converter<User, UserResponse> {

  private final UserMapper userMapper;

  @Override
  public UserResponse convert(User source) {
    return userMapper.mapToResponse(source);
  }
}
