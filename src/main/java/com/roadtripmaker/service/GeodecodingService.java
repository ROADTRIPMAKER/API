package com.roadtripmaker.service;

import com.roadtripmaker.domain.model.Geodecoding;

import java.util.Optional;

public interface GeodecodingService {
    Optional<Geodecoding> computeGeoLocation(String fullAddressLine);
}
