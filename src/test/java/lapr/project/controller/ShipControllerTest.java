package lapr.project.controller;

import java.time.LocalDateTime;

import lapr.project.model.Message;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

class ShipControllerTest {
    @Test
    void testConstructor() {
        ShipController actualShipController = new ShipController();
        assertEquals(15, actualShipController.getFiles().size());
        assertTrue(actualShipController.getShips().isEmpty());
        assertFalse(actualShipController.getPairsOfShips().isEmpty());
    }

    @Test
    void testConstructor2() {
        ShipController actualShipController = new ShipController();
        assertTrue(actualShipController.getShips().isEmpty());
    }

    @Test
    void testImportShips() {


        (new ShipController()).importShips("files/sships.csv");
    }

    @Test
    void testGetDetails() {
        ShipController controller = new ShipController();
        controller.importShips("files/testShip.csv");
        assertNull((new ShipController()).getDetails("42"));
        assertNull((new ShipController()).getDetails("424242"));
        assertNull((new ShipController()).getDetails("42"));
        assertNull((new ShipController()).getDetails("424242"));
        assertNotNull((new ShipController()).getDetails("211331640"));
        assertNotNull((new ShipController()).getDetails("IMO9193305"));
        assertNotNull((new ShipController()).getDetails("DHBN"));
    }

    @Test
    void testGetFiles() {
        assertEquals(15, (new ShipController()).getFiles().size());
    }

    @Test
    void testGetTopNShips() {
        ShipController shipController = new ShipController();
        LocalDateTime start = LocalDateTime.of(1, 1, 1, 1, 1);
        assertTrue(shipController.getTopNShips(1, start, LocalDateTime.of(1, 1, 1, 1, 1)).isEmpty());
    }

    @Test
    void getAllMessages(){
        ShipController controller = new ShipController();
        controller.importShips("files/testShip.csv");
        assertNotNull(controller.getAllMessages("211331640"));
    }

}