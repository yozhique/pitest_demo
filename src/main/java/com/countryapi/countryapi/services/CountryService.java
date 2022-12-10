package com.countryapi.countryapi.services;

import com.countryapi.countryapi.models.Country;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class CountryService {


    private final RestService restService;

    public CountryService(RestService restService) {
        this.restService = restService;
    }

    public List<Country> getCountries() {
        return restService.getCountries();
    }

    public List<Country> getCountriesTopTenByPopulation() {
        return restService.getCountries().stream()
                .filter(country -> country.getPopulation() != null)
                .sorted(Comparator.comparing(Country::getPopulation).reversed())
                .limit(10)
                .collect(toList());
    }

    public List<Country> getCountriesTopTenByArea() {
        return restService.getCountries().stream()
                .filter(country -> country.getArea() != null)
                .sorted(Comparator.comparing(Country::getArea).reversed())
                .limit(10)
                .collect(toList());
    }

    public List<Country> getCountriesTopTenByDensity() {
        return restService.getCountries().stream()
                .filter(country -> country.getArea() != null && country.getPopulation() != null)
                .sorted(Comparator.comparingDouble(Utils::getDensity).reversed())
                .limit(10)
                .collect(toList());
    }

}