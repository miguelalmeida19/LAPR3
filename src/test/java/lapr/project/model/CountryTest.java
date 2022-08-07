package lapr.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CountryTest {
    @Test
    void testConstructor() {
        Country actualCountry = new Country("GB", "Continent", "Capital", 10.0, 10.0);

        assertEquals("Capital", actualCountry.getCapital());
        assertEquals("Continent", actualCountry.getContinent());
        assertEquals("GB", actualCountry.getCountryName());
        assertEquals(10.0, actualCountry.getLatitude());
        assertEquals(10.0, actualCountry.getLongitude());
    }

    @Test
    void testConstructor2() {
        Country actualCountry = new Country("GB", "Continent", "Capital", 10.0, 10.0);

        assertEquals("Capital", actualCountry.getCapital());
        assertEquals(10.0, actualCountry.getLongitude());
        assertEquals(10.0, actualCountry.getLatitude());
        assertEquals("GB", actualCountry.getCountryName());
        assertEquals("Continent", actualCountry.getContinent());
    }
}

