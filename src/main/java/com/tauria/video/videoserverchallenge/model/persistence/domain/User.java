package com.tauria.video.videoserverchallenge.model.persistence.domain;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
  @GeneratedValue
  private Long id;

  @NotNull
  private String firstName;
  @NotNull
  private String lastName;

  @NotNull
  private String username;
  @NotNull
  private String password;

  @ManyToMany
  @JoinTable(
      name = "user_team",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "team_id"))
  private Set<Team> teamsEnrolled;

  public User() {
  }

  public User(String firstName, String lastName, String username, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.password = password;
  }

}
