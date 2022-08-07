package lapr.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ContainerTest {
    @Test
    void testConstructor() {
        Container actualContainer = new Container(1, "Payload", 10.0, 10.0, 10.0, ISO_CODE.FLAT, 12.0, "cheiro");

        assertEquals(10.0, actualContainer.getCargoWeight());
        assertEquals(10.0, actualContainer.getGross());
        assertEquals(1, actualContainer.getID());
        assertEquals(ISO_CODE.FLAT, actualContainer.getIsoCode());
        assertEquals("Payload", actualContainer.getPayload());
        assertEquals(10.0, actualContainer.getTare());
    }

    @Test
    void testConstructor2() {
        Container actualContainer = new Container(1, "Payload", 10.0, 10.0, 10.0, ISO_CODE.FLAT, 12.0, "cheiro");

        assertEquals(10.0, actualContainer.getCargoWeight());
        assertEquals(10.0, actualContainer.getTare());
        assertEquals("Payload", actualContainer.getPayload());
        assertEquals(ISO_CODE.FLAT, actualContainer.getIsoCode());
        assertEquals(1, actualContainer.getID());
        assertEquals(10.0, actualContainer.getGross());
    }

    @Test
    void testConstructor3() {
        assertEquals(10.0,
                (new Container(1, "Payload", 10.0, 10.0, 10.0, ISO_CODE.GENERAL_PROPOSE, 10.0, "jane.doe@example.org"))
                        .getTemperature());
    }

    @Test
    void testConstructor4() {
        assertEquals("jane.doe@example.org",
                (new Container(1, "Payload", 10.0, 10.0, 10.0, ISO_CODE.GENERAL_PROPOSE, 10.0, "jane.doe@example.org"))
                        .getClientEmail());
    }
}

