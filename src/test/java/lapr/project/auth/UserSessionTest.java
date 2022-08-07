package lapr.project.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import lapr.project.auth.domain.model.Email;
import lapr.project.auth.domain.model.Password;
import lapr.project.auth.domain.model.User;
import lapr.project.auth.domain.model.UserRole;
import lapr.project.auth.mappers.dto.UserRoleDTO;
import org.junit.jupiter.api.Test;

class UserSessionTest {
    @Test
    void testConstructor() {
        UserSession actualUserSession = new UserSession();
        assertNull(actualUserSession.getUserId());
        assertFalse(actualUserSession.isLoggedIn());
        assertTrue(actualUserSession.getUserRoles().isEmpty());
        assertNull(actualUserSession.getUserName());
    }

    @Test
    void testConstructor2() {
        Email id = new Email("jane.doe@example.org");
        assertTrue((new UserSession(new User(id, new Password("iloveyou"), "Name"))).isLoggedIn());
    }

    @Test
    void testConstructor3() {
        assertThrows(IllegalArgumentException.class, () -> new UserSession(null));
    }

    @Test
    void testIsLoggedIn() {
        assertFalse((new UserSession()).isLoggedIn());
    }

    @Test
    void testIsLoggedIn2() {
        Email id = new Email("jane.doe@example.org");
        assertTrue((new UserSession(new User(id, new Password("iloveyou"), "Name"))).isLoggedIn());
    }

    @Test
    void testIsLoggedInWithRole() {
        assertFalse((new UserSession()).isLoggedInWithRole("42"));
    }

    @Test
    void testIsLoggedInWithRole2() {
        Email id = new Email("jane.doe@example.org");
        assertFalse((new UserSession(new User(id, new Password("iloveyou"), "Name"))).isLoggedInWithRole("42"));
    }

    @Test
    void testIsLoggedInWithRole3() {
        Email id = new Email("jane.doe@example.org");

        User user = new User(id, new Password("iloveyou"), "Name");
        user.addRole(new UserRole("42", "The characteristics of someone or something"));
        assertTrue((new UserSession(user)).isLoggedInWithRole("42"));
    }

    @Test
    void testIsLoggedInWithRole4() {
        Email id = new Email("jane.doe@example.org");

        User user = new User(id, new Password("iloveyou"), "Name");
        user.addRole(new UserRole("Id", "The characteristics of someone or something"));
        assertFalse((new UserSession(user)).isLoggedInWithRole("42"));
    }

    @Test
    void testIsLoggedInWithRole5() {
        Email id = new Email("jane.doe@example.org");

        User user = new User(id, new Password("iloveyou"), "Name");
        user.addRole(new UserRole("42", "The characteristics of someone or something"));
        assertFalse((new UserSession(user)).isLoggedInWithRole(null));
    }

    @Test
    void testGetUserName() {
        assertNull((new UserSession()).getUserName());
    }

    @Test
    void testGetUserName2() {
        Email id = new Email("jane.doe@example.org");
        assertNull((new UserSession(new User(id, new Password("iloveyou"), "Name"))).getUserName());
    }

    @Test
    void testGetUserId() {
        assertNull((new UserSession()).getUserId());
    }

    @Test
    void testGetUserId2() {
        Email email = new Email("jane.doe@example.org");
        assertSame(email, (new UserSession(new User(email, new Password("iloveyou"), "Name"))).getUserId());
    }

    @Test
    void testGetUserRoles() {
        assertTrue((new UserSession()).getUserRoles().isEmpty());
    }

    @Test
    void testGetUserRoles2() {
        Email id = new Email("jane.doe@example.org");
        assertTrue((new UserSession(new User(id, new Password("iloveyou"), "Name"))).getUserRoles().isEmpty());
    }

    @Test
    void testGetUserRoles3() {
        Email id = new Email("jane.doe@example.org");

        User user = new User(id, new Password("iloveyou"), "Name");
        user.addRole(new UserRole("42", "The characteristics of someone or something"));
        List<UserRoleDTO> actualUserRoles = (new UserSession(user)).getUserRoles();
        assertEquals(1, actualUserRoles.size());
        UserRoleDTO getResult = actualUserRoles.get(0);
        assertEquals("The characteristics of someone or something", getResult.getDescription());
        assertEquals("42", getResult.getId());
    }
}

