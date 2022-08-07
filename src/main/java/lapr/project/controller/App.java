package lapr.project.controller;

import lapr.project.auth.AuthFacade;
import lapr.project.auth.UserSession;
import lapr.project.auth.domain.shared.Constants;
import lapr.project.data.ConnectionFactory;
import lapr.project.data.DatabaseConnection;
import lapr.project.model.Company;

import java.io.IOException;

public class App {
    private final Company company;
    private final AuthFacade authFacade;

    private App(){
        this.company = new Company();
        this.authFacade = this.company.getAuthFacade();
    }


    private static App singleton = null;

    public static App getInstance() {
        if(singleton == null)
        {
            synchronized(App.class)
            {
                singleton = new App();
            }
        }
        return singleton;
    }

    public Company getCompany() {
        return company;
    }

    public UserSession getCurrentUserSession()
    {
        return this.authFacade.getCurrentUserSession();
    }

    /**
     * This method does the login
     */
    public boolean doLogin(String email, String pwd)
    {
        return this.authFacade.doLogin(email,pwd).isLoggedIn();
    }

    /**
     * This method logs out
     */
    public void doLogout()
    {
        this.authFacade.doLogout();
    }
}