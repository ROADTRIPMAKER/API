package com.roadtripmaker.domain.repository;

import com.roadtripmaker.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByMail(String mail);
}
