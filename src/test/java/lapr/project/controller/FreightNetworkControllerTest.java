package lapr.project.controller;

import java.util.ArrayList;
import java.util.LinkedList;

import lapr.project.model.Capital;
import lapr.project.model.Country;

import lapr.project.model.Port;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FreightNetworkControllerTest {
    @Test
    void testGetCountries() {
        assertFalse((new FreightNetworkController()).getCountries().isEmpty());
        assertFalse((new FreightNetworkController()).getCountries().isEmpty());
    }

    @Test
    void testGetDistanceBetweenPortCapital() {
        FreightNetworkController freightNetworkController = new FreightNetworkController();
        Port port = new Port("Continent", "GB", "Code", "Name", 10.0, 10.0);

        assertEquals(0.0, freightNetworkController.getDistanceBetweenPortCapital(port,
                new Country("GB", "Continent", "Capital", 10.0, 10.0)));
    }

    @Test
    void testGetDistanceBetweenPortCapital3() {
        FreightNetworkController freightNetworkController = new FreightNetworkController();
        Port port = new Port("Continent", "GB", "Code", "Name", 10.0, 10.0);

        assertEquals(0.0, freightNetworkController.getDistanceBetweenPortCapital(port,
                new Country("GB", "Continent", "Capital", 10.0, 10.0)));
    }

    @Test
    void testGetDistanceBetweenCapitals() {
        FreightNetworkController freightNetworkController = new FreightNetworkController();
        Country c1 = new Country("GB", "Continent", "Capital", 10.0, 10.0);

        assertEquals(0.0,
                freightNetworkController.getDistanceBetweenCapitals(c1, new Country("GB", "Continent", "Capital", 10.0, 10.0)));
    }

    @Test
    void testGetDistanceBetweenCapitals3() {
        FreightNetworkController freightNetworkController = new FreightNetworkController();
        Country c1 = new Country("GB", "Continent", "Capital", 10.0, 10.0);

        assertEquals(0.0,
                freightNetworkController.getDistanceBetweenCapitals(c1, new Country("GB", "Continent", "Capital", 10.0, 10.0)));
    }

    @Test
    void testIndexOfObject() {
        FreightNetworkController freightNetworkController = new FreightNetworkController();
        assertEquals(0, freightNetworkController.indexOfObject(new ArrayList<>(), "42"));
    }

    @Test
    void testIndexOfObject2() {
        FreightNetworkController freightNetworkController = new FreightNetworkController();

        ArrayList<Object> objectList = new ArrayList<>();
        objectList.add("42");
        assertEquals(0, freightNetworkController.indexOfObject(objectList, "42"));
    }

    @Test
    void testIndexOfObject3() {
        FreightNetworkController freightNetworkController = new FreightNetworkController();
        assertEquals(0, freightNetworkController.indexOfObject(new ArrayList<>(), "42"));
    }

    @Test
    void testIndexOfObject4() {
        FreightNetworkController freightNetworkController = new FreightNetworkController();

        ArrayList<Object> objectList = new ArrayList<>();
        objectList.add("42");
        assertEquals(0, freightNetworkController.indexOfObject(objectList, "42"));
    }

    @Test
    void testGetVertices() {
        assertFalse((new FreightNetworkController()).getVertices().isEmpty());
        assertFalse((new FreightNetworkController()).getVertices().isEmpty());
    }


    @Test
    void testInsertInMatrix2() {
        FreightNetworkController freightNetworkController = new FreightNetworkController();
        assertEquals(281, freightNetworkController.insertInMatrix(new ArrayList<>(), new Double[][]{new Double[]{10.0}}, 1));
    }




    @Test
    void testBuild3() {
        assertEquals(146, (new FreightNetworkController()).build(1));
    }

    @Test
    void testPrint() {
        FreightNetworkController.print(new Double[][]{new Double[]{10.0}}, new ArrayList<>());
    }

    @Test
    void testPrint2() {
        assertEquals(0, FreightNetworkController.print(new Double[][]{new Double[]{10.0}}, new ArrayList<>()));
    }


    @Test
    void testConnectPortToCountryPorts4() {
        FreightNetworkController freightNetworkController = new FreightNetworkController();
        Port port = new Port("Continent", "GB", "Code", "Name", 10.0, 10.0);

        assertEquals(0, freightNetworkController.connectPortToCountryPorts(port, new Double[][]{new Double[]{10.0}},
                new ArrayList<>()));
    }

    @Test
    void testConnectPortToCountryPorts6() {
        FreightNetworkController freightNetworkController = new FreightNetworkController();
        Port port = new Port("Continent", "GB", "Code", "Name", 10.0, 10.0);

        ArrayList<Object> objectList = new ArrayList<>();
        objectList.add("42");
        assertEquals(0,
                freightNetworkController.connectPortToCountryPorts(port, new Double[][]{new Double[]{10.0}}, objectList));
    }


    @Test
    void testConnectPortToNClosestPorts5() {
        FreightNetworkController freightNetworkController = new FreightNetworkController();
        Port port = new Port("Continent", "GB", "Code", "Name", 10.0, 10.0);

        assertEquals(0, freightNetworkController.connectPortToNClosestPorts(port, new Double[][]{new Double[]{10.0}},
                new ArrayList<>(), 1));
    }

    @Test
    void testConnectPortToNClosestPorts6() {
        FreightNetworkController freightNetworkController = new FreightNetworkController();
        assertEquals(0, freightNetworkController.connectPortToNClosestPorts(null, new Double[][]{new Double[]{10.0}},
                new ArrayList<>(), 1));
    }

    @Test
    void testConnectPortToNClosestPorts7() {
        FreightNetworkController freightNetworkController = new FreightNetworkController();
        Port port = new Port("Continent", "GB", "Code", "Name", 10.0, 10.0);

        ArrayList<Object> objectList = new ArrayList<>();
        objectList.add("42");
        assertEquals(0,
                freightNetworkController.connectPortToNClosestPorts(port, new Double[][]{new Double[]{10.0}}, objectList, 1));
    }
}

