package com.tauria.video.videoserverchallenge.model.persistence.repository;

import com.tauria.video.videoserverchallenge.model.persistence.domain.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest, String> {

}
