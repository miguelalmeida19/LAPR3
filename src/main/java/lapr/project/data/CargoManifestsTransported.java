package lapr.project.data;

import lapr.project.model.Trip;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static lapr.project.ui.console.console_utils.Utils.print;

public class CargoManifestsTransported {

    private final DatabaseConnection data;
    int id;
    static String ano;
    int contManifests = 0;
    static double average = 0;
    List<Trip> trips;

    /**
     * Constructor
     */
    public CargoManifestsTransported(DatabaseConnection data) {
        this.data = data;
    }

    /**
     * Method used to fetch the employee's id using the email he logged in to the application with
     */
    public int getID(String email) {

        try {

            String sql = "SELECT EMPLOYEEID FROM USER_ACCOUNT WHERE EMAIL = '" + email + "'";

            ResultSet rs = executeSQLQuery(sql, data);

            if (rs != null) {
                rs.next();
                id = rs.getInt("EMPLOYEEID");

            } else {
                throw new IllegalArgumentException("No trips found for " + id + " Employee ID :((");
            }

        } catch (Exception e) {
            print(e.getMessage());
        }
        return id;
    }

    /**
     * Method that will search in the database for the trips of the ship capitan logged in in the application for a given year introduced by him
     */
    public void getCargoManifestTransported(String ano) {

        trips = new ArrayList<>();

        CargoManifestsTransported.ano = ano;

        try {

            String sql = "SELECT SHIPIMO, BASE_DATE_TIME FROM TRIP WHERE (EMPLOYEEID = " + id + " AND SUBSTR(BASE_DATE_TIME, 1, 4) = '" + ano + "')";

            ResultSet rs = executeSQLQuery(sql, data);

            if (rs != null) {
                while (rs.next()) {
                    Trip trip = new Trip(
                            rs.getString("SHIPIMO"),
                            rs.getString("BASE_DATE_TIME")
                    );

                    trips.add(trip);
                }
            } else {
                throw new IllegalArgumentException("No trips found for " + id + " Employee ID in " + ano + " year :((");
            }

            if (!trips.isEmpty()) {
                getAmount();
            }

        } catch (Exception e) {
            print(e.getMessage());
        }
    }

    /**
     * Method for calculating the average number of containers per cargo manifest
     *
     * @throws SQLException .
     */

    public void getAmount() throws SQLException {

        //Number of Containers transported during given year
        contManifests = 0;
        int contContainers = 0;

        for (Trip trip : trips) {
            contManifests++;

            String sql = "SELECT ID FROM CARGO_MANIFEST WHERE (SHIPIMO = '" + trip.getShipIMO() + "' AND SUBSTR(BASE_DATE_TIME, 1, 16) = '" + trip.getBase_date_time() + "')";
            ResultSet rs1 = executeSQLQuery(sql, data);

            if (rs1.next()) {

                sql = "SELECT * FROM CARGO_MANIFEST_CONTAINER WHERE CARGO_MANIFESTID = " + rs1.getInt("ID");
                ResultSet rs2 = executeSQLQuery(sql, data);

                while (rs2.next()) {
                    contContainers++;
                }

            } else {
                throw new IllegalArgumentException("No cargo manifests found for " + ano + " year :((");
            }
        }
        average = (double) contContainers / contManifests;
    }

    /**
     * @return - Returns the amount of cargo manifests found
     */
    public int getContManifests() {
        return contManifests;
    }

    /**
     * @return - Returns the average number of containers per cargo manifest
     */
    public double getAverage() {
        return average;
    }

    public static ResultSet executeSQLQuery(String sql, DatabaseConnection data) throws SQLException {
        try (PreparedStatement p = data.getConnection().prepareStatement(sql)) {

            return p.executeQuery(sql);
        }
    }
}
