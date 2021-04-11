package com.tauria.video.videoserverchallenge.api.controller;

import com.tauria.video.videoserverchallenge.api.constant.ApiConstants;
import com.tauria.video.videoserverchallenge.model.dto.ConferenceRoomResponse;
import com.tauria.video.videoserverchallenge.model.request.CreateConferenceRoomRequest;
import com.tauria.video.videoserverchallenge.model.request.JoinConferenceRoomRequest;
import com.tauria.video.videoserverchallenge.model.request.LeaveConferenceRoomRequest;
import com.tauria.video.videoserverchallenge.model.response.CreateConferenceRoomResponse;
import com.tauria.video.videoserverchallenge.model.response.GetAllConferenceRoomsResponse;
import com.tauria.video.videoserverchallenge.model.response.JoinConferenceRoomResponse;
import com.tauria.video.videoserverchallenge.model.response.LeaveConferenceRoomResponse;
import com.tauria.video.videoserverchallenge.service.ConferenceRoomCommandService;
import com.tauria.video.videoserverchallenge.service.ConferenceRoomQueryService;
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
@RequestMapping(value = ApiConstants.CONFERENCE_ROOM_BASEURL,
    produces = {ApiConstants.RESPONSE_CONTENTTYPE},
    consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.ALL_VALUE})
@RequiredArgsConstructor
public class ConferenceRoomController {

  private final ConferenceRoomQueryService conferenceRoomQueryService;
  private final ConferenceRoomCommandService conferenceRoomCommandService;

  private final ConversionService conversionService;

  @GetMapping
  @ResponseStatus(HttpStatus.CREATED)
  public GetAllConferenceRoomsResponse getAll() {
    return new GetAllConferenceRoomsResponse(
        conferenceRoomQueryService.getAll().stream()
            .map(conferenceRoom -> conversionService
                .convert(conferenceRoom, ConferenceRoomResponse.class))
            .collect(Collectors.toList())
    );
  }

  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  public CreateConferenceRoomResponse create(@RequestBody CreateConferenceRoomRequest request) {
    return new CreateConferenceRoomResponse(
        conversionService.convert(
            conferenceRoomCommandService
                .create(request.getCreatorId(), request.getRequiredAttendeesIdList(),
                    request.getOptionalAttendeesIdList(), request.isAllowGuests()),
            ConferenceRoomResponse.class
        )
    );
  }

  @PostMapping("/{id}/join")
  @ResponseStatus(HttpStatus.OK)
  public JoinConferenceRoomResponse join(@PathVariable("id") Long id,
      @RequestBody JoinConferenceRoomRequest request) {
    return new JoinConferenceRoomResponse(
        conversionService.convert(
            conferenceRoomCommandService.join(id, request.getUserId()),
            ConferenceRoomResponse.class
        )
    );
  }

  @PostMapping("/{id}/leave")
  @ResponseStatus(HttpStatus.OK)
  public LeaveConferenceRoomResponse leave(@PathVariable("id") Long id,
      @RequestBody LeaveConferenceRoomRequest request) {
    return new LeaveConferenceRoomResponse(
        conversionService.convert(
            conferenceRoomCommandService.leave(id, request.getUserId()),
            ConferenceRoomResponse.class
        )
    );
  }
}
