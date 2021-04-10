package com.tauria.video.videoserverchallenge.model.request;

import lombok.Getter;

@Getter
public class RegisterUserRequest {

  private String firstname;
  private String lastname;
  private String username;
  private String password;
}
