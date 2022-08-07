package lapr.project.ui.console;

import lapr.project.controller.App;
import lapr.project.data.DatabaseConnection;
import lapr.project.ui.console.console_utils.TextUtils;

import java.sql.*;

import static lapr.project.ui.console.console_utils.Utils.print;

public class CrewUI implements Runnable{
    static final String IPHEN = "  -  ";

    public static void main(String[] args) {
        CrewUI c = new CrewUI();
        c.run();
    }
    @Override
    public void run() {
        try {
            DatabaseConnection databaseConnection = App.getInstance().getCompany().getDatabaseConnection();

            //gets the captain employee id
            String email = App.getInstance().getCompany().getAuthFacade().getCurrentUserSession().getUserId().getString();
            String queryEmployeeID = "SELECT EMPLOYEEID FROM USER_ACCOUNT WHERE EMAIL='" + email + "'";
            ResultSet rst;
            try (Statement statement = databaseConnection.getConnection().createStatement()) {
                rst = statement.executeQuery(queryEmployeeID);
            }
            rst.next();
            int captainID = rst.getInt(1);
            Connection conn = databaseConnection.getConnection();

            ResultSet rs;
            try (Statement s = conn.createStatement()) {
                rs = s.executeQuery("SELECT trip.base_date_time FROM TRIP " +
                        "    INNER JOIN EMPLOYEE " +
                        "    ON EMPLOYEE.ID=trip.employeeid " +
                        "    where employee.id=" + captainID + " ORDER BY trip.base_date_time DESC fetch first 1 row only");
            }
            rs.next();
            String lastTripCaptainDate = rs.getString(1);
            print(lastTripCaptainDate);
            String shipimo;
            try (CallableStatement cstmt = conn.prepareCall("{? = call GETCURRENTSHIPCAPTAIN(?,?)}")) {
                cstmt.registerOutParameter(1, Types.VARCHAR);
                cstmt.setInt(3, captainID);
                cstmt.setString(2, lastTripCaptainDate);
                cstmt.executeUpdate();
                shipimo = cstmt.getString(1);
            }
            print(shipimo);

            String query = "SELECT CONTAINER.ID,  container.temperature,"+
                    "cargo_manifest_container.posx, cargo_manifest_container.posy,"+
                    "cargo_manifest_container.posz, container.payload FROM cargo_manifest INNER JOIN cargo_manifest_container "+
            "ON cargo_manifest_container.cargo_manifestid = CARGO_MANIFEST.ID "+
            "INNER JOIN CONTAINER ON CONTAINER.ID = cargo_manifest_container.CONTAINERID"+
           " where TO_DATE(cargo_manifest.base_date_time, 'YYYY-MM-DD HH24:MI') > TO_DATE('"+lastTripCaptainDate+"', 'YYYY-MM-DD HH24:MI')"+
            "AND TO_DATE(cargo_manifest.base_date_time, 'YYYY-MM-DD HH24:MI') <= SYSDATE"+
            " AND cargo_manifest.type = 'TO BE OFFLOADED'"+
           " AND cargo_manifest.vehicleid=(SELECT trip.vehicleid  FROM TRIP"+
           " INNER JOIN EMPLOYEE"+
            " ON EMPLOYEE.ID=trip.employeeid"+
            " where employee.id="+captainID+" AND trip.base_date_time = '"+lastTripCaptainDate+"' ORDER BY trip.base_date_time DESC fetch first 1 row only)";
            ResultSet rs2;
            try (PreparedStatement pStatement = databaseConnection.getConnection().prepareStatement(query)) {
                rs2 = pStatement.executeQuery(query);
            }
            print("ID   - TEMPERATURE     - POSX     - POSY    -    POSZ          -    PAYLOAD");
            while (rs2.next()) {
                print(rs2.getString("ID") + IPHEN + rs2.getString("TEMPERATURE") + IPHEN + rs2.getString("POSX") + IPHEN + rs2.getString("POSY") + IPHEN + rs2.getString("POSZ") + IPHEN + rs2.getString("PAYLOAD"));
            }
        }catch(Exception e){
            print(TextUtils.ANSI_RED+"[!] "+TextUtils.ANSI_RESET+"Something went wrong. Error code: "+e.getMessage());
        }
    }
}
