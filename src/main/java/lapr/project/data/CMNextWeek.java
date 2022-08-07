package lapr.project.data;

import lapr.project.controller.App;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CMNextWeek {

    public List<String> getWarehouses(int WorkingPort){

        List<String> Map = new ArrayList<>();

        try {

            String sql = "SELECT ID FROM WAREHOUSE WHERE PORTID = " + WorkingPort;

            ResultSet rs = App.getInstance().getCompany().getStatement().executeQuery(sql);

            while (rs.next()) {
                Map.add(rs.getString("ID"));
            }


        } catch (Exception e) {
            throw new IllegalArgumentException("No Warehouses founded for the PortID " + WorkingPort + " :((");
        }
        return Map;
    }

    /**
     * Method that searches which ships are available next Monday and returns a list with the shipsIMO and the port where the ship is located
     */

    public List<String> getMapLoading(int WorkingPort, String nextSunday, String nextSaturday) {

        List<String> Map = new ArrayList<>();

        try {

            String sql = "SELECT * FROM CARGO_MANIFEST" +
                    " INNER JOIN TRIP ON (TRIP.BASE_DATE_TIME = CARGO_MANIFEST.BASE_DATE_TIME AND TRIP.VEHICLEID = CARGO_MANIFEST.VEHICLEID) " +
                    " INNER JOIN CARGO_MANIFEST_CONTAINER ON (CARGO_MANIFEST_CONTAINER.CARGO_MANIFESTID = CARGO_MANIFEST.ID) " +
                    " INNER JOIN CONTAINER ON (CONTAINER.ID = CARGO_MANIFEST_CONTAINER.CONTAINERID) " +
                    "WHERE (CARGO_MANIFEST.BASE_DATE_TIME >= '" + nextSunday + "' AND CARGO_MANIFEST.BASE_DATE_TIME <= '" + nextSaturday + "'" +
                    " AND CARGO_MANIFEST.TYPE = 'TO BE LOADED' AND CARGO_MANIFEST.PORTID = " + WorkingPort + ") ORDER BY TRIP.BASE_DATE_TIME ASC";

            ResultSet rs = App.getInstance().getCompany().getStatement().executeQuery(sql);

            while (rs.next()) {
                Map.add(rs.getString("BASE_DATE_TIME") + "#" + rs.getInt("VEHICLEID") + "#" + rs.getString("ARRIVAL") + "#" + rs.getInt("CONTAINERID") + "#" + rs.getString("ISO_CODE") + "#" + rs.getInt("POSX") + "#" + rs.getInt("POSY") + "#" + rs.getInt("POSZ"));
            }


        } catch (Exception e) {
            throw new IllegalArgumentException("Loading map unavailable for next week :((");
        }
        return Map;
    }

    public List<String> getMapUnloading(String Warehouse, String Warehouse2, String nextSunday, String nextSaturday) {

        List<String> Map = new ArrayList<>();

        try {

            String sql = "SELECT * FROM CARGO_MANIFEST" +
                    " INNER JOIN TRIP ON (TRIP.BASE_DATE_TIME = CARGO_MANIFEST.BASE_DATE_TIME AND TRIP.VEHICLEID = CARGO_MANIFEST.VEHICLEID) " +
                    " INNER JOIN CARGO_MANIFEST_CONTAINER ON (CARGO_MANIFEST_CONTAINER.CARGO_MANIFESTID = CARGO_MANIFEST.ID) " +
                    " INNER JOIN CONTAINER ON (CONTAINER.ID = CARGO_MANIFEST_CONTAINER.CONTAINERID) " +
                    "WHERE (CARGO_MANIFEST.BASE_DATE_TIME >= '" + nextSunday + "' AND CARGO_MANIFEST.BASE_DATE_TIME <= '" + nextSaturday + "'" +
                    " AND CARGO_MANIFEST.TYPE = 'TO BE OFFLOADED' AND (CARGO_MANIFEST.WAREHOUSEID = '" + Warehouse + "' OR CARGO_MANIFEST.WAREHOUSEID = '" + Warehouse2 + "')) " +
                    "ORDER BY TRIP.BASE_DATE_TIME ASC";

            ResultSet rs = App.getInstance().getCompany().getStatement().executeQuery(sql);


            while (rs.next()) {
                Map.add(rs.getString("BASE_DATE_TIME") + "#" + rs.getInt("VEHICLEID") + "#" + rs.getString("ARRIVAL") + "#" + rs.getInt("CONTAINERID") + "#" + rs.getString("ISO_CODE")+ "#" + rs.getInt("POSX") + "#" + rs.getInt("POSY") + "#" + rs.getInt("POSZ"));
            }

        } catch (Exception e) {
            throw new IllegalArgumentException("Offloading map unavailable for next week :((");
        }
        return Map;
    }
}