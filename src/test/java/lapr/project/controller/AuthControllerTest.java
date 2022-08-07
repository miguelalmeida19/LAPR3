package lapr.project.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthControllerTest {
    @Test
    void testConstructor() {
        assertNotNull((new AuthController()).getUserRoles());
    }

    @Test
    void testDoLogin() {
        assertTrue((new AuthController()).doLogin("rubenex@dei.com", "tiagovskii"));
    }

    @Test
    void testGetUserRoles() {
        App.getInstance().getCompany().getAuthFacade().doLogin("rubenex@dei.com", "tiagovskii");
        assertNotNull(App.getInstance().getCompany().getAuthFacade().getCurrentUserSession().getUserRoles());
    }

    @Test
    void testDoLogout() {
        AuthController authController = new AuthController();
        authController.doLogout();
        assertNull(authController.getUserRoles());
    }
}

