package lapr.project.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CMNextWeekControllerTest {

    @Test
    void testNextSunday() {
        assertEquals("2022-01-23", (new CMNextWeekController()).nextSunday());
    }

    @Test
    void testNextSaturday() {
        assertEquals("2022-01-29", (new CMNextWeekController()).nextSaturday());
    }

}
