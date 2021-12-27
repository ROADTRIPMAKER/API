package com.roadtripmaker.service;

import com.roadtripmaker.domain.model.Address;
import com.roadtripmaker.domain.model.Geodecoding;

import java.util.Optional;

public interface GeodecodingService {
    Geodecoding computeGeoLocation(Address address);
}
