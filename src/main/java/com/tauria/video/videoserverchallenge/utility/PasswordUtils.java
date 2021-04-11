package com.tauria.video.videoserverchallenge.utility;

import org.springframework.stereotype.Component;

@Component
public class PasswordUtils {

  public String encodePassword(String password) {
    return "encoded" + password; // TODO: Use PasswordEncoderFactories when spring-security is added.
  }
}
