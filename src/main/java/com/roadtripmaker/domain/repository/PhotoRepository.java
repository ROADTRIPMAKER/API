package com.roadtripmaker.domain.repository;

import com.roadtripmaker.domain.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhotoRepository extends JpaRepository<Photo, UUID> {
    Photo findByUuid(UUID uuid);
}
