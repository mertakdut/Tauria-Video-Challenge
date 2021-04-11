package com.tauria.video.videoserverchallenge.model.persistence.domain;

import javax.validation.constraints.NotNull;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Guest {

  @Id
  @GeneratedValue
  private Long id;

  @NotNull
  private String firstname;
  @NotNull
  private String lastname;
}
