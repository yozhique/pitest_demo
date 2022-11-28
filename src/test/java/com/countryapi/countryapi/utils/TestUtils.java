package com.countryapi.countryapi.utils;

import com.countryapi.countryapi.models.Country;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    public static List<Country> getTestCountries() {
        List<Country> countries = new ArrayList<>();
        for ( int i = 1; i<= 20 ; i++ ) {
            countries.add(generateCountry(i));
        }
        return countries;
    }

    private static Country generateCountry(int i) {
        Country country = new Country();
        country.setName("Country" + i);
        country.setCapital("CountryCapital" + i);
        country.setPopulation(i*i);
        country.setArea(i);
        return country;
    }

}
