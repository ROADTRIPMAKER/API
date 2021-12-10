package com.roadtripmaker.domain.repository;

import com.roadtripmaker.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Role findByLibelle(String libelle);
}
