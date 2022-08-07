package lapr.project.controller;

import lapr.project.auth.mappers.dto.UserRoleDTO;

import java.util.List;
/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class AuthController {

    private final App app;

    /**
     * Controller
     */
    public AuthController()
    {
        this.app = App.getInstance();
    }

    /**
     * This method does the login
     */
    public boolean doLogin(String email, String pwd)
    {
        try {
            return this.app.doLogin(email, pwd);
        } catch(IllegalArgumentException ex)
        {
            return false;
        }
    }

    /**
     * This method gets the user roles
     */
    public List<UserRoleDTO> getUserRoles()
    {
        if (this.app.getCurrentUserSession().isLoggedIn())
        {
            return this.app.getCurrentUserSession().getUserRoles();
        }
        return null;
    }

    /**
     * This method does the logout
     */
    public void doLogout()
    {
        this.app.doLogout();
    }
}

