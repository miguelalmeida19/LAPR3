package lapr.project.model;

import lapr.project.controller.App;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CompanyTest {

    @Test
    public void getAuthFacadeNotNull() {
        assertNotEquals(App.getInstance().getCompany().getAuthFacade(), null);
    }

    @Test
    public void testPersistence(){
        Company company = new Company();
        assertFalse(company.getShipStore().getShipList().isEmpty());
        assertFalse(company.getPortStore().getPortList().isEmpty());
        assertFalse(company.getAuthFacade().getUsers().getStore().isEmpty());
    }
}