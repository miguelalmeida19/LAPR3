package lapr.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TripTest {
    @Test
    void testConstructor() {
        Trip actualTrip = new Trip ("Ship IMO", "2020-03-01");

        assertEquals("2020-03-01", actualTrip.getBase_date_time());
        assertEquals("Ship IMO", actualTrip.getShipIMO());
    }
}

