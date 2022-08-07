package lapr.project.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static lapr.project.ui.console.console_utils.Utils.print;

/**
 * This is the Class for Audit Trails
 */
public class AuditTrails {

    private final DatabaseConnection data;
    private final  List<String> list = new ArrayList<>();

    /**
     * Constructor
     * @param data - DatabaseConnection
     */
    public AuditTrails(DatabaseConnection data){
        this.data = data;
    }


    /**
     * Method that returns a list of all messages present in the audit trails table for a given CargoManifestID and ContainerID
     */
    public List<String> getAuditTrails(int cargoManifestId, int containerId){

        try {

            String sql = "SELECT * FROM AUDIT_TRAIL WHERE (CARGO_MANIFESTID = " + cargoManifestId + " AND CONTAINERID = " + containerId + ") ORDER BY BASE_DATE_TIME ASC";
            ResultSet rs = executeSQLQuery(sql, data);

            if (rs != null) {
                while (rs.next()) {
                   list.add( "Cargo Manifest ID: " + rs.getInt("CARGO_MANIFESTID")
                           + "Container ID: " + rs.getInt("CONTAINERID")
                           + "Date: " + rs.getString("BASE_DATE_TIME")
                           + "Operation: " + rs.getString("OPERATION")
                           + "User Email: " + rs.getString("USEREMAIL"));
                }
            } else {
                throw new IllegalArgumentException("No changes were found for Container ID " + containerId + " in Cargo Manifest ID " + cargoManifestId +" !");
            }

        } catch (Exception e) {
            print(e.getMessage());
        }
        return list;
    }

    /**
     * This method executes sql queries
     */
    public static ResultSet executeSQLQuery(String sql, DatabaseConnection data) throws SQLException {
        PreparedStatement p = data.getConnection().prepareStatement(sql);
        return p.executeQuery(sql);
    }
}
