package com.roadtripmaker.service;

import com.roadtripmaker.domain.model.Address;
import com.roadtripmaker.domain.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service @RequiredArgsConstructor @Transactional @Slf4j public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    @Override public Address saveAddress(Address address) {
        log.info("Sauvegarde d'une nouvelle adresse  : {} en base de données.", address.getUuid());

        return this.addressRepository.save(address);
    }
}
