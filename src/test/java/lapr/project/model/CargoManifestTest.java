package lapr.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CargoManifestTest {
    @Test
    void testConstructor() {
        CargoManifest actualCargoManifest = new CargoManifest(1, "Time", "Type", "Ship IMO", 123);

        assertEquals(1, actualCargoManifest.getId());
        assertEquals(123, actualCargoManifest.getPortId());
        assertEquals("Ship IMO", actualCargoManifest.getShipIMO());
        assertEquals("Time", actualCargoManifest.getTime());
        assertEquals("Type", actualCargoManifest.getType());
    }
}

