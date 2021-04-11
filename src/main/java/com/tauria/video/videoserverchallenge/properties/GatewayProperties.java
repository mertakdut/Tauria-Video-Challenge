package com.tauria.video.videoserverchallenge.properties;

import com.tauria.video.videoserverchallenge.model.Region;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ConfigurationProperties(prefix = "gateway")
@Component
public class GatewayProperties {

  private List<Region> regions;
}
