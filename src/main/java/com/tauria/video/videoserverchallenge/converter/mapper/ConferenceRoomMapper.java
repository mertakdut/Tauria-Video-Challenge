package com.tauria.video.videoserverchallenge.converter.mapper;

import com.tauria.video.videoserverchallenge.model.dto.ConferenceRoomResponse;
import com.tauria.video.videoserverchallenge.model.persistence.domain.ConferenceRoom;
import org.mapstruct.Mapper;

@Mapper
public interface ConferenceRoomMapper {

  ConferenceRoomResponse mapToResponse(ConferenceRoom conferenceRoom);
}
