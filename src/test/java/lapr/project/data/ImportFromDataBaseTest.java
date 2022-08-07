package lapr.project.data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import lapr.project.model.Country;
import lapr.project.model.Port;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImportFromDataBaseTest {
    @Test
    void testGetContainersByShipPortType() {
        assertThrows(IllegalArgumentException.class,
                () -> ImportFromDataBase.getContainersByShipPortType("Ship IMO", LocalDateTime.of(1, 1, 1, 1, 1), "Type"));
    }

    @Test
    void testGetPositionOfContainer() {
        assertThrows(IllegalArgumentException.class, () -> ImportFromDataBase.getPositionOfContainer("Container ID"));
    }

    @Test
    void testGetOccupancyRateByCargoManifest() {
        assertThrows(IllegalArgumentException.class,
                () -> ImportFromDataBase.getOccupancyRateByCargoManifest("Code", "Cargo manifest"));
        assertThrows(IllegalArgumentException.class,
                () -> ImportFromDataBase.getOccupancyRateByCargoManifest("BORDERNAME", "Cargo manifest"));
        assertThrows(IllegalArgumentException.class,
                () -> ImportFromDataBase.getOccupancyRateByCargoManifest("42", "Cargo manifest"));
    }

    @Test
    void testGetOccupancyRateByDate() {
        assertEquals(0.0, ImportFromDataBase.getOccupancyRateByDate("Code", "2020-03-01"));
        assertEquals(0.0, ImportFromDataBase.getOccupancyRateByDate("BORDERNAME", "2020-03-01"));
        assertEquals(0.0, ImportFromDataBase.getOccupancyRateByDate("42", "2020-03-01"));
    }

    @Test
    void testGetAllCountries() {
        assertFalse(ImportFromDataBase.getAllCountries().isEmpty());
    }

    @Test
    void testGetCountryBorders() {
        Country country = new Country("GB", "Continent", "Capital", 10.0, 10.0);

        assertTrue(ImportFromDataBase.getCountryBorders(country, new ArrayList<>()).isEmpty());
    }

    @Test
    void testGetCountryBorders2() {
        assertTrue(ImportFromDataBase.getCountryBorders(null, new ArrayList<>()).isEmpty());
    }

    @Test
    void testGetPortFromCountry() {
        assertTrue(ImportFromDataBase.getPortFromCountry("GB").isEmpty());
    }

    @Test
    void testGetDistanceBetweenPorts() {
        Port port1 = new Port("Continent", "GB", "Code", "Name", 10.0, 10.0);

        assertEquals(0.0,
                ImportFromDataBase.getDistanceBetweenPorts(port1, new Port("Continent", "GB", "Code", "Name", 10.0, 10.0)));
    }

    @Test
    void testGetDistanceBetweenPorts2() {
        assertEquals(0.0,
                ImportFromDataBase.getDistanceBetweenPorts(null, new Port("Continent", "GB", "Code", "Name", 10.0, 10.0)));
    }

    @Test
    void testGetDistanceBetweenPorts3() {
        assertEquals(0.0,
                ImportFromDataBase.getDistanceBetweenPorts(new Port("Continent", "GB", "Code", "Name", 10.0, 10.0), null));
    }

    @Test
    void testGetPossibleConnections() {
        assertTrue(
                ImportFromDataBase.getPossibleConnections(new Port("Continent", "GB", "Code", "Name", 10.0, 10.0)).isEmpty());
        assertTrue(ImportFromDataBase.getPossibleConnections(null).isEmpty());
    }

    @Test
    void testGetRouteOfContainer() {
        assertNull(ImportFromDataBase.getRouteOfContainer("Container ID", "Client Reg"));
    }

    @Test
    void testWrapLinkedHashMap() {
        LinkedHashMap<Port, Double> linkedHashMap = new LinkedHashMap<>();
        assertTrue(
                ImportFromDataBase.wrapLinkedHashMap(linkedHashMap)
                        .isEmpty());
    }

    @Test
    void testWrapLinkedHashMap2() {
        LinkedHashMap<Port, Double> portResultDoubleMap = new LinkedHashMap<>();
        portResultDoubleMap.put(new Port("Continent", "GB", "Code", "Name", 10.0, 10.0), 10.0);
        LinkedHashMap<Double, Port> actualWrapLinkedHashMapResult = ImportFromDataBase
                .wrapLinkedHashMap(portResultDoubleMap);
        assertEquals(1, actualWrapLinkedHashMapResult.size());
        assertTrue(actualWrapLinkedHashMapResult.containsKey(10.0));
    }
}

