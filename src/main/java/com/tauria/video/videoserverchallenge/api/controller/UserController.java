package com.tauria.video.videoserverchallenge.api.controller;

import com.tauria.video.videoserverchallenge.api.constant.ApiConstants;
import com.tauria.video.videoserverchallenge.model.request.JoinTeamRequest;
import com.tauria.video.videoserverchallenge.model.request.RegisterUserRequest;
import com.tauria.video.videoserverchallenge.model.response.GetAllUsersResponse;
import com.tauria.video.videoserverchallenge.model.response.GetUserByIdResponse;
import com.tauria.video.videoserverchallenge.model.response.JoinTeamResponse;
import com.tauria.video.videoserverchallenge.model.response.RegisterUserResponse;
import com.tauria.video.videoserverchallenge.service.UserCommandService;
import com.tauria.video.videoserverchallenge.service.UserQueryService;
import lombok.RequiredArgsConstructor;
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

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public GetAllUsersResponse getAll() {
    return new GetAllUsersResponse(userQueryService.getAll());
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public GetUserByIdResponse getById(@PathVariable("id") String id) {
    return new GetUserByIdResponse(userQueryService.getById(id));
  }

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public RegisterUserResponse register(@RequestBody RegisterUserRequest request) {
    return new RegisterUserResponse(userCommandService
        .save(request.getFirstname(), request.getLastname(), request.getUsername(),
            request.getPassword()));
  }

  @PostMapping("/{id}/join-team")
  @ResponseStatus(HttpStatus.OK)
  public JoinTeamResponse joinTeam(@PathVariable("id") String id,
      @RequestBody JoinTeamRequest joinTeamRequest) {
    return new JoinTeamResponse(userCommandService.joinTeam(id, joinTeamRequest.getTeamId()));
  }
}
