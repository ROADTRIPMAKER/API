package com.roadtripmaker.domain.repository;

import com.roadtripmaker.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByLibelle(String libelle);
}
