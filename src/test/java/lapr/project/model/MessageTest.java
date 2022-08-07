package lapr.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class MessageTest {
    @Test
    void testConstructor() {
        LocalDateTime ofResult = LocalDateTime.of(1, 1, 1, 1, 1);
        String transceiver = "hello";
        Message actualMessage = new Message(ofResult, 10.0, 10.0, 10.0, 10.0, 10.0, transceiver);

        assertEquals(10.0, actualMessage.getCog());
        assertEquals(10.0, actualMessage.getHeading());
        assertEquals(10.0, actualMessage.getLatitude());
        assertSame(ofResult, actualMessage.getLocalDateTime());
        assertEquals(10.0, actualMessage.getLongitude());
        assertEquals(10.0, actualMessage.getSog());
        assertSame(transceiver, actualMessage.getTransceiver());
    }

    @Test
    void testConstructor2() {
        LocalDateTime localDateTime = LocalDateTime.of(1, 1, 1, 1, 1);
        String transceiver = "hello";
        Message actualMessage = new Message(localDateTime, 10.0, 10.0, 10.0, 10.0, 10.0, transceiver);

        assertEquals(10.0, actualMessage.getCog());
        assertSame(transceiver, actualMessage.getTransceiver());
        assertEquals(10.0, actualMessage.getSog());
        assertEquals(10.0, actualMessage.getLongitude());
        assertEquals(10.0, actualMessage.getLatitude());
        assertEquals("01:01", actualMessage.getLocalDateTime().toLocalTime().toString());
        assertEquals(10.0, actualMessage.getHeading());
    }

    @Test
    void testConstructor3() {
        LocalDateTime ofResult = LocalDateTime.of(1, 1, 1, 1, 1);
        Message actualMessage = new Message(ofResult, 10.0, 10.0, 10.0, 10.0, 10.0, "Transceiver");

        assertEquals(10.0, actualMessage.getCog());
        assertEquals(10.0, actualMessage.getHeading());
        assertEquals(10.0, actualMessage.getLatitude());
        assertSame(ofResult, actualMessage.getLocalDateTime());
        assertEquals(10.0, actualMessage.getLongitude());
        assertEquals(10.0, actualMessage.getSog());
        assertEquals("Transceiver", actualMessage.getTransceiver());
        assertEquals("10.0                 10.0                 10.0                 10.0                 10.0            "
                + "     Transceiver          0001-01-01 01:01    ", actualMessage.toString());
    }

    @Test
    void testConstructor4() {
        Message actualMessage = new Message(LocalDateTime.of(1, 1, 1, 1, 1), 10.0, 10.0, 10.0, 10.0, 10.0, "Transceiver");

        assertEquals(10.0, actualMessage.getCog());
        assertEquals("Transceiver", actualMessage.getTransceiver());
        assertEquals(10.0, actualMessage.getSog());
        assertEquals(10.0, actualMessage.getLongitude());
        assertEquals(10.0, actualMessage.getLatitude());
        assertEquals("01:01", actualMessage.getLocalDateTime().toLocalTime().toString());
        assertEquals(10.0, actualMessage.getHeading());
    }

    @Test
    void testUploadToDatabase() {
        assertEquals("\n"
                        + "INSERT INTO MESSAGES (BASE_DATE_TIME, SHIPIMO, LATITUDE, LONGITUDE ,SOG, COG, HEADING, TRANSCEIVER_CLASS)"
                        + " VALUES('01-01-0001 01:01:00', 'Ship IMO',10.0,10.0,10.0,10.0,10.0,'Transceiver');",
                (new Message(LocalDateTime.of(1, 1, 1, 1, 1), 10.0, 10.0, 10.0, 10.0, 10.0, "Transceiver"))
                        .uploadToDatabase("Ship IMO"));
    }

    @Test
    void testCompareTo() {
        LocalDateTime localDateTime = LocalDateTime.of(1, 1, 1, 1, 1);
        Message message = new Message(localDateTime, 10.0, 10.0, 10.0, 10.0, 10.0, "name");
        LocalDateTime localDateTime1 = LocalDateTime.of(1, 1, 1, 1, 1);
        assertEquals(0,
                message.compareTo(new Message(localDateTime1, 10.0, 10.0, 10.0, 10.0, 10.0, "name")));
    }

    @Test
    void testCompareTo2() {
        Message message = new Message(LocalDateTime.of(1, 1, 1, 1, 1), 10.0, 10.0, 10.0, 10.0, 10.0, "Transceiver");
        assertEquals(0,
                message.compareTo(new Message(LocalDateTime.of(1, 1, 1, 1, 1), 10.0, 10.0, 10.0, 10.0, 10.0, "Transceiver")));
    }

    @Test
    void testCompareToWrong() {
        LocalDateTime localDateTime = LocalDateTime.of(1, 1, 1, 1, 1);
        Message message = new Message(localDateTime, 10.0, 10.0, 10.0, 10.0, 10.0, "name");
        LocalDateTime localDateTime1 = LocalDateTime.of(1, 1, 1, 1, 2);
        assertEquals(-1,
                message.compareTo(new Message(localDateTime1, 10.0, 10.0, 10.0, 10.0, 10.0, "name")));
    }
}

