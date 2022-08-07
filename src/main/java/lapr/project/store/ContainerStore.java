package lapr.project.store;

import lapr.project.controller.App;
import lapr.project.data.DatabaseConnection;
import lapr.project.model.Container;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ContainerStore {
    private final List<Container> containerList;
    /**
     *Constructor
     */
    public ContainerStore(){
        containerList = new ArrayList<>();
    }
    public List<Container> getContainerList() {
        return containerList;
    }

    /**
     * Method that gets the situation of a specific container using its id.
     * @param containerID id of the container
     * @return the status of the container
     * @throws SQLException error related to sql queries.
     */
    public String getContainerStatus( String containerID, DatabaseConnection databaseConnection) throws SQLException{
        String email;
        try {
            email = App.getInstance().getCompany().getAuthFacade().getCurrentUserSession().getUserId().getString();
        }catch(Exception e){
            email = "email";
        }
        int controll = clientContainerVerification(containerID, email,  databaseConnection.getConnection()); //validation
        if(controll == 10 || controll == 11) {
            throwexception(controll);
        }

        List<String> cargos_manifestid = getCargoManifestIDs(containerID, databaseConnection);

        //gets the most recent cargo manifest
        String id = getTheMostRecentCargoManifest(cargos_manifestid,databaseConnection);


        return getTypeOfManifest(id,databaseConnection);
    }

    /**
     * Throws an exception with the code and the relevant message
     * @param code code
     */
    private void throwexception(int code){
        if(code == 10){
            throw new IllegalArgumentException("10 – invalid container id" );
        }
        if(code == 11){
            throw new IllegalArgumentException("11 – container is not leased by client");

        }
    }

    /**
     * Verifies if the container exists and if the user owns that container
     * @param containerID id of the container
     * @param clientEmail client registration email
     * @param conn connection to the database
     * @return the code from the database
     */
    private int clientContainerVerification(String containerID, String clientEmail, Connection conn) throws SQLException {
        CallableStatement cstmt = conn.prepareCall("{? = call iscontainerclient(?,?)}");
        cstmt.registerOutParameter(1, Types.INTEGER);
        cstmt.setString(2, clientEmail);
        cstmt.setInt(3, Integer.parseInt(containerID));
        cstmt.executeUpdate();
        return cstmt.getInt(1);
    }

    /**
     * Executes a SQL query using a prepared statement.
     * @param query query to run.
     * @return the result set with the values.
     * @throws SQLException error related to the database.
     */
    private ResultSet executeSQLQuery(String query, DatabaseConnection databaseConnection) throws SQLException {
        // i use a preparedstatement because it's faster then the normal executeQuery.
        PreparedStatement pStatement =databaseConnection.getConnection().prepareStatement(query);
        return pStatement.executeQuery(query);
    }

    /**
     * Gets all of the cargo manifests that has the container.
     * @param containerID id of the container to look for.
     * @return a list with all the manifests.
     * @throws SQLException error related to the database.
     */
    public List<String> getCargoManifestIDs(String containerID, DatabaseConnection databaseConnection) throws SQLException {
        List<String> cargos_manifestid = new ArrayList<>();

        String query = "SELECT cargo_manifestid FROM cargo_manifest_container WHERE containerid =" + containerID;
        ResultSet rs = executeSQLQuery(query,databaseConnection);
        boolean hasNext = rs.next();

        if (!hasNext) throw new SQLException("The database don't have a container with this ID.");
        while (hasNext) {
            cargos_manifestid.add(rs.getString("cargo_manifestid"));
            hasNext = rs.next();
        }
        return cargos_manifestid;
    }

    /**
     * From a list with multiple cargo manifests gets the most recent one.
     * @param cargos_manifestid list with the cargo manifests.
     * @return the id of the most recent cargo manifest.
     * @throws SQLException error related to the database.
     */
    public String getTheMostRecentCargoManifest(List<String> cargos_manifestid, DatabaseConnection databaseConnection) throws SQLException {
        LocalDateTime now = LocalDateTime.now();
        //now we will grab the dates from this cargo manifests and get the most recent
        String id;
        LocalDateTime maxDate = null; //the most recent date. at the end is the date of the manifest found.

        //get the date of the first ID. There is only one entry per cargo manifest ID because the cargo manifest is unique
        String query = "SELECT BASE_DATE_TIME FROM CARGO_MANIFEST WHERE ID=" + cargos_manifestid.get(0);
        ResultSet rs = executeSQLQuery(query,databaseConnection);
        DateTimeFormatter ft = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        rs.next();
        //the most recent id is the first. if its not it will be replaced ahead
        id = cargos_manifestid.get(0);

        //this loop gets the most recent cargo manifest.
        for (int n = 0; n < cargos_manifestid.size(); n++) {
            query = "SELECT BASE_DATE_TIME FROM CARGO_MANIFEST WHERE ID=" + cargos_manifestid.get(n);

            rs = executeSQLQuery(query,databaseConnection) ;      //needed to get the value
            rs.next();
            String dateTemp = rs.getString("BASE_DATE_TIME").replace("T", " ");  //the string from database has a random letter T.

            //we cannot have cargos_manifest from the future.... i mean, time travel is not possible yet
            if(maxDate == null && LocalDateTime.parse(dateTemp, ft).compareTo(now) <=0){
                maxDate = LocalDateTime.parse(dateTemp, ft);
                id = cargos_manifestid.get(n);
            }else if(maxDate != null && maxDate.compareTo(LocalDateTime.parse(dateTemp, ft)) < 0 && LocalDateTime.parse(dateTemp, ft).compareTo(now) <=0) {
                maxDate = LocalDateTime.parse(dateTemp, ft);
                id = cargos_manifestid.get(n);
            }
        }

        if(id.equals("")) throw new SQLException("The container with the id provided is not on a ship or in a port");
        return id;

    }

    /**
     * Method that generates the string that gives the location of the container
     * @param manifestid manifest id
     * @param databaseConnection connection to the database
     * @return the string with the location of the container
     * @throws SQLException exception related to the database
     */
    private String toBeOffLoadedManifest(String manifestid,DatabaseConnection databaseConnection) throws SQLException {
        //we need to get the id from port / warehouse
        String query = "SELECT PORTID, WAREHOUSEID FROM CARGO_MANIFEST WHERE ID="+manifestid;
        PreparedStatement pStatement =databaseConnection.getConnection().prepareStatement(query);
        ResultSet rs =  pStatement.executeQuery(query);
        rs.next();
        String portID = rs.getString("PORTID");
        String warehouseID = rs.getString("WAREHOUSEID");
        if(warehouseID == null){
            query = "SELECT NAME FROM PORT WHERE ID="+portID;
            rs =executeSQLQuery(query,databaseConnection);
            rs.next(); //needed to get the value
            String portName = rs.getString("NAME");
            return "PORT, "+portName;
        }else{
            query = "SELECT ID FROM WAREHOUSE WHERE ID='"+warehouseID+"'";
            rs =executeSQLQuery(query,databaseConnection);
            rs.next(); //needed to get the value
            String warehousename = rs.getString("ID");
            return "WAREHOUSE, "+warehousename;
        }

    }

    private String toBeLoadedManifest(String manifestid,DatabaseConnection databaseConnection) throws SQLException {
        //is on ship / truck
        String query = "SELECT vehicleid FROM CARGO_MANIFEST WHERE ID="+manifestid;
        PreparedStatement pStatement =databaseConnection.getConnection().prepareStatement(query);
        ResultSet rs =  pStatement.executeQuery(query);
        rs.next();
        String vehicleid = rs.getString("VEHICLEID");
        query = "SELECT type FROM VEHICLE WHERE ID="+vehicleid;
        pStatement =databaseConnection.getConnection().prepareStatement(query);
        rs =  pStatement.executeQuery(query);
        rs.next();
        String typevehicle = rs.getString("TYPE");
        if(typevehicle.equals("SHIP")){
            query = "SELECT name FROM ship WHERE VEHICLEID="+vehicleid;
            pStatement =databaseConnection.getConnection().prepareStatement(query);
            rs =  pStatement.executeQuery(query);
            rs.next();
            return "SHIP, "+rs.getString("NAME");
        }else{
            query = "SELECT ID FROM truck WHERE VEHICLEID="+vehicleid;
            pStatement =databaseConnection.getConnection().prepareStatement(query);
            rs =  pStatement.executeQuery(query);
            rs.next();
            return "TRUCK, "+rs.getString("ID");
        }
    }
    /**
     * Gets the type of the manifest (loading / unloading). It also generates the output of the program based with the type of the manifest.
     * @param id id of the cargo manifest.
     * @return the string with the output of the program.
     * @throws SQLException error related to the database.
     */
    public String getTypeOfManifest(String id, DatabaseConnection databaseConnection) throws SQLException {
        //get the type of the manifest (load or unload)
        String query = "SELECT TYPE FROM CARGO_MANIFEST WHERE ID=" + id;
        ResultSet rs = executeSQLQuery(query,databaseConnection);
        rs.next(); //needed to get the value

        String type = rs.getString("TYPE");
        if (type.equals("TO BE OFFLOADED")) {
            return toBeOffLoadedManifest(id,databaseConnection);
        } else {
            return toBeLoadedManifest(id, databaseConnection);
        }
    }
}
