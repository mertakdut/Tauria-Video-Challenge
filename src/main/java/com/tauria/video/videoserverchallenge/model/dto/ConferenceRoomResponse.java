package com.tauria.video.videoserverchallenge.model.dto;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConferenceRoomResponse {
  private UserResponse createdBy;
  private TeamResponse owner;
  private Set<UserResponse> requiredAttendees;
  private Set<UserResponse> optionalAttendees;
  private Set<UserResponse> usersInside;
  private boolean allowGuests;
}
