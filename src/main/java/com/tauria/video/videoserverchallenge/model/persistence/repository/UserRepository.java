package com.tauria.video.videoserverchallenge.model.persistence.repository;

import com.tauria.video.videoserverchallenge.model.persistence.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
