package com.countryapi.countryapi.controllers;

import com.countryapi.countryapi.services.CountryService;
import com.countryapi.countryapi.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CountryController.class)
public class CountryControllerTest {

    @MockBean
    private CountryService countryService;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void prepare() {
        when(countryService.getCountries()).thenReturn(TestUtils.getTestCountries());
        when(countryService.getCountriesTopTenByDensity()).thenReturn(TestUtils.getTop10ByDensity());
        when(countryService.getCountriesTopTenByArea()).thenReturn(TestUtils.getTop10ByArea());
        when(countryService.getCountriesTopTenByPopulation()).thenReturn(TestUtils.getTop10ByPopulation());
    }

    @Test
    void getCountriesTest() throws Exception {
        String expectedJson = TestUtils.getFileContents("CountryControllerTest/allCountries.json");
        mvc.perform(get("/countries")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    void getCountriesTop10ByPopulation() throws Exception {
        String expectedJson = TestUtils.getFileContents("CountryControllerTest/top10ByPopulation.json");
        mvc.perform(get("/countries/populationTopTen")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    void getCountriesTop10ByArea() throws Exception {
        String expectedJson = TestUtils.getFileContents("CountryControllerTest/top10ByArea.json");
        mvc.perform(get("/countries/areaTopTen")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    void getCountriesTop10ByDensity() throws Exception {
        String expectedJson = TestUtils.getFileContents("CountryControllerTest/top10ByDensity.json");
        mvc.perform(get("/countries/densityTopTen")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }
}
