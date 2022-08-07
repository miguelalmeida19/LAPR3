package lapr.project.controller;

import lapr.project.data.ExportToDatabase;
import lapr.project.model.*;
import lapr.project.store.ShipStore;
import lapr.project.structures.BST;
import lapr.project.ui.console.console_utils.TextUtils;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

import static lapr.project.ui.console.console_utils.Utils.print;

public class ShipController {
    private final Company company;
    private final BST bstMMSI;
    private final BST bstIMO;
    private final BST bstCallSign;
    private final List<Ship> ships;
    private final ShipStore shipStore;

    /**
     * Constructor
     */
    public ShipController() {
        company = App.getInstance().getCompany();
        bstMMSI = company.getShipStore().getBstMMSI();
        bstIMO = company.getShipStore().getBstIMO();
        bstCallSign = company.getShipStore().getBstCallSign();
        ships = new ArrayList<>();
        shipStore = company.getShipStore();
    }

    //US101

    /**
     * This method imports ships from a file
     *
     */
    public void importShips(String file) {
        ships.clear();
        print("\n" + TextUtils.ANSI_GREEN + "Importing Ships . . ." + TextUtils.ANSI_BLUE + "\n");
        File file1 = new File(file);
        ExportToDatabase exportToDatabase = new ExportToDatabase();
        try {
            Scanner scanner = new Scanner(file1);
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lineArray = line.split(",");

                int mmsi = Integer.parseInt(lineArray[0]);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                LocalDateTime localDateTime = LocalDateTime.parse(lineArray[1], formatter);

                double latitude = Double.parseDouble(lineArray[2]);
                double longitude = Double.parseDouble(lineArray[3]);
                double sog = Double.parseDouble(lineArray[4]);
                double cog = Double.parseDouble(lineArray[5]);
                if (cog < 0 && cog > -359) {
                    cog = cog * (-1);
                }
                if (cog < 359) {
                    double heading = Double.parseDouble(lineArray[6]);
                    String transceiver = (lineArray[15]);
                    if (bstMMSI.search(mmsi) == null || ((Ship) bstMMSI.search(mmsi)).getMMSI() != mmsi) {
                        String name = lineArray[7];
                        String imo = lineArray[8];
                        String callsign = lineArray[9];
                        String vesselType = lineArray[10];
                        double length = Double.parseDouble(lineArray[11]);
                        double width = Double.parseDouble(lineArray[12]);
                        double draft = Double.parseDouble(lineArray[13]);
                        String capacity = lineArray[14];

                        Ship ship = new Ship(mmsi, name, imo, callsign, vesselType, length, width, draft, capacity);
                        ships.add(ship);
                        Message mess = new Message(localDateTime, latitude, longitude, sog, cog, heading, transceiver);
                        ship.addMessage(mess);
                        App.getInstance().getCompany().getShipStore().addShip(ship);

                    } else {
                        Ship ship = (Ship) bstMMSI.search(mmsi);

                        Message mess = new Message(localDateTime, latitude, longitude, sog, cog, heading, transceiver);
                        ship.addMessage(mess);
                    }
                }
            }
            exportToDatabase.exportShipsToDatabase(ships);
        } catch (Exception ignored) {
            //Exception ignored
        }
    }

    /**
     * This method gets the details of a ship, matching to a given code
     *
     * @return String with all details of a ship
     */
    //US102
    public String getDetails(String code) {
        if (Pattern.matches(".*[a-zA-Z]+.*", code) == false) {
            if (code.length() == 9) {
                int mmsi = Integer.parseInt(code);
                return ((Ship) bstMMSI.search(mmsi)).getDetails();
            }
        } else {
            if (code.length() == 10) {
                return ((Ship) bstIMO.search(code)).getDetails();
            } else {
                return ((Ship) bstCallSign.search(code)).getDetails();
            }
        }
        return null;
    }

    /**
     * This method gets a list of files
     *
     * @return list of files
     */
    public List<File> getFiles() {
        File diretory = new File("./files");
        return Arrays.asList(diretory.listFiles());
    }

    /**
     * @return list of ships
     */
    public List<Ship> getShips() {
        return ships;
    }

    /**
     * @return all messages from a ship with a given code
     */
    public TreeSet<Message> getAllMessages(String code) {
        return shipStore.getShip(code).getAllMessages();
    }

    //US106

    /**
     * @return a linkedHashMap with the top n ships in a date period
     */
    public LinkedHashMap<String, List<Ship>> getTopNShips(int n, LocalDateTime start, LocalDateTime end)  {
        return shipStore.getTopNShips(n, start, end);
    }

    //US107

    /**
     * @return AbstractMap with pair of ships
     */
    public AbstractMap<Ship, HashMap<Double, Ship>> getPairsOfShips() {
        return shipStore.getPairsOfShips();
    }

    public Ship getShip(String code){
        return shipStore.getShip(code);
    }
}