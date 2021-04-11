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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    name = "users",
    uniqueConstraints =
    @UniqueConstraint(columnNames = {"username"})
)
@Getter
@Setter
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String firstName;
  @NotNull
  private String lastName;
  @NotNull
  private String username;
  @NotNull
  private String password;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
      name = "user_team",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "team_id", referencedColumnName = "id"))
  private Set<Team> teamsEnrolled;

  @ManyToMany(mappedBy = "requiredAttendees", cascade = CascadeType.ALL)
  private Set<ConferenceRoom> asRequiredAttendee;

  @ManyToMany(mappedBy = "optionalAttendees", cascade = CascadeType.ALL)
  private Set<ConferenceRoom> asOptionalAttendee;

  @ManyToMany(mappedBy = "usersInside", cascade = CascadeType.ALL)
  private Set<ConferenceRoom> asInsideUser;

  @OneToMany(mappedBy = "createdBy")
  private Set<ConferenceRoom> asCreator;

  public User() {
  }

  public User(String firstName, String lastName, String username, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.password = password;
  }

}
