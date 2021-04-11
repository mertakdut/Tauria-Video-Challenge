package com.tauria.video.videoserverchallenge.configuration;

import com.tauria.video.videoserverchallenge.converter.ConferenceRoomToResponseConverter;
import com.tauria.video.videoserverchallenge.converter.RegionToResponseConverter;
import com.tauria.video.videoserverchallenge.converter.TeamToResponseConverter;
import com.tauria.video.videoserverchallenge.converter.UserToResponseConverter;
import java.util.HashSet;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;

@Configuration
public class ConversionConfiguration {

  @Bean
  public ConversionServiceFactoryBean conversionService(
      UserToResponseConverter userToResponseConverter,
      ConferenceRoomToResponseConverter conferenceRoomToResponseConverter,
      RegionToResponseConverter regionToResponseConverter,
      TeamToResponseConverter teamToResponseConverter) {
    Set<Converter> setOfConverters = new HashSet<>();
    setOfConverters.add(userToResponseConverter);
    setOfConverters.add(conferenceRoomToResponseConverter);
    setOfConverters.add(regionToResponseConverter);
    setOfConverters.add(teamToResponseConverter);

    ConversionServiceFactoryBean factory = new ConversionServiceFactoryBean();
    factory.setConverters(setOfConverters);
    return factory;
  }
}
