package lapr.project.data;

import lapr.project.model.Message;
import lapr.project.model.Port;
import lapr.project.model.Ship;
import lapr.project.ui.console.console_utils.TextUtils;

import java.util.List;

import static lapr.project.ui.console.console_utils.Utils.print;

public class ExportToDatabase {

    static final String BEGIN = "BEGIN \n";
    static final String END = "\n END;";
    static final String VALUES = " VALUES ";

    /**
     * This method uploads the ships to the database
     */
    public void uploadShipToDatabase(Ship ship, int shipsCount, int messagesCount){
        String query;
        shipsCount++;
        query = "INSERT INTO VEHICLE (TYPE, CAPACITY, NUMBER_OF_GENERATORS, GENERATORS_POWER)" + VALUES
                + "('" + "SHIP" +  "'," + 100 + "," + 10 + "," + 3000 + ");";
        print(query);

        query = "INSERT INTO SHIP (IMO,MMSI,CALL_SIGN,NAME,VESSEL_TYPE,LENGTH,WIDTH,DRAFT, VEHICLEID) " + VALUES +
                "('" + ship.getIMO() + "','" + ship.getMMSI() + "','" + ship.getCallsign() + "','" + ship.getName() + "','" + ship.getVesselType() + "'," +
                ship.getLength() + "," + ship.getWidth() + "," + ship.getDraft() + "," + shipsCount + ");";

        print(query);

        StringBuilder queryMessages = new StringBuilder(BEGIN);
        for (Message m : ship.getAllMessages()) {
            queryMessages.append(m.uploadToDatabase(ship.getIMO()));
            messagesCount++;
        }
        queryMessages.append(END);
        print(queryMessages.toString());
    }

    /**
     * This method uploads the ports to the database
     */
    public void uploadPortToDatabase(Port port) {
        String query;
        query = "INSERT INTO PORT (ID,NAME,CONTINENT,COUNTRY_NAME,LATITUDE,LONGITUDE) " + VALUES +
                "(" + port.getCode() + ",'" + port.getPort() + "','" + port.getContinent() + "','" + port.getCountry() + "'," + port.getLatitude() + "," +
                port.getLongitude() + ");";


        print(query);
    }

    /**
     * Exports ship to the database
     */
    public void exportShipsToDatabase(List<Ship> ships) {
        int shipsCount = 0;
        int messagesCount = 0;
        try {
            for (Ship s : ships) {
                uploadShipToDatabase(s, shipsCount, messagesCount);
                messagesCount += s.getAllMessages().size();
                shipsCount++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Exports ports to the database
     */
    public void exportPortsToDatabase(List<Port> ports) {
        print(TextUtils.ANSI_GREEN + "\nImporting Ports to Database . . ." + TextUtils.ANSI_BLUE);
        for (Port p : ports) {
            uploadPortToDatabase(p);
        }
    }

    /**
     * Randomizes between 2 numbers
     */
    public static int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }
}

