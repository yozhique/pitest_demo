package com.countryapi.countryapi;

import com.countryapi.countryapi.models.Country;
import com.countryapi.countryapi.services.CountryService;
import com.countryapi.countryapi.services.RestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.countryapi.countryapi.utils.TestUtils.getTestCountries;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class CountryServiceTest {

    @InjectMocks
    private CountryService service;

    @Mock
    private RestService restService;

    @BeforeEach
    void prepare() {
        Mockito.when(restService.getCountries()).thenReturn(getTestCountries());
    }



    @Test
    public void getCountriesTopTenByPopulationTest() {
        List<Country> result = service.getCountriesTopTenByPopulation();
        assertThat(result.size()).isEqualTo(10);
        assertThat(result.get(0).getPopulation()).isGreaterThan(result.get(1).getPopulation());
    }

    @Test
    public void getCountriesTopTenByAreaTest() {
        List<Country> result = service.getCountriesTopTenByArea();
        assertThat(result.size()).isEqualTo(10);
        assertThat(result.get(0).getArea()).isGreaterThan(result.get(1).getArea());
    }

    @Test
    public void getCountriesTopTenByDensityTest() {
        List<Country> result = service.getCountriesTopTenByDensity();
        assertThat(result.size()).isEqualTo(10);
        assertThat(result.get(0).getPopulation() / result.get(0).getArea())
                .isGreaterThan(result.get(1).getArea() / result.get(1).getArea());
    }

    @Test
    public void getCountriesFullListTest() {
        List<Country> result = service.getCountries();
        assertThat(result.size()).isGreaterThanOrEqualTo(20);
    }
}