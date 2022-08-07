package lapr.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PortTest {
    @Test
    void testConstructor() {
        Port actualPort = new Port("Continent", "GB", "Code", "Port", 10.0, 10.0);

        assertEquals("Code", actualPort.getCode());
        assertEquals("Continent", actualPort.getContinent());
        assertEquals("GB", actualPort.getCountry());
        assertEquals(10.0, actualPort.getLatitude());
        assertEquals(10.0, actualPort.getLongitude());
        assertEquals("Port", actualPort.getPort());
    }

    @Test
    void testConstructor2() {
        Port actualPort = new Port("Continent", "GB", "Code", "Port", 10.0, 10.0);

        assertEquals("Code", actualPort.getCode());
        assertEquals("Port", actualPort.getPort());
        assertEquals(10.0, actualPort.getLongitude());
        assertEquals(10.0, actualPort.getLatitude());
        assertEquals("GB", actualPort.getCountry());
        assertEquals("Continent", actualPort.getContinent());
    }

    @Test
    void testConstructor3() {
        assertEquals("Name", (new Port("Continent", "GB", "Code", "Name", 10.0, 10.0)).toString());
    }
}

