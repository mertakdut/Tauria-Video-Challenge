package com.tauria.video.videoserverchallenge.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMethod;

@Getter
@Setter
public class Endpoint {
  private String path;
  private RequestMethod method;
}
