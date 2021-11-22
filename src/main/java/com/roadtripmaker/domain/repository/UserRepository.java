package com.roadtripmaker.domain.repository;

import com.roadtripmaker.domain.model.RoadUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<RoadUser, UUID> {
    RoadUser findByMail(String mail);
}
