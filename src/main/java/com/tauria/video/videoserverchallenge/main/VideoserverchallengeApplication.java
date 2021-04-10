package com.tauria.video.videoserverchallenge.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.tauria.video.videoserverchallenge"})
@EntityScan("com.tauria.video.videoserverchallenge.model.persistence.domain")
@EnableJpaRepositories("com.tauria.video.videoserverchallenge.model.persistence.repository")
public class VideoserverchallengeApplication {

  public static void main(String[] args) {
    SpringApplication.run(VideoserverchallengeApplication.class, args);
  }
}
