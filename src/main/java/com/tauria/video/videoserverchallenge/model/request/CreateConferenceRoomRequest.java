package com.tauria.video.videoserverchallenge.model.request;

import java.util.List;
import lombok.Getter;

@Getter
public class CreateConferenceRoomRequest {
  private Long creatorId;
  private List<Long> requiredAttendeesIdList;
  private List<Long> optionalAttendeesIdList;
  private boolean allowGuests;
}
