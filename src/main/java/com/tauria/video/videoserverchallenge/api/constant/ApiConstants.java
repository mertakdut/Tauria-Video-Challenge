package com.tauria.video.videoserverchallenge.api.constant;

import org.springframework.http.MediaType;

public final class ApiConstants {

  public static final String RESPONSE_CONTENTTYPE =
      MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8";

  public static final String GATEWAY_BASEURL = "/api";

  public static final String USER_BASEURL = "/user";

  public static final String CONFERENCE_ROOM_BASEURL = "/conference";

  private ApiConstants() {

  }
}
