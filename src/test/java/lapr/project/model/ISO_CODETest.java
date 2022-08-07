package lapr.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class ISO_CODETest {
    @Test
    void testFromString() {
        assertNull(ISO_CODE.fromString("Text"));
        assertEquals(ISO_CODE.TANK_DANGEROUS_LIQUIDS, ISO_CODE.fromString("20TD"));
    }

    @Test
    void testValueOf() {
        ISO_CODE actualValueOfResult = ISO_CODE.valueOf("GENERAL_PROPOSE");
        assertEquals("22GP", actualValueOfResult.getCode());
        assertEquals(6.096, actualValueOfResult.getSizeX());
        assertEquals(2.4384, actualValueOfResult.getSizeY());
        assertEquals(2.5908, actualValueOfResult.getSizeZ());
    }
}

