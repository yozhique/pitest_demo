package com.countryapi.countryapi.models;

import lombok.Data;

import java.util.List;

@Data
public class Country {
    private String name;
    private String capital;
    private List<CountryCurrency> currencies;
    private Integer population;
    private Integer area;
}
