package com.roadtripmaker.domain.repository;

import com.roadtripmaker.domain.model.Geodecoding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GeodecodingRepository extends JpaRepository<Geodecoding, UUID> {
    Geodecoding findByUuid(UUID uuid);
}
