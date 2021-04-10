package com.tauria.video.videoserverchallenge.api.controller;

import com.tauria.video.videoserverchallenge.api.constant.ApiConstants;
import com.tauria.video.videoserverchallenge.model.request.CreateConferenceRoomRequest;
import com.tauria.video.videoserverchallenge.model.request.JoinConferenceRoomRequest;
import com.tauria.video.videoserverchallenge.model.response.CreateConferenceRoomResponse;
import com.tauria.video.videoserverchallenge.model.response.JoinConferenceRoomResponse;
import com.tauria.video.videoserverchallenge.service.ConferenceRoomCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = ApiConstants.CONFERENCE_ROOM_BASEURL,
    produces = {ApiConstants.RESPONSE_CONTENTTYPE},
    consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.ALL_VALUE})
@RequiredArgsConstructor
public class ConferenceRoomController {

  private final ConferenceRoomCommandService conferenceRoomCommandService;

  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  public CreateConferenceRoomResponse create(@RequestBody CreateConferenceRoomRequest request) {
    return new CreateConferenceRoomResponse(
        conferenceRoomCommandService
            .create(request.getUserId(), request.getRequiredAttendeesIdList(),
                request.getOptionalAttendeesIdList(), request.isAllowGuests()));
  }

  @PostMapping("/{id}/join")
  @ResponseStatus(HttpStatus.OK)
  public JoinConferenceRoomResponse join(@PathVariable("id") String id,
      @RequestBody JoinConferenceRoomRequest request) {
    return new JoinConferenceRoomResponse(
        conferenceRoomCommandService.join(id, request.getUserId()));
  }
}
