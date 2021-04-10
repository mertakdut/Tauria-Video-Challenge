package com.tauria.video.videoserverchallenge.model.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

  @Id
  @GeneratedValue
  private String id;

  private String firstname;
  private String lastname;

}
