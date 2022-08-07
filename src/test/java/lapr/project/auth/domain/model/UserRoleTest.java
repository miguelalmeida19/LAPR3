package lapr.project.auth.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class UserRoleTest {
    @Test
    void testConstructor() {
        UserRole actualUserRole = new UserRole("42", "The characteristics of someone or something");

        assertEquals("The characteristics of someone or something", actualUserRole.getDescription());
        assertEquals("42", actualUserRole.getId());
        assertEquals("42 - The characteristics of someone or something", actualUserRole.toString());
    }

    @Test
    void testConstructor2() {
        UserRole actualUserRole = new UserRole("42", "The characteristics of someone or something");

        assertEquals("The characteristics of someone or something", actualUserRole.getDescription());
        assertEquals("42", actualUserRole.getId());
    }

    @Test
    void testConstructor3() {
        assertThrows(IllegalArgumentException.class, () -> new UserRole("", "The characteristics of someone or something"));

    }

    @Test
    void testConstructor4() {
        assertThrows(IllegalArgumentException.class, () -> new UserRole("42", ""));

    }

    @Test
    void testHasId() {
        assertTrue((new UserRole("42", "The characteristics of someone or something")).hasId("42"));
        assertFalse((new UserRole("Id", "The characteristics of someone or something")).hasId("42"));
        assertFalse((new UserRole("42", "The characteristics of someone or something")).hasId(""));
    }

    @Test
    void testEquals() {
        assertFalse((new UserRole("42", "The characteristics of someone or something")).equals(null));
        assertFalse(
                (new UserRole("42", "The characteristics of someone or something")).equals("Different type to UserRole"));
    }

    @Test
    void testEquals2() {
        UserRole userRole = new UserRole("42", "The characteristics of someone or something");
        assertTrue(userRole.equals(userRole));
        int expectedHashCodeResult = userRole.hashCode();
        assertEquals(expectedHashCodeResult, userRole.hashCode());
    }

    @Test
    void testEquals3() {
        UserRole userRole = new UserRole("42", "The characteristics of someone or something");
        UserRole userRole1 = new UserRole("42", "The characteristics of someone or something");

        assertTrue(userRole.equals(userRole1));
        int expectedHashCodeResult = userRole.hashCode();
        assertEquals(expectedHashCodeResult, userRole1.hashCode());
    }

    @Test
    void testEquals4() {
        UserRole userRole = new UserRole("Id", "The characteristics of someone or something");
        assertFalse(userRole.equals(new UserRole("42", "The characteristics of someone or something")));
    }
}

