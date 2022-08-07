package lapr.project.controller;
import lapr.project.data.DatabaseConnection;
import lapr.project.data.ShipsOnMonday;
import java.util.List;

public class ShipsOnMondayController {

    private final ShipsOnMonday s;

    /**
     * Constructor
     */
    public ShipsOnMondayController(){
        DatabaseConnection data = App.getInstance().getCompany().getDatabaseConnection();
        s = new ShipsOnMonday(data);
    }

    //[US210] As Traffic manager, I need to know which ships will be available on Monday
    //next week and their location.
    //      o Acceptance Criteria [BDDAD]:
    //              Monday next week is properly identified.
    //              Only available ships are returned.
    //              All available ships are returned.

    /**
     * Method that searches which ships are available next Monday and returns a list with the shipsIMO and the port where the ship is located
     */
    public List<String> getBoats(){
        return s.getBoats();
    }
}
