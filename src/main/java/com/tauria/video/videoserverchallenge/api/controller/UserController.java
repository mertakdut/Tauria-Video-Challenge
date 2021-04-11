package com.tauria.video.videoserverchallenge.api.controller;

import com.tauria.video.videoserverchallenge.api.constant.ApiConstants;
import com.tauria.video.videoserverchallenge.model.dto.UserResponse;
import com.tauria.video.videoserverchallenge.model.request.JoinTeamRequest;
import com.tauria.video.videoserverchallenge.model.request.RegisterUserRequest;
import com.tauria.video.videoserverchallenge.model.response.GetAllUsersResponse;
import com.tauria.video.videoserverchallenge.model.response.GetUserByIdResponse;
import com.tauria.video.videoserverchallenge.model.response.JoinTeamResponse;
import com.tauria.video.videoserverchallenge.model.response.RegisterUserResponse;
import com.tauria.video.videoserverchallenge.service.UserCommandService;
import com.tauria.video.videoserverchallenge.service.UserQueryService;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = ApiConstants.USER_BASEURL,
    produces = {ApiConstants.RESPONSE_CONTENTTYPE},
    consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.ALL_VALUE})
@RequiredArgsConstructor
public class UserController {

  private final UserQueryService userQueryService;
  private final UserCommandService userCommandService;
  private final ConversionService conversionService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public GetAllUsersResponse getAll() {
    return new GetAllUsersResponse(userQueryService.getAll().stream()
        .map(user -> conversionService.convert(user, UserResponse.class))
        .collect(Collectors.toList()));
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public GetUserByIdResponse getById(@PathVariable("id") Long id) {
    return new GetUserByIdResponse(
        conversionService.convert(userQueryService.getById(id), UserResponse.class)
    );
  }

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public RegisterUserResponse register(@RequestBody RegisterUserRequest request) {
    return new RegisterUserResponse(
        conversionService.convert(
            userCommandService
                .save(request.getFirstName(), request.getLastName(), request.getUsername(),
                    request.getPassword()), UserResponse.class)
    );
  }

  @PostMapping("/{id}/join-team")
  @ResponseStatus(HttpStatus.OK)
  public JoinTeamResponse joinTeam(@PathVariable("id") Long id,
      @RequestBody JoinTeamRequest joinTeamRequest) {
    return new JoinTeamResponse(conversionService
        .convert(userCommandService.joinTeam(id, joinTeamRequest.getTeamId()), UserResponse.class));
  }
}
