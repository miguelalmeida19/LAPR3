package lapr.project.controller;

import lapr.project.data.ImportFromDataBase;
import lapr.project.model.Container;
import lapr.project.model.Ship;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class ContainersController {

    private final ShipController shipController = new ShipController();

    /**
     * This method gets an hashmap with containers from a state and a ship
     * @return hashmap with ship and a list of containers
     */
    public HashMap<String, List<Container>> getContainersFromStateAndShip(String code, String state, LocalDateTime actualDate){
        Ship ship = shipController.getShip(code);
        String shipIMO = ship.getIMO();
        return ImportFromDataBase.getContainersByShipPortType(shipIMO, actualDate, state);
    }

    /**
     * @return The ship where the captain is
     */
    public static String getShipForCaptain(){
        try{
            String email = App.getInstance().getCompany().getAuthFacade().getCurrentUserSession().getUserId().getString();
            String sql = "SELECT * FROM USER_ACCOUNT WHERE EMAIL = '" + email + "'";
            ResultSet rs = App.getInstance().getCompany().getStatement().executeQuery(sql);
            if (rs.next()){
                int employeeId = rs.getInt("employeeid");
                sql = "SELECT * FROM EMPLOYEE WHERE ID = " + employeeId + "";
                rs = App.getInstance().getCompany().getStatement().executeQuery(sql);
                if (rs.next()){
                    return rs.getString("SHIPIMO");
                }
            }
        }catch (Exception ignore){
            //Exception ignore
        }
        return "";
    }
}