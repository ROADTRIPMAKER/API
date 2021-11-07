package com.roadtripmaker.domain.repository;

import com.roadtripmaker.domain.model.Poste;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PosteRepository extends JpaRepository<Poste, UUID> {
    Poste findByUuid(UUID uuid);
}
