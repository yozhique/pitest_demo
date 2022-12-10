package com.countryapi.countryapi.utils;

import com.countryapi.countryapi.models.Country;
import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestUtils {

    public static List<Country> getTestCountries() {
        List<Country> countries = new ArrayList<>();
        for ( int i = 1; i<= 20 ; i++ ) {
            countries.add(generateCountry(i));
        }
        return countries;
    }

    public static List<Country> getTop10ByArea() {
        List<Country> countries = getTestCountries();
        countries.sort(Comparator.comparing(Country::getArea).reversed());
        return countries.subList(0, 10);
    }

    public static List<Country> getTop10ByPopulation() {
        List<Country> countries = getTestCountries();
        countries.sort(Comparator.comparing(Country::getPopulation).reversed());
        return countries.subList(0, 10);
    }

    public static List<Country> getTop10ByDensity() {
        List<Country> countries = getTestCountries();
        countries.sort(Comparator.comparingDouble(TestUtils::getDensity).reversed());
        return countries.subList(0, 10);
    }

    private static double getDensity(Country country) {
        if (country.getArea() == 0) {
            return 0;
        }
        return country.getPopulation().doubleValue()/country.getArea().doubleValue();
    }

    private static Country generateCountry(int i) {
        Country country = new Country();
        country.setName("Country" + i);
        country.setCapital("CountryCapital" + i);
        country.setPopulation(i*i);
        country.setArea(i);
        return country;
    }

    @SneakyThrows
    public static String getFileContents(String fileName) {
        return StreamUtils.copyToString( new ClassPathResource(fileName).getInputStream(), Charset.defaultCharset());
    }

}
