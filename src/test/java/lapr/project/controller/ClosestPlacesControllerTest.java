package lapr.project.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ClosestPlacesControllerTest {

    @Test
    void testGetPlaces() {
        assertTrue((new ClosestPlacesController()).getPlaces("Continent").isEmpty());
    }
}

