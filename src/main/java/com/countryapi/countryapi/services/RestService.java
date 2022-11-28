package com.countryapi.countryapi.services;

import com.countryapi.countryapi.models.Country;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class RestService {

    private final String restcountriesUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public RestService(@Value("${restcountries.url}") String restcountriesUrl) {
        this.restcountriesUrl = restcountriesUrl;
    }

    public List<Country> getCountries() {
        var countries = restTemplate.getForObject(restcountriesUrl, Country[].class);
        if (countries != null) {
            return Arrays.asList(countries);
        } else {
            return Collections.emptyList();
        }
    }
}
