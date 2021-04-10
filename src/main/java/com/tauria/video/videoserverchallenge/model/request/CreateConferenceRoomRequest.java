package com.tauria.video.videoserverchallenge.model.request;

import java.util.List;
import lombok.Getter;

@Getter
public class CreateConferenceRoomRequest {

  private String userId;
  private List<String> requiredAttendeesIdList;
  private List<String> optionalAttendeesIdList;
  private boolean allowGuests;
}
