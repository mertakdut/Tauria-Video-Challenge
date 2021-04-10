package com.tauria.video.videoserverchallenge.model.persistence.domain;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ConferenceRoom {

  @Id
  @GeneratedValue
  private String id;

  @ManyToOne
  private User createdBy;

  @ManyToOne
  private Team owner;

  @OneToMany
  private Set<User> requiredAttendees;

  @OneToMany
  private Set<User> optionalAttendees;

  private boolean allowGuests;

  public ConferenceRoom(User createdBy, Team owner, Set<User> requiredAttendees,
      Set<User> optionalAttendees, boolean allowGuests) {
    this.createdBy = createdBy;
    this.owner = owner;
    this.requiredAttendees = requiredAttendees;
    this.optionalAttendees = optionalAttendees;
    this.allowGuests = allowGuests;
  }
}
