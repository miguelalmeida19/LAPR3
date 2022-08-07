package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.model.*;
import lapr.project.model.Container;
import lapr.project.ui.console.console_utils.TextUtils;
import lapr.project.utils.DistanceBetweenCoodinates;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

import static lapr.project.ui.console.console_utils.Utils.print;


public class ImportFromDataBase {

    static final String SELECTCARGOMANI = "SELECT * FROM cargo_manifest WHERE shipimo = '";
    static final String SELECTCARGOCONT = "SELECT * FROM cargo_manifest_container WHERE cargo_manifestid = '";
    private static final String ISO_CODE_CONSTANT = "iso_code";
    /**
     * This method returns the containers to be either loaded of offloaded
     *
     * @return HashMap with the listr of containers
     */
    public static HashMap<String, List<Container>> getContainersByShipPortType(String shipIMO, LocalDateTime actualDate, String type) {
        try {
            List<Container> containers = new ArrayList<>();
            String sql;
            HashMap<String, List<Container>> hashMap = new LinkedHashMap<>();

            sql = SELECTCARGOMANI + shipIMO + "' AND " +
                    "base_date_time > '" + actualDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "'"
                    + " AND type='" + type + "'" + "ORDER BY base_date_time";

            ResultSet rs = App.getInstance().getCompany().getStatement().executeQuery(sql);
            if (rs.next()) {
                String port = rs.getString("portId");
                sql = SELECTCARGOCONT + rs.getString("id") + "'";
                ResultSet rs1 = App.getInstance().getCompany().getStatement().executeQuery(sql);
                List<String> containersID = new ArrayList<>();
                while (rs1.next()) {
                    containersID.add(rs1.getString("containerid"));
                }
                for (String containerId : containersID) {
                    sql = "SELECT * FROM container WHERE id = " + containerId + "";
                    ResultSet rs2 = App.getInstance().getCompany().getStatement().executeQuery(sql);
                    while (rs2.next()) {
                        Container container = new Container(
                                rs2.getInt("id"),
                                rs2.getString("payload"),
                                rs2.getDouble("tare"),
                                rs2.getDouble("gross"),
                                rs2.getDouble("cargo_weight"),
                                ISO_CODE.fromString(rs.getString(ISO_CODE_CONSTANT)),
                                rs2.getDouble("temperature"),
                                rs2.getString("Client_Email"));

                        containers.add(container);
                    }
                }
                hashMap.put(port, containers);
                return hashMap;
            }

        } catch (Exception e) {
            print(e.getMessage());
        }
        throw new IllegalArgumentException("No containers found " + type + " :((");
    }

    /**
     * This method gets the position of a container in a ship
     *
     * @return array with x,y and z coordinates
     */
    public static int[] getPositionOfContainer(String containerID) {
        //
        try {
            int[] positions = new int[3];
            String sql;
            sql = "SELECT * FROM cargo_manifest_container WHERE containerID = '" + containerID + "'";
            ResultSet rs = App.getInstance().getCompany().getStatement().executeQuery(sql);
            if (rs.next()) {
                positions[0] = rs.getInt("POSX");
                positions[1] = rs.getInt("POSY");
                positions[2] = rs.getInt("POSZ");
            }
            return positions;
        } catch (Exception ignored) {
            //ignored
        }
        throw new IllegalArgumentException("No positions found for this container in database :((");
    }

    /**
     * This method returns the occupancy rate by a cargo manifest
     *
     * @return double with occupancy rate
     */
    public static double getOccupancyRateByCargoManifest(String code, String cargoManifest) {
        try {
            Ship ship = App.getInstance().getCompany().getShipStore().getShip(code);
            String shipIMO = ship.getIMO();
            String sql = SELECTCARGOMANI + shipIMO + "' AND ID = '" + cargoManifest + "'";
            ResultSet rs = App.getInstance().getCompany().getStatement().executeQuery(sql);
            if (rs.next() && rs.getString("type").equals("TO BE OFFLOADED")) {
                sql = SELECTCARGOCONT + cargoManifest + "'";
                rs = App.getInstance().getCompany().getStatement().executeQuery(sql);
                List<String> containersList = new ArrayList<>();
                while (rs.next()) {
                    containersList.add(String.valueOf(rs.getInt("containerID")));
                }
                int sum = 0;
                for (String container_id : containersList) {
                    sql = "SELECT * FROM CONTAINER WHERE ID = " + container_id;
                    rs = App.getInstance().getCompany().getStatement().executeQuery(sql);

                    ISO_CODE iso = null;
                    if (rs.next()) {
                        iso = ISO_CODE.fromString(rs.getString(ISO_CODE_CONSTANT));
                    }
                    double sizex = iso.getSizeX();
                    double sizey = iso.getSizeY();
                    double sizez = iso.getSizeZ();
                    double volume = sizex * sizey * sizez;
                    sum += volume;
                }
                return (sum / Double.parseDouble(ship.getCapacity())) * 100;
            }
        } catch (Exception ignored) {
            //ignored
        }
        throw new IllegalArgumentException("No Container with given the data in database :((");
    }

    /**
     * This method returns the occupancy rate in a given method
     *
     * @return double with occupancy rate
     */
    public static double getOccupancyRateByDate(String code, String date) {
        try {
            Ship ship = App.getInstance().getCompany().getShipStore().getShip(code);
            String shipIMO = ship.getIMO();
            String sql = SELECTCARGOMANI + shipIMO + "' AND base_date_time = '" + date + "'";
            ResultSet rs = App.getInstance().getCompany().getStatement().executeQuery(sql);
            if (rs.next()) {
                if (rs.getString("type").equals("TO BE OFFLOADED")) {
                    sql = SELECTCARGOCONT + rs.getString("id") + "'";
                    rs = App.getInstance().getCompany().getStatement().executeQuery(sql);

                    List<String> containersList = new ArrayList<>();
                    while (rs.next()) {
                        containersList.add(String.valueOf(rs.getInt("containerID")));
                    }
                    double sum = 0;
                    for (String container_id : containersList) {
                        sql = "SELECT * FROM CONTAINER WHERE ID = " + container_id;
                        rs = App.getInstance().getCompany().getStatement().executeQuery(sql);

                        ISO_CODE iso = null;
                        if (rs.next()) {
                            iso = ISO_CODE.fromString(rs.getString(ISO_CODE_CONSTANT));
                        }

                        double sizex = iso.getSizeX();
                        double sizey = iso.getSizeY();
                        double sizez = iso.getSizeZ();
                        double volume = sizex * sizey * sizez;
                        sum += volume;
                    }
                    try {
                        return (sum / Double.parseDouble(ship.getCapacity())) * 100;
                    } catch (Exception e) {
                        print(TextUtils.ANSI_RED + "\nShip Capacity " + ship.getCapacity() + " is invalid!" + TextUtils.ANSI_BLUE);
                    }

                }
            } else {
                throw new IllegalArgumentException("No Container with given the data in database :((");
            }
        } catch (Exception ignored) {
            //ignored
        }
        return 0;
    }

    /**
     * @return a list of all countries in the DataBase
     */
    public static List<Country> getAllCountries() {
        List<Country> countries = new ArrayList<>();
        try {
            String query = "SELECT * FROM COUNTRY";

            ResultSet rs = App.getInstance().getCompany().getStatement().executeQuery(query);

            while (rs.next()) {
                Country c = new Country(rs.getString("Name"), rs.getString("Continent"), rs.getString("Capital"), rs.getDouble("LATITUDE"), rs.getDouble("LONGITUDE"));
                countries.add(c);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return countries;
    }

    /**
     * Gets the borders of a certain country
     * @return The list of countries which is a border of a certain country
     */
    public static List<Country> getCountryBorders(Country country, List<Country> countries) {
        List<Country> borders = new ArrayList<>();
        try {
            String query = "SELECT * FROM BORDER WHERE BORDERNAME='" + country.getCountryName() + "'";
            ResultSet resultSet = App.getInstance().getCompany().getStatement().executeQuery(query);
            while (resultSet.next()) {
                Country c = getCountryByName(resultSet.getString("COUNTRYNAME"), countries);
                if (!borders.contains(c)) {
                    borders.add(c);
                }
            }
            query = "SELECT * FROM BORDER WHERE COUNTRYNAME='" + country.getCountryName() + "'";
            resultSet = App.getInstance().getCompany().getStatement().executeQuery(query);
            while (resultSet.next()) {
                Country c = getCountryByName(resultSet.getString("BORDERNAME"), countries);
                if (!borders.contains(c)) {
                    borders.add(c);
                }
            }
        } catch (Exception ignored) {
            //ignored
        }
        return borders;
    }

    /**
     * Gets the country by its name
     * @return the wanted country
     */
    private static Country getCountryByName(String name, List<Country> countries) {
        for (Country c : countries) {
            if (c.getCountryName().equals(name)) {
                return c;
            }
        }
        throw new IllegalArgumentException("ALERTA O PAÍS " + name + " NÃO EXISTE!");
    }

    /**
     * This method gets the port by its id
     * @return the port with the id provided
     */
    private static Port getPortByID(String id) {
        for (Port p : App.getInstance().getCompany().getPortStore().getPortList()) {
            if (p.getCode().equals(id)) {
                return p;
            }
        }
        throw new IllegalArgumentException("PORT IS NOT PRESENT");
    }

    /**
     * This method gets the list of ports from a country
     * @return the list of ports for the country provided
     */
    public static List<Port> getPortFromCountry(String country) {
        List<Port> ports = new ArrayList<>();
        try {
            String query = "SELECT * FROM PORT WHERE COUNTRY_NAME='" + country + "'";
            ResultSet rs = App.getInstance().getCompany().getStatement().executeQuery(query);
            while (rs.next()) {
                Port port = new Port(rs.getString("CONTINENT"), rs.getString("COUNTRY_NAME"), rs.getString("ID"), rs.getString("NAME"), rs.getDouble("LATITUDE"), rs.getDouble("LONGITUDE"));
                ports.add(port);
            }
        } catch (Exception e) {
            print(e.getMessage());
        }
        return ports;
    }

    /**
     *
     */
    public static LinkedHashMap<Double, Port> wrapLinkedHashMap(LinkedHashMap<Port, Double> linkedHashMap) {
        LinkedHashMap<Double, Port> linkedHashMap1 = new LinkedHashMap<>();
        for (Port pt : linkedHashMap.keySet()) {
            linkedHashMap1.put(linkedHashMap.get(pt), pt);
        }
        return linkedHashMap1;
    }

    /**
     * This method calculates the distance between 2 ports
     * @return a double with the distance between the 2 ports provided
     */
    public static double getDistanceBetweenPorts(Port port1, Port port2) {
        double distance = 0;
        try {
            String query = "SELECT * FROM SEADISTS WHERE FROM_PORT= '" + port1.getPort() + "' AND TO_PORT='" + port2.getPort() + "'";
            ResultSet rs = App.getInstance().getCompany().getStatement().executeQuery(query);
            if (rs.next()) {
                distance = rs.getDouble("SEA_DISTANCE");
            } else {
                query = "SELECT * FROM SEADISTS WHERE FROM_PORT= '" + port2.getPort() + "' AND TO_PORT='" + port1.getPort() + "'";
                rs = App.getInstance().getCompany().getStatement().executeQuery(query);
                if (rs.next()) {
                    distance = rs.getDouble("SEA_DISTANCE");
                }
            }
        } catch (Exception e) {
            //ignored
        }
        return distance;
    }

    /**
     * This method gets the possible connections for a certain port
     * @return a linkedHashMap with the connections an its distances
     */
    public static LinkedHashMap<Port, Double> getPossibleConnections(Port port) {
        LinkedHashMap<Port, Double> distances = new LinkedHashMap<>();
        LinkedHashMap<Port, Double> distancesCut = new LinkedHashMap<>();
        try {
            String query = "SELECT * FROM SEADISTS WHERE TO_PORTID = " + port.getCode();
            ResultSet rs = App.getInstance().getCompany().getStatement().executeQuery(query);

            while (rs.next()) {
                String code = String.valueOf(rs.getInt("PORTID"));
                Port pt = getPortByID(code);
                double distance = DistanceBetweenCoodinates.distanceBetweenCoordinates(port.getLatitude(), port.getLongitude(), pt.getLatitude(), pt.getLongitude());
                if (!distances.containsKey(pt)) {
                    distances.put(pt, distance);
                }
            }

            query = "SELECT * FROM SEADISTS WHERE PORTID = " + port.getCode();
            rs = App.getInstance().getCompany().getStatement().executeQuery(query);

            while (rs.next()) {
                String code = String.valueOf(rs.getInt("TO_PORTID"));
                Port pt = getPortByID(code);
                double distance = DistanceBetweenCoodinates.distanceBetweenCoordinates(port.getLatitude(), port.getLongitude(), pt.getLatitude(), pt.getLongitude());
                if (!distances.containsKey(pt)) {
                    distances.put(pt, distance);
                }
            }

            List<Double> listValues = new ArrayList<>(distances.values());
            Collections.sort(listValues);

            for (Map.Entry<Port, Double> pt : distances.entrySet()) {
                if (!pt.getKey().getCountry().equals(port.getCountry())) {
                    distancesCut.put(pt.getKey(), pt.getValue());
                }
            }
        } catch (Exception ignored) {
            //ignored

        }
        return distancesCut;
    }

    /**
     * This method calls a function from the database to obtain the route of a container
     * @return a string with the route
     */
    public static String getRouteOfContainer(String containerID, String clientReg) {
        String mCount = null;
        try {
            try (CallableStatement stm = App.getInstance().getCompany().getStatement().getConnection().prepareCall("{ ?=call getrouteofcontainer(?,?) }")) {
                stm.registerOutParameter(1, Types.VARCHAR);
                stm.setInt(2, Integer.parseInt(containerID));
                stm.setInt(3, Integer.parseInt(clientReg));
                stm.executeUpdate();
                mCount = stm.getString(1);
            }
        } catch (Exception e) {
            print(e.getMessage());
        }
        return mCount;
    }

    /**
     * This method calls a function from the DataBase to get estimate of containers leaving
     * @return string with the number of containers
     */
    public static String getEstimateOfContainersLeaving() {
        String mCount = null;
        try {
            String email = App.getInstance().getCompany().getAuthFacade().getCurrentUserSession().getUserId().getString();
            CallableStatement stm = App.getInstance().getCompany().getStatement().getConnection().prepareCall("{ ?=call getEstimateOfContainersLeaving(?) }");
            stm.registerOutParameter(1, Types.VARCHAR);
            stm.setString(2, email);
            stm.executeUpdate();
            mCount = stm.getString(1);
            stm.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return mCount;
    }

    /**
     * This method calls a function from the DataBase to get the occupancy rate of each warehouse
     * @return string with the occupancy rates
     */
    public static String getOccupancyRateOfEachWarehouse() {
        String mCount = null;
        try {
            String email = App.getInstance().getCompany().getAuthFacade().getCurrentUserSession().getUserId().getString();
            try (CallableStatement stm = App.getInstance().getCompany().getStatement().getConnection().prepareCall("{ ?=call getOCCupancyRateWarehouses(?) }")) {
                stm.registerOutParameter(1, Types.VARCHAR);
                stm.setString(2, email);
                stm.executeUpdate();
                mCount = stm.getString(1);
            }
        } catch (Exception e) {
            print(e.getMessage());
        }
        return mCount;
    }

    /**
     * This method calls a function from the DataBase to get the occupancy rate of each warehouse in a month
     * @return string with the occupancy rates
     */
    public static String getOccupancyRateOfEachWarehouse(int month) {
        String mCount = null;
        try {
            String email = App.getInstance().getCompany().getAuthFacade().getCurrentUserSession().getUserId().getString();
            try (CallableStatement stm = App.getInstance().getCompany().getStatement().getConnection().prepareCall("{ ?=call getOCCupancyRateWarehousesMONTH(?, ?) }")) {
                stm.registerOutParameter(1, Types.VARCHAR);
                stm.setString(2, email);
                stm.setInt(3, month);
                stm.executeUpdate();
                mCount = stm.getString(1);
            }
        } catch (Exception e) {
            print(e.getMessage());
        }
        return mCount;
    }

    public static String getCountryCapital(double latitude, double longitude) {
        String mCount = null;
        try {
            try (CallableStatement stm = App.getInstance().getCompany().getStatement().getConnection().prepareCall("{ ?=call GETCOUNTRYCAPITAL(?, ?) }")) {
                stm.registerOutParameter(1, Types.VARCHAR);
                stm.setDouble(2, latitude);
                stm.setDouble(3, longitude);
                stm.executeUpdate();
                mCount = stm.getString(1);
            }
        } catch (Exception ignore) {
            //ignored
        }
        return mCount;
    }

    public static String getCountryContinent(double latitude, double longitude) {
        String mCount = null;
        try {
            try (CallableStatement stm = App.getInstance().getCompany().getStatement().getConnection().prepareCall("{ ?=call GETCOUNTRYCONTINENT(?, ?) }")) {
                stm.registerOutParameter(1, Types.VARCHAR);
                stm.setDouble(2, latitude);
                stm.setDouble(3, longitude);
                stm.executeUpdate();
                mCount = stm.getString(1);
            }
        } catch (Exception ignore) {
            //ignored
        }
        return mCount;
    }

    public static String getCountryName(double latitude, double longitude) {
        String mCount = null;
        try {
            try (CallableStatement stm = App.getInstance().getCompany().getStatement().getConnection().prepareCall("{ ?=call GETCOUNTRYNAME(?, ?) }")) {
                stm.registerOutParameter(1, Types.VARCHAR);
                stm.setDouble(2, latitude);
                stm.setDouble(3, longitude);
                stm.executeUpdate();
                mCount = stm.getString(1);
            }
        } catch (Exception ignore) {
            //ignored
        }
        return mCount;
    }

    public static String getPORTContinent(double latitude, double longitude) {
        String mCount = null;
        try {
            try (CallableStatement stm = App.getInstance().getCompany().getStatement().getConnection().prepareCall("{ ?=call GETPORTCONTINENT(?, ?) }")) {
                stm.registerOutParameter(1, Types.VARCHAR);
                stm.setDouble(2, latitude);
                stm.setDouble(3, longitude);
                stm.executeUpdate();
                mCount = stm.getString(1);
            }
        } catch (Exception ignore) {
            //ignored
        }
        return mCount;
    }

    public static String getPORTCountryName(double latitude, double longitude) {
        String mCount = null;
        try {
            try (CallableStatement stm = App.getInstance().getCompany().getStatement().getConnection().prepareCall("{ ?=call GETPORTCOUNTRYNAME(?, ?) }")) {
                stm.registerOutParameter(1, Types.VARCHAR);
                stm.setDouble(2, latitude);
                stm.setDouble(3, longitude);
                stm.executeUpdate();
                mCount = stm.getString(1);
            }
        } catch (Exception ignore) {
            //ignored
        }
        return mCount;
    }

    public static String getPORTName(double latitude, double longitude) {
        String mCount = null;
        try {
            try (CallableStatement stm = App.getInstance().getCompany().getStatement().getConnection().prepareCall("{ ?=call GETPORTNAME(?, ?) }")) {
                stm.registerOutParameter(1, Types.VARCHAR);
                stm.setDouble(2, latitude);
                stm.setDouble(3, longitude);
                stm.executeUpdate();
                mCount = stm.getString(1);
            }
        } catch (Exception ignore) {
            //ignored
        }
        return mCount;
    }

    public static String getPortOfUser(String email) {
        String mCount = null;
        try {
            try (CallableStatement stm = App.getInstance().getCompany().getStatement().getConnection().prepareCall("{ ?=call GETPORTOFUSER(?) }")) {
                stm.registerOutParameter(1, Types.VARCHAR);
                stm.setString(2, email);
                stm.executeUpdate();
                mCount = stm.getString(1);
            }
        } catch (Exception ignore) {
            //ignored

        }
        return mCount;
    }

    /**
     * This method calls a function from the DataBase to add a cargo manifest container and trigger a warning if it has no capacity left
     * @return the warning in case of no capacity left
     */
    public static String addCargoManifestContainer(int cargoManifestID, int containerID, int posx, int posy, int posz){
        String ret = null;
        try {
            String query = "INSERT INTO CARGO_MANIFEST_CONTAINER(CARGO_MANIFESTID, CONTAINERID, POSX, POSY, POSZ) VALUES (?, ?, ?, ?, ?)";

            Connection connection = null;
            PreparedStatement statement;

            try {
                connection = App.getInstance().getCompany().getDatabaseConnection().getConnection();
                connection.setAutoCommit(false);
                statement = connection.prepareStatement(query);

                statement.setInt(1, cargoManifestID);
                statement.setInt(2, containerID);
                statement.setInt(3, posx);
                statement.setInt(4, posy);
                statement.setInt(5, posz);
                statement.addBatch();

                statement.executeBatch();
                connection.commit();
            } catch (SQLException e) {
                try { connection.rollback(); } catch (SQLException ignored) {
                    //ignored
                }
                ret = e.getMessage();
            }

        }catch (Exception e){
            ret = e.getMessage();
        }
        return ret;
    }

    /**
     * This method calls a function from the DataBase to add a cargo manifest container and trigger a warning if the ship is occupied in the date
     * @return the warning in case the ship is occupied
     */
    public static String addCargoManifest(int vehicleId, String warehouseId, String baseDateTime, String type){
        String ret = null;
        try {
            String query = "INSERT INTO CARGO_MANIFEST(VEHICLEID, WAREHOUSEID, BASE_DATE_TIME, TYPE) VALUES (?, ?, ?, ?)";

            Connection connection = null;
            PreparedStatement statement;

            try {
                connection = App.getInstance().getCompany().getDatabaseConnection().getConnection();
                connection.setAutoCommit(false);
                statement = connection.prepareStatement(query);

                statement.setInt(1, vehicleId);
                statement.setString(2, warehouseId);
                statement.setString(3, baseDateTime);
                statement.setString(4, type);
                statement.addBatch();

                statement.executeBatch();
                connection.commit();
            } catch (SQLException e) {
                try { connection.rollback(); } catch (SQLException ignored) {
                    //ignored
                }
                ret = e.getMessage();
            }

        }catch (Exception e){
            ret = e.getMessage();
        }
        return ret;
    }

    public static String getAverageOccupancyRate(String shipImo, String date1, String date2){
        String mCount = null;
        try {
            try (CallableStatement stm = App.getInstance().getCompany().getStatement().getConnection().prepareCall("{ ?=call getAverageOccupancyRate(?, ?, ?) }")) {
                stm.registerOutParameter(1, Types.VARCHAR);
                stm.setString(2, shipImo);
                stm.setString(3, date1);
                stm.setString(4, date2);
                stm.executeUpdate();
                mCount = stm.getString(1);
            }
        } catch (Exception e) {
            print(e.getMessage());
        }
        return mCount+"%";
    }
    public static String getAverageOccupancyRateOfVoyage(int threshold){
        String mCount = null;
        try {
            try (CallableStatement stm = App.getInstance().getCompany().getStatement().getConnection().prepareCall("{ ?=call getAverageOccupancyRateOfVoyage(?) }")) {
                stm.registerOutParameter(1, Types.VARCHAR);
                stm.setInt(2, threshold);
                stm.executeUpdate();
                mCount = stm.getString(1);
            }
        } catch (Exception e) {
            print(e.getMessage());
        }
        return mCount;
    }
}