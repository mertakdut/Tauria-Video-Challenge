package com.tauria.video.videoserverchallenge.utility;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtils {

  public String encodePassword(String password) {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password);
  }
}
