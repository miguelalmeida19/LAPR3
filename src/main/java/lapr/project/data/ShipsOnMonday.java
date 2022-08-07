package lapr.project.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static lapr.project.ui.console.console_utils.Utils.print;

public class ShipsOnMonday {

    private final DatabaseConnection data;

    /**
     * Constructor
     * @param data - DatabaseConnection
     */
    public ShipsOnMonday(DatabaseConnection data){
        this.data = data;
    }

    private static final List<String> Available = new ArrayList<>();
    private static final  List<String> imo = new ArrayList<>();

    //[US210] As Traffic manager, I need to know which ships will be available on Monday
    //next week and their location.
    //      o Acceptance Criteria [BDDAD]:
    //              Monday next week is properly identified.
    //              Only available ships are returned.
    //              All available ships are returned.


    /**
     * Method that returns the current date
     * @return - Current Date
     */

    public static LocalDate getDate() {
        return LocalDate.now();
    }

    /**
     * Method that returns the date of the next Monday
     * @return - Next Monday Date
     */

    public static LocalDate nextMondayDate() {
        return getDate().plus((8 - getDate().getDayOfWeek().getValue()), ChronoUnit.DAYS);
    }

    /**
     * Method that goes to the trip table and stores in a list all the shipIMO it finds
     */
    public void getIMO() {

        try {
            String sql = "SELECT SHIPIMO FROM TRIP";

            ResultSet rs = executeSQLQuery(sql,data);

            while (rs.next()) {
                imo.add(rs.getString("SHIPIMO"));
            }
        } catch (Exception e) {
            print(e.getMessage());
        }
    }

    /**
     * Method that searches which ships are available next Monday and returns a list with the shipsIMO and the port where the ship is located
     */

    public List<String> getBoats(){

        try{
            getIMO();

            for (String IMO : imo) {

                String sql = "SELECT * FROM CARGO_MANIFEST WHERE (SHIPIMO = '" + IMO + "' AND SUBSTR(BASE_DATE_TIME, 1, 10) < '" + nextMondayDate() + "') ORDER BY BASE_DATE_TIME DESC FETCH FIRST 1 ROWS ONLY";
                ResultSet rs = executeSQLQuery(sql,data);

                if (rs != null) {
                    while (rs.next()) {
                        if ("TO BE OFFLOADED".equals(rs.getString("TYPE"))) {
                            Available.add("\n[*] The boat with the ShipIMO " + rs.getString("SHIPIMO") + " is available in the " + rs.getInt("PORTID") + " Port");
                        }
                    }
                }
            }

        } catch (Exception e) {
            print(e.getMessage());
        }

        if (!Available.isEmpty()) {
            return Available;
        } else {
            throw new IllegalArgumentException("No boats found for " + nextMondayDate() + " :((");
        }
    }

    public static ResultSet executeSQLQuery(String sql, DatabaseConnection data) throws SQLException {
        try (PreparedStatement p = data.getConnection().prepareStatement(sql)) {
            return p.executeQuery(sql);
        }
    }
}
