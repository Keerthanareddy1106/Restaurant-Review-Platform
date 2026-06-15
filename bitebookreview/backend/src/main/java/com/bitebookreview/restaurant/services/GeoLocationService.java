package com.bitebookreview.restaurant.services;

import com.bitebookreview.restaurant.domain.GeoLocation;
import com.bitebookreview.restaurant.domain.entities.Address;

public interface GeoLocationService {
    GeoLocation geoLocate(Address address);
}
