package com.tauria.video.videoserverchallenge.model.persistence.repository;

import com.tauria.video.videoserverchallenge.model.persistence.domain.ConferenceRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConferenceRoomRepository extends JpaRepository<ConferenceRoom, Long> {

}
