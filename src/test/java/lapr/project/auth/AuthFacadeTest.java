package lapr.project.auth;

import lapr.project.auth.domain.shared.Constants;
import lapr.project.controller.App;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthFacadeTest {
    @Test
    void testConstructor() {
        AuthFacade actualAuthFacade = new AuthFacade();
        UserSession expectedUserSession = actualAuthFacade.getCurrentUserSession();
        assertSame(expectedUserSession, actualAuthFacade.getUserSession());
        assertFalse(App.getInstance().getCompany().getAuthFacade().getRoles().exists(Constants.ROLE_CLIENT));
    }

    @Test
    void testConstructor2() {
        AuthFacade actualAuthFacade = new AuthFacade();
        UserSession currentUserSession = actualAuthFacade.getCurrentUserSession();
        assertSame(currentUserSession, actualAuthFacade.getUserSession());
        assertNull(currentUserSession.getUserName());
    }

    @Test
    void testAddUserRole() {
        assertTrue((new AuthFacade()).addUserRole("42", "The characteristics of someone or something"));
    }

    @Test
    void testAddUserRole2() {
        AuthFacade authFacade = new AuthFacade();
        authFacade.addUserRole("42", "The characteristics of someone or something");
        assertFalse(authFacade.addUserRole("42", "The characteristics of someone or something"));
    }

    @Test
    void testAddUser() {
        assertTrue((new AuthFacade()).addUser("Name", "jane.doe@example.org", "Pwd"));
    }

    @Test
    void testAddUser2() {
        AuthFacade authFacade = new AuthFacade();
        authFacade.addUserWithRoles("U.U.U@U.U.U.UUUU", "jane.doe@example.org", "U.U.U@U.U.U.UUUU", new String[]{"42"});
        assertFalse(authFacade.addUser("Name", "jane.doe@example.org", "Pwd"));
    }

    @Test
    void testAddUserWithRole() {
        assertFalse((new AuthFacade()).addUserWithRole("Name", "jane.doe@example.org", "Pwd", "42"));
    }

    @Test
    void testAddUserWithRole2() {
        AuthFacade authFacade = new AuthFacade();
        authFacade.addUserRole("42", "The characteristics of someone or something");
        assertTrue(authFacade.addUserWithRole("Name", "jane.doe@example.org", "Pwd", "42"));
    }

    @Test
    void testAddUserWithRole3() {
        AuthFacade authFacade = new AuthFacade();
        authFacade.addUserWithRoles("U.U.U@U.U.U.UUUU", "jane.doe@example.org", "U.U.U@U.U.U.UUUU", new String[]{"42"});
        authFacade.addUserRole("42", "The characteristics of someone or something");
        assertFalse(authFacade.addUserWithRole("Name", "jane.doe@example.org", "Pwd", "42"));
    }

    @Test
    void testAddUserWithRole4() {
        AuthFacade authFacade = new AuthFacade();
        authFacade.addUserRole("U.U.U@U.U.U.UUUU", "The characteristics of someone or something");
        assertFalse(authFacade.addUserWithRole("Name", "jane.doe@example.org", "Pwd", "42"));
    }

    @Test
    void testAddUserWithRoles() {
        assertTrue((new AuthFacade()).addUserWithRoles("Name", "jane.doe@example.org", "Pwd", new String[]{"42"}));
    }

    @Test
    void testAddUserWithRoles2() {
        AuthFacade authFacade = new AuthFacade();
        authFacade.addUserWithRoles("U.U.U@U.U.U.UUUU", "jane.doe@example.org", "U.U.U@U.U.U.UUUU", new String[]{"42"});
        assertFalse(authFacade.addUserWithRoles("Name", "jane.doe@example.org", "Pwd", new String[]{"42"}));
    }

    @Test
    void testAddUserWithRoles3() {
        AuthFacade authFacade = new AuthFacade();
        authFacade.addUserRole("42", "The characteristics of someone or something");
        assertTrue(authFacade.addUserWithRoles("Name", "jane.doe@example.org", "Pwd", new String[]{"42"}));
    }

    @Test
    void testAddUserWithRoles4() {
        AuthFacade authFacade = new AuthFacade();
        authFacade.addUserRole("U.U.U@U.U.U.UUUU", "The characteristics of someone or something");
        assertTrue(authFacade.addUserWithRoles("Name", "jane.doe@example.org", "Pwd", new String[]{"42"}));
    }

    @Test
    void testExistsUser() {
        assertFalse((new AuthFacade()).existsUser("jane.doe@example.org"));
    }

    @Test
    void testExistsUser2() {
        AuthFacade authFacade = new AuthFacade();
        authFacade.addUserWithRoles("U.U.U@U.U.U.UUUU", "jane.doe@example.org", "U.U.U@U.U.U.UUUU", new String[]{"42"});
        assertTrue(authFacade.existsUser("jane.doe@example.org"));
    }

    @Test
    void testExistsUser3() {
        AuthFacade authFacade = new AuthFacade();
        authFacade.addUserWithRoles("U.U.U@U.U.U.UUUU", "U.U.U@U.U.U.UUUU", "U.U.U@U.U.U.UUUU", new String[]{"42"});
        assertFalse(authFacade.existsUser("jane.doe@example.org"));
    }

    @Test
    void testDoLogin() {
        (new AuthFacade()).doLogin("jane.doe@example.org", "iloveyou");
    }

    @Test
    void testDoLogin2() {


        AuthFacade authFacade = new AuthFacade();
        authFacade.addUserWithRoles("U.U.U@U.U.U.UUUU", "jane.doe@example.org", "U.U.U@U.U.U.UUUU", new String[]{"42"});
        authFacade.doLogin("jane.doe@example.org", "iloveyou");
    }

    @Test
    void testDoLogin3() {


        AuthFacade authFacade = new AuthFacade();
        authFacade.addUserWithRoles("U.U.U@U.U.U.UUUU", "U.U.U@U.U.U.UUUU", "U.U.U@U.U.U.UUUU", new String[]{"42"});
        authFacade.doLogin("jane.doe@example.org", "iloveyou");
    }

    @Test
    void testDoLogout() {
        AuthFacade authFacade = new AuthFacade();
        authFacade.doLogout();
        assertNull(authFacade.getCurrentUserSession().getUserName());
    }
}

