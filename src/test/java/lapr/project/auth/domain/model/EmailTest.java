package lapr.project.auth.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class EmailTest {
    @Test
    void testConstructor() {
        Email actualEmail = new Email("jane.doe@example.org");
        assertEquals("jane.doe@example.org", actualEmail.getString());
        assertEquals("jane.doe@example.org", actualEmail.toString());
    }

    @Test
    void testConstructor2() {
        assertEquals("jane.doe@example.org", (new Email("jane.doe@example.org")).getString());
    }

    @Test
    void testConstructor3() {
        assertThrows(IllegalArgumentException.class,
                () -> new Email("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"));
    }

    @Test
    void testConstructor4() {
        assertThrows(IllegalArgumentException.class, () -> new Email(""));
    }

    @Test
    void testEquals() {
        assertFalse((new Email("jane.doe@example.org")).equals(null));
        assertFalse((new Email("jane.doe@example.org")).equals("Different type to Email"));
        assertFalse((new Email("jane.doe@example.org")).equals(null));
    }

    @Test
    void testEquals2() {
        Email email = new Email("jane.doe@example.org");
        assertTrue(email.equals(email));
        int expectedHashCodeResult = email.hashCode();
        assertEquals(expectedHashCodeResult, email.hashCode());
    }

    @Test
    void testEquals3() {
        Email email = new Email("jane.doe@example.org");
        Email email1 = new Email("jane.doe@example.org");
        assertTrue(email.equals(email1));
        int expectedHashCodeResult = email.hashCode();
        assertEquals(expectedHashCodeResult, email1.hashCode());
    }
}

