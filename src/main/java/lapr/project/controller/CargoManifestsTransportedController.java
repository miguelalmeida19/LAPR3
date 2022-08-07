package lapr.project.controller;

import lapr.project.data.CargoManifestsTransported;
import lapr.project.data.DatabaseConnection;

public class CargoManifestsTransportedController {

    private final CargoManifestsTransported c;

    //[US207] As Ship Captain, I want to know how many cargo manifests I have transported during a given year and the
    // average number of containers per manifest.
    //      o Acceptance Criteria [BDDAD]:
    //              Only the cargo manifests of the specified year are considered.
    //              Average containers per cargo manifest are properly computed.

    public CargoManifestsTransportedController(){
        DatabaseConnection data = App.getInstance().getCompany().getDatabaseConnection();
        c = new CargoManifestsTransported(data);
    }

    public int getID(){
        return c.getID(App.getInstance().getCompany().getAuthFacade().getCurrentUserSession().getUserId().getString());
    }

    public void getCargoManifestTransported(String year){
        c.getCargoManifestTransported(year);
    }

    public int getContManifests() {
        return c.getContManifests();
    }

    public double getAverage() {
        return c.getAverage();
    }
}
