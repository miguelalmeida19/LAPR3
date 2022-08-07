package lapr.project.auth.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

class UserTest {
    @Test
    void testConstructor() {
        Email email = new Email("jane.doe@example.org");
        User actualUser = new User(email, new Password("iloveyou"), "Name");

        assertSame(email, actualUser.getId());
        assertEquals("Name", actualUser.getName());
        assertEquals("jane.doe@example.org - Name", actualUser.toString());
    }

    @Test
    void testConstructor2() {
        Email id = new Email("jane.doe@example.org");
        User actualUser = new User(id, new Password("iloveyou"), "Name");

        assertEquals("jane.doe@example.org - Name", actualUser.toString());
        assertEquals("Name", actualUser.getName());
        assertTrue(actualUser.getRoles().isEmpty());
    }

    @Test
    void testConstructor3() {
        assertThrows(IllegalArgumentException.class, () -> new User(null, new Password("iloveyou"), "Name"));

    }

    @Test
    void testConstructor4() {
        Email id = new Email("jane.doe@example.org");
        assertThrows(IllegalArgumentException.class, () -> new User(id, new Password("iloveyou"), ""));

    }

    @Test
    void testHasId() {
        Email id = new Email("jane.doe@example.org");
        User user = new User(id, new Password("iloveyou"), "Name");
        assertTrue(user.hasId(new Email("jane.doe@example.org")));
    }

    @Test
    void testHasId2() {
        Email id = new Email("jane.doe@example.org");
        assertFalse((new User(id, new Password("iloveyou"), "Name")).hasId(null));
    }

    @Test
    void testHasPassword() {
        Email id = new Email("jane.doe@example.org");
        assertFalse((new User(id, new Password("iloveyou"), "Name")).hasPassword("Pwd"));
    }

    @Test
    void testHasPassword2() {
        Email id = new Email("jane.doe@example.org");
        assertFalse((new User(id, new Password("iloveyou"), "Name")).hasPassword(""));
    }

    @Test
    void testHasPassword3() {
        Email id = new Email("jane.doe@example.org");
        assertTrue((new User(id, new Password("42"), "Name")).hasPassword("42"));
    }

    @Test
    void testAddRole() {
        Email id = new Email("jane.doe@example.org");
        User user = new User(id, new Password("iloveyou"), "Name");
        assertTrue(user.addRole(new UserRole("42", "The characteristics of someone or something")));
        assertEquals(1, user.getRoles().size());
    }

    @Test
    void testAddRole2() {
        Email id = new Email("jane.doe@example.org");

        User user = new User(id, new Password("iloveyou"), "Name");
        user.addRole(new UserRole("42", "The characteristics of someone or something"));
        assertFalse(user.addRole(new UserRole("42", "The characteristics of someone or something")));
        assertEquals(1, user.getRoles().size());
    }

    @Test
    void testAddRole3() {
        Email id = new Email("jane.doe@example.org");
        assertFalse((new User(id, new Password("iloveyou"), "Name")).addRole(null));
    }

    @Test
    void testRemoveRole() {
        Email id = new Email("jane.doe@example.org");
        User user = new User(id, new Password("iloveyou"), "Name");
        assertFalse(user.removeRole(new UserRole("42", "The characteristics of someone or something")));
        assertTrue(user.getRoles().isEmpty());
    }

    @Test
    void testRemoveRole2() {
        Email id = new Email("jane.doe@example.org");

        User user = new User(id, new Password("iloveyou"), "Name");
        user.addRole(new UserRole("42", "The characteristics of someone or something"));
        assertTrue(user.removeRole(new UserRole("42", "The characteristics of someone or something")));
        assertTrue(user.getRoles().isEmpty());
    }

    @Test
    void testRemoveRole3() {
        Email id = new Email("jane.doe@example.org");
        assertFalse((new User(id, new Password("iloveyou"), "Name")).removeRole(null));
    }

    @Test
    void testHasRole() {
        Email id = new Email("jane.doe@example.org");
        assertFalse((new User(id, new Password("iloveyou"), "Name")).hasRole("42"));
    }

    @Test
    void testHasRole2() {
        Email id = new Email("jane.doe@example.org");

        User user = new User(id, new Password("iloveyou"), "Name");
        user.addRole(new UserRole("42", "The characteristics of someone or something"));
        assertTrue(user.hasRole("42"));
    }

    @Test
    void testHasRole3() {
        Email id = new Email("jane.doe@example.org");

        User user = new User(id, new Password("iloveyou"), "Name");
        user.addRole(new UserRole("Id", "The characteristics of someone or something"));
        assertFalse(user.hasRole("42"));
    }

    @Test
    void testHasRole4() {
        Email id = new Email("jane.doe@example.org");

        User user = new User(id, new Password("iloveyou"), "Name");
        user.addRole(new UserRole("42", "The characteristics of someone or something"));
        assertFalse(user.hasRole(""));
    }

    @Test
    void testHasRole5() {
        Email id = new Email("jane.doe@example.org");
        User user = new User(id, new Password("iloveyou"), "Name");
        assertFalse(user.hasRole(new UserRole("42", "The characteristics of someone or something")));
    }

    @Test
    void testHasRole6() {
        Email id = new Email("jane.doe@example.org");

        User user = new User(id, new Password("iloveyou"), "Name");
        user.addRole(new UserRole("42", "The characteristics of someone or something"));
        assertTrue(user.hasRole(new UserRole("42", "The characteristics of someone or something")));
    }

    @Test
    void testHasRole7() {
        Email id = new Email("jane.doe@example.org");
        assertFalse((new User(id, new Password("iloveyou"), "Name")).hasRole((UserRole) null));
    }

    @Test
    void testGetRoles() {
        Email id = new Email("jane.doe@example.org");
        assertTrue((new User(id, new Password("iloveyou"), "Name")).getRoles().isEmpty());
    }

    @Test
    void testGetRoles2() {
        Email id = new Email("jane.doe@example.org");

        User user = new User(id, new Password("iloveyou"), "Name");
        user.addRole(new UserRole("42", "The characteristics of someone or something"));
        assertEquals(1, user.getRoles().size());
    }

    @Test
    void testEquals() {
        Email id = new Email("jane.doe@example.org");
        assertFalse((new User(id, new Password("iloveyou"), "Name")).equals(null));
    }

    @Test
    void testEquals2() {
        Email id = new Email("jane.doe@example.org");
        assertFalse((new User(id, new Password("iloveyou"), "Name")).equals("Different type to User"));
    }

    @Test
    void testEquals3() {
        Email id = new Email("jane.doe@example.org");
        User user = new User(id, new Password("iloveyou"), "Name");
        assertTrue(user.equals(user));
        int expectedHashCodeResult = user.hashCode();
        assertEquals(expectedHashCodeResult, user.hashCode());
    }

    @Test
    void testEquals4() {
        Email id = new Email("jane.doe@example.org");
        User user = new User(id, new Password("iloveyou"), "Name");
        Email id1 = new Email("jane.doe@example.org");
        User user1 = new User(id1, new Password("iloveyou"), "Name");

        assertTrue(user.equals(user1));
        int expectedHashCodeResult = user.hashCode();
        assertEquals(expectedHashCodeResult, user1.hashCode());
    }

    @Test
    void testEquals5() {
        Email id = mock(Email.class);
        User user = new User(id, new Password("iloveyou"), "Name");
        Email id1 = new Email("jane.doe@example.org");
        assertFalse(user.equals(new User(id1, new Password("iloveyou"), "Name")));
    }
}

