package com.tauria.video.videoserverchallenge.converter.mapper;

import com.tauria.video.videoserverchallenge.model.dto.UserResponse;
import com.tauria.video.videoserverchallenge.model.persistence.domain.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

  UserResponse mapToResponse(User user);
}
