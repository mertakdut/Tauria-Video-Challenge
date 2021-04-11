package com.tauria.video.videoserverchallenge.converter;

import com.tauria.video.videoserverchallenge.converter.mapper.ConferenceRoomMapper;
import com.tauria.video.videoserverchallenge.model.dto.ConferenceRoomResponse;
import com.tauria.video.videoserverchallenge.model.persistence.domain.ConferenceRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConferenceRoomToResponseConverter implements
    Converter<ConferenceRoom, ConferenceRoomResponse> {

  private final ConferenceRoomMapper conferenceRoomMapper;

  @Override
  public ConferenceRoomResponse convert(ConferenceRoom source) {
    return conferenceRoomMapper.mapToResponse(source);
  }
}
