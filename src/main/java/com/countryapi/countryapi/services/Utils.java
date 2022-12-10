package com.countryapi.countryapi.services;

import com.countryapi.countryapi.models.Country;

class Utils {

    public static double getDensity(Country country) {
        if (country.getArea() == 0) { // technically it should be infinity, but no :)
            return 0;
        }
        return country.getPopulation().doubleValue()/country.getArea().doubleValue();
    }

}
