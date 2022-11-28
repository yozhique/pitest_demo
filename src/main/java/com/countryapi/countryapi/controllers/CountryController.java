package com.countryapi.countryapi.controllers;

import com.countryapi.countryapi.models.Country;
import com.countryapi.countryapi.services.CountryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> getCountries() {
        return countryService.getCountries();
    }

    @GetMapping("/populationTopTen")
    public List<Country> getCountriesTop10ByPopulation() {
        return countryService.getCountriesTopTenByPopulation();
    }

    @GetMapping("/areaTopTen")
    public List<Country> getCountriesTop10ByArea() {
        return countryService.getCountriesTopTenByArea();
    }

    @GetMapping("/densityTopTen")
    public List<Country> getCountriesTop10ByDensity() {
        return countryService.getCountriesTopTenByDensity();
    }
}