package lapr.project.model;

import lapr.project.store.ShipStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class ShipTest {

    public Ship ship;
    public ShipStore shipStore = new ShipStore();

    @BeforeEach
    public void setUp() throws Exception {

        ship = new Ship(123456789, "CargoFest", "IMO1234567", "123XY", "1234", 5.5, 4.5, 100.3, "100");
        shipStore.addShip(ship);
    }

    @Test
    public void addMessage() {
    }

    @Test
    public void getWidth() {
        assertEquals(4.5, ship.getWidth());
    }

    @Test
    public void getCapacity() {
        assertEquals("100", ship.getCapacity());

    }
    @Test
    public void getLength() {
        assertEquals(5.5, ship.getLength());

    }


    @Test
    //Falta este porque é como os toString, uma javardice total :D
    public void getDetails() {
    }

    @Test
    public void getDraft() {
        assertEquals(100.3, ship.getDraft());
    }


    @Test
    public void getIMO() {
        assertEquals("IMO1234567", ship.getIMO());
    }

    @Test
    public void getMMSI() {
        assertEquals(123456789, ship.getMMSI());
    }


    @Test
    public void getCallsign() {
        assertEquals("123XY", ship.getCallsign());
    }

    @Test
    public void getName() {
        assertEquals("CargoFest", ship.getName());
    }

    @Test
    public void getVesselType() {
        assertEquals("1234", ship.getVesselType());
    }


    @Test
    public void getAllMessages() {
    }

    @Test
    public void checkMMSIWrong() {
        Exception thrown = assertThrows(
                Exception.class,
                () -> ship.checkMMSI(123),
                "Invalid MMSI! Must have 9 digits"
        );

        assertTrue(thrown.getMessage().contains("MMSI"));
    }

    @Test
    public void checkMMSIRight() {
        ship.checkMMSI(123456789);

    }

    @Test
    public void checkMMSITooBig() {
        assertThrows(
                IllegalArgumentException.class,()->{
                    ship.checkMMSI(1345343232);

                });

    }

    @Test
    public void checkNameEmpty() {
        Exception thrown = assertThrows(
                Exception.class,
                () -> ship.checkName(""),
                "Name cannot be empty"
        );

        assertTrue(thrown.getMessage().contains("Name"));
    }

    @Test
    public void checkNameRight() {
        ship.checkName("Cargo");
    }

    @Test
    public void checkIMOSmall() {
        Exception thrown = assertThrows(
                Exception.class,
                () -> ship.checkIMO("IMO1234"),
                "Invalid IMO! Must have IMO followed by 7 digits"
        );

        assertTrue(thrown.getMessage().contains("IMO"));
    }

    @Test
    public void checkIMOBig() {
        assertThrows(
                IllegalArgumentException.class,()->{
                    ship.checkIMO("IMO123456789006");

                });
    }

    @Test
    public void checkIMORight() {
        ship.checkIMO("IMO1234567");
    }

    @Test
    public void checkIMOWithoutI() {
        assertThrows(
                IllegalArgumentException.class,()->{
                    ship.checkIMO("EMO1234567");

                });
    }

    @Test
    public void checkIMOWithoutM() {
        assertThrows(
                IllegalArgumentException.class,()->{
                    ship.checkIMO("INO1234567");

                });
    }

    @Test
    public void checkIMOWithoutO() {
        assertThrows(
                IllegalArgumentException.class,()->{
                    ship.checkIMO("IMA1234567");

                });
    }



    @Test
    public void checkVesselTypeWrong() {
        Exception thrown = assertThrows(
                Exception.class,
                () -> ship.checkVesselType(null),
                "Vessel Type cannot be null"
        );

        assertTrue(thrown.getMessage().contains("Vessel"));
    }

    @Test
    public void checkVesselTypeLetters() {
        assertThrows(
                IllegalArgumentException.class,()->{
                    ship.checkVesselType("123re3");

                });

    }

    @Test
    public void checkVesselType() {
        ship.checkVesselType("1234");
    }

    @Test
    public void checkLengthInvalid() {
        Exception thrown = assertThrows(
                Exception.class,
                () -> ship.checkLength(-5),
                "Length must be always greater than 0."
        );

        assertTrue(thrown.getMessage().contains("Length"));
    }

    @Test
    public void checkLength() {
        ship.checkLength(5);

    }

    @Test
    public void checkWidthInvalid() {
        Exception thrown = assertThrows(
                Exception.class,
                () -> ship.checkWidth(-5),
                "Width must be always greater than 0."
        );

        assertTrue(thrown.getMessage().contains("Width"));
    }

    @Test
    public void checkWidth() {
        ship.checkWidth(5);
    }

    @Test
    public void checkCapacity() {
        ship.checkCapacity("5");
    }

    @Test
    public void checkDraft() {
    }

    @Test
    public void checkIMOWrong() {
        assertThrows(
                IllegalArgumentException.class,()->{
                    Ship ship= new Ship(123456789, "CargoFest", "IMO123456788", "123XY", "1234", 5.5, 4.5, 100.3, "100");

                });

    }
    @Test
    public void checkMMSIWrongShip() {

        assertThrows(
                IllegalArgumentException.class,()->{
                    Ship ship= new Ship(1234567891, "CargoFest", "IMO1234567", "123XY", "1234", 5.5, 4.5, 100.3, "100");

                });
    }
    @Test
    public void checkNameWrong() {

        assertThrows(
                IllegalArgumentException.class,()->{
                    Ship ship= new Ship(123456789, "", "IMO1234567", "123XY", "1234", 5.5, 4.5, 100.3, "100");

                });
    }

    @Test
    public void checkVesselTypeWrongShip() {

        assertThrows(
                IllegalArgumentException.class,()->{
                    Ship ship= new Ship(123456789, "CargoFest", "IMO1234567", "123XY", "123y", 5.5, 4.5, 100.3, "100");

                });
    }
    @Test
    public void checkLengthWrongShip() {
        assertThrows(
                IllegalArgumentException.class,()->{
                    Ship ship= new Ship(123456789, "CargoFest", "IMO1234567", "123XY", "1234", -5.5, 4.5, 100.3, "100");

                });
    }
    @Test
    public void checkLengthWrongShip1() {
        Ship ship= new Ship(123456789, "CargoFest", "IMO1234567", "123XY", "1234", 1, 4.5, 100.3, "100");
    }
    @Test
    public void checkLengthWrongShip2() {
        Ship ship= new Ship(123456789, "CargoFest", "IMO1234567", "123XY", "1234", 0, 4.5, 100.3, "100");
    }
    @Test
    public void checkWidthWrong() {
        assertThrows(
                IllegalArgumentException.class,()->{
                    Ship ship= new Ship(123456789, "CargoFest", "IMO1234567", "123XY", "1234", 5.5, -4.5, 100.3, "100");

                });
    }
    @Test
    public void checkWidthWrong1() {
        Ship ship= new Ship(123456789, "CargoFest", "IMO1234567", "123XY", "1234", 5.5, 1, 100.3, "100");
    }
    @Test
    public void checkWidthWrong2() {
        Ship ship= new Ship(123456789, "CargoFest", "IMO1234567", "123XY", "1234", 5.5, 0, 100.3, "100");
    }

    @Test
    public void checkCapacity1() {
        Exception thrown = assertThrows(
                Exception.class,
                () -> ship.checkCapacity("-5"),
                "Capacity must be always greater than 0."
        );

        assertTrue(thrown.getMessage().contains("Capacity"));
    }
    @Test
    public void checkCapacity2() {

        Ship ship= new Ship(123456789, "CargoFest", "IMO1234567", "123XY", "1234", 5.5, 4.5, 100.3, "1");

    }
    @Test
    public void checkShipDetails() {
        System.out.println(ship.getDetails());
        String expected="MMSI: 123456789\n" +
                "NAME: CargoFest\n" +
                "IMO: IMO1234567\n" +
                "CALLSIGN: 123XY\n" +
                "VESSELTYPE: 1234\n" +
                "LENGTH: 5.5\n" +
                "WIDTH: 4.5\n" +
                "CAPACITY: 100\n" +
                "DRAFT: 100.3\n";
       assertEquals(true,expected.equals(ship.getDetails()));

    }
}