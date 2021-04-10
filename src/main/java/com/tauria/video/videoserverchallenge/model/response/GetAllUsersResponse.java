package com.tauria.video.videoserverchallenge.model.response;

// TODO: Mapper is required e.g. ModelMapper, to avoid sending sensitive information as response.

import com.tauria.video.videoserverchallenge.model.persistence.domain.User;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GetAllUsersResponse {

  private final List<User> userList;
}
