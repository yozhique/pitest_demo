package com.countryapi.countryapi.services;

import com.countryapi.countryapi.models.Country;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UtilsTest {

    @Test
    void getDensityNormalTest() {
        Country country = new Country();
        country.setArea(100);
        country.setPopulation(1000);
        assertThat(Utils.getDensity(country)).isEqualTo(10.0d);
    }

    @Test
    void getDensityZeroAreaTest() {
        Country country = new Country();
        country.setArea(0);
        country.setPopulation(1000);
        assertThat(Utils.getDensity(country)).isEqualTo(0);
    }

}
