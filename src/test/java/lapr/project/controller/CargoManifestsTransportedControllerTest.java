package lapr.project.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CargoManifestsTransportedControllerTest {
    @Test
    void testConstructor() {
        assertEquals(0.0, (new CargoManifestsTransportedController()).getAverage());
    }

    @Test
    void testGetContManifests() {
        assertEquals(0, (new CargoManifestsTransportedController()).getContManifests());
    }

    @Test
    void testGetAverage() {
        assertEquals(0.0, (new CargoManifestsTransportedController()).getAverage());
    }
}

