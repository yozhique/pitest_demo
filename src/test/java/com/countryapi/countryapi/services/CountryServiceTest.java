package com.countryapi.countryapi.services;

import com.countryapi.countryapi.models.Country;
import com.countryapi.countryapi.utils.TestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.countryapi.countryapi.utils.TestUtils.getTestCountries;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CountryServiceTest {

    @InjectMocks
    private CountryService service;

    @Mock
    private RestService restService;

    @BeforeEach
    void prepare() {
        when(restService.getCountries()).thenReturn(getTestCountries());
    }

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getCountriesTopTenByPopulationTest() throws Exception {
        String expectedJson = TestUtils.getFileContents("CountryServiceTest/top10ByPopulation.json");
        List<Country> expectedResult = Arrays.asList(objectMapper.readValue(expectedJson, Country[].class));
        List<Country> result = service.getCountriesTopTenByPopulation();
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void getCountriesTopTenByPopulationExcludeNullsTest()  {
        List<Country> countries = getTestCountries().stream().peek(c -> c.setPopulation(null)).collect(Collectors.toList());
        when(restService.getCountries()).thenReturn(countries);
        List<Country> result = service.getCountriesTopTenByPopulation();
        assertThat(result).isEmpty();
    }

    @Test
    public void getCountriesTopTenByAreaTest() throws Exception {
        String expectedJson = TestUtils.getFileContents("CountryServiceTest/top10ByArea.json");
        List<Country> expectedResult = Arrays.asList(objectMapper.readValue(expectedJson, Country[].class));
        List<Country> result = service.getCountriesTopTenByArea();
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void getCountriesTopTenByAreaExcludeNullsTest() {
        List<Country> countries = getTestCountries().stream().peek(c -> c.setArea(null)).collect(Collectors.toList());
        when(restService.getCountries()).thenReturn(countries);
        List<Country> result = service.getCountriesTopTenByArea();
        assertThat(result).isEmpty();
    }

    @Test
    public void getCountriesTopTenByDensityTest() throws Exception {
        String expectedJson = TestUtils.getFileContents("CountryServiceTest/top10ByDensity.json");
        List<Country> expectedResult = Arrays.asList(objectMapper.readValue(expectedJson, Country[].class));
        List<Country> result = service.getCountriesTopTenByDensity();
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void getCountriesTopTenByDensityExcludeNullsTest() {
        List<Country> countries = getTestCountries().stream().peek(c -> c.setPopulation(null)).collect(Collectors.toList());
        when(restService.getCountries()).thenReturn(countries);
        List<Country> result = service.getCountriesTopTenByDensity();
        assertThat(result).isEmpty();
    }

    @Test
    public void getCountriesFullListTest() throws Exception {
        String expectedJson = TestUtils.getFileContents("CountryServiceTest/allCountries.json");
        List<Country> expectedResult = Arrays.asList(objectMapper.readValue(expectedJson, Country[].class));
        List<Country> result = service.getCountries();
        assertThat(result).isEqualTo(expectedResult);
    }
}