package com.tauria.video.videoserverchallenge.model.persistence.domain;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ConferenceRoom {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(cascade = CascadeType.ALL)
  private User createdBy;

  @OneToOne(mappedBy = "asOwner", cascade = CascadeType.ALL)
  private Team owner;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
      name = "user_conference_req_attendee",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "conference_room_id", referencedColumnName = "id"))
  private Set<User> requiredAttendees;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
      name = "user_conference_opt_attendee",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "conference_room_id", referencedColumnName = "id"))
  private Set<User> optionalAttendees;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
      name = "user_conference_insider",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "conference_room_id", referencedColumnName = "id"))
  private Set<User> usersInside;

  private boolean allowGuests;

  public ConferenceRoom() {
  }

  public ConferenceRoom(User createdBy, Team owner, Set<User> requiredAttendees,
      Set<User> optionalAttendees, boolean allowGuests) {
    this.createdBy = createdBy;
    this.owner = owner;
    this.requiredAttendees = requiredAttendees;
    this.optionalAttendees = optionalAttendees;
    this.allowGuests = allowGuests;
  }
}
