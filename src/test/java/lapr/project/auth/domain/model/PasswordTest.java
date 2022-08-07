package lapr.project.auth.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PasswordTest {
    @Test
    void testConstructor() {
        // TODO: This test is incomplete.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     Password.password

        new Password("iloveyou");
    }

    @Test
    void testConstructor2() {
        // TODO: This test is incomplete.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by <init>(String)
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        new Password("iloveyou");
    }

    @Test
    void testConstructor3() {
        assertThrows(IllegalArgumentException.class, () -> new Password(""));
    }

    @Test
    void testConstructor4() {
        // TODO: This test is incomplete.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by <init>(String)
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        new Password("lapr.project.auth.domain.model.Password42");
    }

    @Test
    void testCheckPassword() {
        assertFalse((new Password("iloveyou")).checkPassword("Pwd"));
        assertFalse((new Password("iloveyou")).checkPassword(""));
        assertTrue((new Password("lapr.project.auth.domain.model.Password"))
                .checkPassword("lapr.project.auth.domain.model.Password"));
    }

    @Test
    void testEquals() {
        assertFalse((new Password("iloveyou")).equals(null));
        assertFalse((new Password("iloveyou")).equals("Different type to Password"));
    }

    @Test
    void testEquals2() {
        Password password = new Password("iloveyou");
        assertTrue(password.equals(password));
        int expectedHashCodeResult = password.hashCode();
        assertEquals(expectedHashCodeResult, password.hashCode());
    }

    @Test
    void testEquals3() {
        Password password = new Password("iloveyou");
        assertFalse(password.equals(new Password("iloveyou")));
    }
}

