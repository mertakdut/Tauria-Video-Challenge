package com.tauria.video.videoserverchallenge.model.persistence.domain;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Team {

  @Id
  @GeneratedValue
  private String id;

  @NotNull
  private String name;

  @ManyToMany(mappedBy = "teamsEnrolled")
  private Set<User> enrolledUsers;
}
