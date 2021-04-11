package com.tauria.video.videoserverchallenge.model.response;

import com.tauria.video.videoserverchallenge.model.dto.UserResponse;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GetAllUsersResponse {
  private final List<UserResponse> userList;
}
