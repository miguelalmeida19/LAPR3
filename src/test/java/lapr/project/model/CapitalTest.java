package lapr.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CapitalTest {
    @Test
    void testConstructor() {
        assertEquals("Capital", (new Capital("GB", "Continent", "Capital", 10.0, 10.0)).toString());
    }

    @Test
    void testConstructor2() {
        Capital actualCapital = new Capital("GB", "Continent", "Capital", 10.0, 10.0);

        assertEquals("Capital", actualCapital.getCapital());
        assertEquals(10.0, actualCapital.getLongitude());
        assertEquals(10.0, actualCapital.getLatitude());
        assertEquals("GB", actualCapital.getCountryName());
        assertEquals("Continent", actualCapital.getContinent());
    }

    @Test
    void testGetLatitude() {
        assertEquals(10.0, (new Capital("GB", "Continent", "Capital", 10.0, 10.0)).getLatitude());
    }

    @Test
    void testGetLongitude() {
        assertEquals(10.0, (new Capital("GB", "Continent", "Capital", 10.0, 10.0)).getLongitude());
    }

    @Test
    void testGetCapital() {
        assertEquals("Capital", (new Capital("GB", "Continent", "Capital", 10.0, 10.0)).getCapital());
    }

    @Test
    void testGetContinent() {
        assertEquals("Continent", (new Capital("GB", "Continent", "Capital", 10.0, 10.0)).getContinent());
    }

    @Test
    void testGetCountryName() {
        assertEquals("GB", (new Capital("GB", "Continent", "Capital", 10.0, 10.0)).getCountryName());
    }
}

