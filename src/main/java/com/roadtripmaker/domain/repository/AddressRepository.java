package com.roadtripmaker.domain.repository;

import com.roadtripmaker.domain.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
    Address findByUuid(UUID uuid);
}
