package lapr.project.store;

import lapr.project.controller.App;
import lapr.project.controller.ShipSummaryController;
import lapr.project.model.Message;
import lapr.project.model.Ship;
import lapr.project.model.ShipSummary;
import lapr.project.structures.BST;
import lapr.project.utils.DistanceBetweenCoodinates;
import lapr.project.utils.MMSISorter;
import lapr.project.utils.MovementSortingTravelDistance;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;

public class ShipStore {
    private final List<Ship> shipList;
    private BST bstMMSI;
    private BST bstIMO;
    private BST bstCallSign;

    /**
     * Constructor
     */
    public ShipStore() {
        shipList = new ArrayList<>();
        bstMMSI = new BST();
        bstIMO = new BST();
        bstCallSign = new BST();
    }

    /**
     * @return BST MMSI
     */
    public BST getBstMMSI() {
        return bstMMSI;
    }

    /**
     * @return BST IMO
     */
    public BST getBstIMO() {
        return bstIMO;
    }

    /**
     * @return BST CallSign
     */
    public BST getBstCallSign() {
        return bstCallSign;
    }

    /**
     * @return List of Ships
     */
    public List<Ship> getShipList() {
        return shipList;
    }

    /**
     * Checks the duplicated data of a ship (MMSI, IMO, CallSign)
     */

    public void checkDuplicatedData(Ship ship) {
        for (Ship ships : shipList) {
            if (ship.getMMSI() == (ships.getMMSI())) {
                throw new IllegalArgumentException("MMSI already in System");
            }
            if (ship.getIMO().equals(ships.getIMO())) {
                throw new IllegalArgumentException("IMO already in System");
            }
            if (ship.getCallsign().equals(ships.getCallsign())) {
                throw new IllegalArgumentException("CallSign already in System");
            }
        }
    }

    /**
     * Add a ship to the BST'S after looking for duplicated data
     */
    public void addShip(Ship ship) {
        checkDuplicatedData(ship);
        shipList.add(ship);
        bstMMSI.put(ship.getMMSI(), ship);
        bstIMO.put(ship.getIMO(), ship);
        bstCallSign.put(ship.getCallsign(), ship);

    }

    /**
     * Checks if the code provided matches to a given ship
     */
    public Ship getShip(String code) {
        if (!Pattern.matches(".*[a-zA-Z]+.*", code)) {
            if (code.length() == 9) {
                int MMSI = Integer.parseInt(code);
                return ((Ship) App.getInstance().getCompany().getShipStore().getBstMMSI().search(MMSI));
            }
        } else {
            if (code.length() == 10) {
                return ((Ship) App.getInstance().getCompany().getShipStore().getBstIMO().search(code));
            } else {
                return ((Ship) App.getInstance().getCompany().getShipStore().getBstCallSign().search(code));
            }
        }
        throw new IllegalArgumentException("The code provided does not match to any ship in the database.");
    }

    /**
     * This method creates a summary of a ship. It needs a code to find the ship.
     *
     * @param code code. MMSI, IMO or CALL SIGN
     * @return a map with values of the ship.
     */
    public Map<ShipSummary, Object> shipSummary(String code) {
        Map<ShipSummary, Object> shipSummaryObjectMap = new HashMap<>();

        //put the vessel name in the map
        Ship ship = getShip(code);


        TreeSet<Message> messages = ship.getAllMessages(); //all messages are already in ascending order
        LocalDateTime startBaseDateTime = messages.first().getLocalDateTime();
        LocalDateTime endBaseDateTime = messages.last().getLocalDateTime();
        Duration totalMovementTime = Duration.between(startBaseDateTime, endBaseDateTime);

        //1 Minute = 60 Seconds
        //1 Hour = 3600 Seconds ( 60 * 60 )
        //1 Day = 86400 Second ( 24 * 3600 )
        double departureLatidude = messages.first().getLatitude();
        double departureLongitude = messages.first().getLongitude();
        double arrivalLatidude = messages.last().getLatitude();
        double arrivalLongitude = messages.last().getLongitude();
        double deltaDistance = DistanceBetweenCoodinates.distanceBetweenCoordinates(departureLatidude, departureLongitude, arrivalLatidude, arrivalLongitude);

        double sumSOG = 0.0;
        double maxCOG = 0.9;
        double sumCOG = 0.9;
        double maxSOG = 0.0;
        double lastLatitude = messages.first().getLatitude();
        double lastLongitude = messages.first().getLongitude();
        double sumDistances = 0.0;

        // O(N)
        for (Message m : messages) {
            sumCOG = sumCOG + m.getCog();
            sumSOG = sumSOG + m.getSog();

            if (m.getCog() >= maxCOG) {
                maxCOG = m.getCog();
            }
            if (m.getSog() >= maxSOG) {
                maxSOG = m.getSog();
            }
            sumDistances = sumDistances + DistanceBetweenCoodinates.distanceBetweenCoordinates(lastLatitude, lastLongitude, m.getLatitude(), m.getLongitude());
            lastLatitude = m.getLatitude();
            lastLongitude = m.getLongitude();
        }
        DecimalFormat df = new DecimalFormat("#.#####"); //get rounded value

        // Complexity of .pu() method: O(1)
        shipSummaryObjectMap.put(ShipSummary.CODE, code);
        shipSummaryObjectMap.put(ShipSummary.VESSEL_NAME, ship.getName());
        shipSummaryObjectMap.put(ShipSummary.START_BASE_DATE_TIME, startBaseDateTime);
        shipSummaryObjectMap.put(ShipSummary.END_BASE_DATE_TIME, endBaseDateTime);
        shipSummaryObjectMap.put(ShipSummary.TOTAL_NUMBER_MOVEMENTS, messages.size());
        shipSummaryObjectMap.put(ShipSummary.TOTAL_MOVEMENT_TIME, totalMovementTime);
        shipSummaryObjectMap.put(ShipSummary.MEAN_COG, Double.parseDouble(df.format(sumCOG / messages.size()).replace(",", ".")));
        shipSummaryObjectMap.put(ShipSummary.MEAN_SOG, Double.parseDouble(df.format(sumSOG / messages.size()).replace(",", ".")));
        shipSummaryObjectMap.put(ShipSummary.MAX_SOG, Double.parseDouble(df.format(maxSOG).replace(",", ".")));
        shipSummaryObjectMap.put(ShipSummary.MAX_COG, Double.parseDouble(df.format(maxCOG).replace(",", ".")));

        shipSummaryObjectMap.put(ShipSummary.DEPARTURE_LATITUDE, departureLatidude);
        shipSummaryObjectMap.put(ShipSummary.DEPARTURE_LONGITUDE, departureLongitude);
        shipSummaryObjectMap.put(ShipSummary.ARRIVAL_LATITUDE, arrivalLatidude);
        shipSummaryObjectMap.put(ShipSummary.ARRIVAL_LONGITUDE, arrivalLongitude);
        shipSummaryObjectMap.put(ShipSummary.DELTA_DISTANCE, deltaDistance);

        shipSummaryObjectMap.put(ShipSummary.TRAVELED_DISTANCE, sumDistances);
        return shipSummaryObjectMap;


    }

    /**
     * Gets the top number of ships ordered by travelled distance, and SOG, in a date period, grouped by vessel type
     *
     * @return A linkedHashMap with the vessel type and a list of ships
     */
    //US106
    public LinkedHashMap<String, List<Ship>> getTopNShips(int n, LocalDateTime start, LocalDateTime end) {
        LinkedHashMap<String, List<Ship>> linkedHashMap = new LinkedHashMap<>();

        List<Ship> shipsFiltered;
        String lastVesselType = "";
        //make the list of vessel types
        List<String> vesselTypeList = new ArrayList<>();
        for (Ship s : shipList) {
            Map<ShipSummary, Object> map1 = shipSummary(String.valueOf(s.getMMSI()));
            if (!s.getVesselType().equals(lastVesselType) && ((((LocalDateTime) map1.get(ShipSummary.START_BASE_DATE_TIME)).isAfter(start) || ((LocalDateTime) map1.get(ShipSummary.START_BASE_DATE_TIME)).isEqual(start))
                    && (((LocalDateTime) map1.get(ShipSummary.END_BASE_DATE_TIME)).isBefore(end) || ((LocalDateTime) map1.get(ShipSummary.END_BASE_DATE_TIME)).isEqual(end)))) {
                vesselTypeList.add(s.getVesselType());
            }
            lastVesselType = s.getVesselType();
        }

        //Linked hashmap with vesselType as key and a list of ships for each vesselType, with the same vesselType and between the dates
        LinkedHashMap<String, List<Ship>> vesselTypeMap = new LinkedHashMap<>();
        for (String vessel : vesselTypeList) {
            List<Ship> shipp = new ArrayList<>();
            for (Ship ship : shipList) {
                if (ship.getVesselType().equals(vessel)) {
                    shipp.add(ship);
                }
            }
            vesselTypeMap.put(vessel, shipp);
        }

        for (String vesselType : vesselTypeList) {

            shipsFiltered = vesselTypeMap.get(vesselType);

            shipsFiltered.sort(new MovementSortingTravelDistance());
            List<Ship> subItems;
            int nCopy = n;
            if (shipsFiltered.size() < nCopy) {
                while (shipsFiltered.size() != nCopy) {
                    nCopy--;
                }
            }
            subItems = new ArrayList<>(shipsFiltered.subList(0, nCopy));
            linkedHashMap.put(vesselType, subItems);

        }
        return linkedHashMap;
    }

    //US107

    /**
     * This method gets pairs of ships with similar arrival and departures coordinates
     *
     * @return AbstractMap With ships, and HashMap with travelled distance and ship
     */
    public AbstractMap<Ship, HashMap<Double, Ship>> getPairsOfShips() {
        ShipSummaryController shipSummaryController = new ShipSummaryController();
        HashMap<Ship, HashMap<Double, Ship>> map = new HashMap<>();
        LinkedHashMap<Ship, HashMap<Double, Ship>> mapSorted = new LinkedHashMap<>();
        HashMap<Double, Ship> shipHashMap;
        for (Ship ship1 : shipList) {
            shipHashMap = new HashMap<>();
            for (Ship ship2 : shipList) {
                if (ship1 != ship2 && !map.containsKey(ship2) && !map.containsValue(ship2)) {
                    Map<ShipSummary, Object> map1 = shipSummaryController.shipSummary(String.valueOf(ship1.getMMSI()));
                    Map<ShipSummary, Object> map2 = shipSummaryController.shipSummary(String.valueOf(ship2.getMMSI()));
                    if ((Double) map1.get(ShipSummary.TRAVELED_DISTANCE) >= 10 && (Double) map2.get(ShipSummary.TRAVELED_DISTANCE) >= 10) {
                        Double distanceDeparture = DistanceBetweenCoodinates.distanceBetweenCoordinates((Double) map1.get(ShipSummary.DEPARTURE_LATITUDE), (Double) map1.get(ShipSummary.DEPARTURE_LONGITUDE), (Double) map2.get(ShipSummary.DEPARTURE_LATITUDE), (Double) map2.get(ShipSummary.DEPARTURE_LONGITUDE));
                        Double distanceArrival = DistanceBetweenCoodinates.distanceBetweenCoordinates((Double) map1.get(ShipSummary.ARRIVAL_LATITUDE), (Double) map1.get(ShipSummary.ARRIVAL_LONGITUDE), (Double) map2.get(ShipSummary.ARRIVAL_LATITUDE), (Double) map2.get(ShipSummary.ARRIVAL_LONGITUDE));
                        if (Math.abs(distanceDeparture - distanceArrival) <= 5) {

                            double diference = Math.abs(distanceDeparture - distanceArrival);
                            shipHashMap.put(diference, ship2);
                        }
                    }
                }

            }
            shipHashMap = TravelDistOrder(shipHashMap);
            map.put(ship1, shipHashMap);
        }

        map = MMSIOrder(map);
        List<Ship> ships1 = new ArrayList<>(map.keySet());
        ships1.sort(new MMSISorter());
        for (Ship s1 : ships1) {
            if (map.get(s1).size() > 0) {
                mapSorted.put(s1, map.get(s1));
            }
        }
        return mapSorted;
    }

    /**
     * This method orders the ships by their MMSI
     *
     * @return LinkedHashMap with Ship, and HashMap With travelled distance and a ship
     */
    private LinkedHashMap<Ship, HashMap<Double, Ship>> MMSIOrder(HashMap<Ship, HashMap<Double, Ship>> map) {

        List<Ship> ships1 = new ArrayList<>(map.keySet());
        ships1.sort(new MMSISorter());

        LinkedHashMap<Ship, HashMap<Double, Ship>> sortedMap = new LinkedHashMap<>();
        for (Ship d : ships1) {
            sortedMap.put(d, map.get(d));
        }

        return sortedMap;
    }

    /**
     * This method orders the ships by travelled distance
     */
    private LinkedHashMap<Double, Ship> TravelDistOrder(HashMap<Double, Ship> map) {

        List<Double> doubles = new ArrayList<>(map.keySet());

        Collections.sort(doubles);

        LinkedHashMap<Double, Ship> st = new LinkedHashMap<>();
        for (Double d : doubles) {
            Ship ship = map.get(d);
            st.put(d, ship);
        }

        return st;
    }

    /**
     * This method clears the list of ships.
     */
    public void clearShipList() {
        shipList.clear();
        bstMMSI = new BST();
        bstIMO = new BST();
        bstCallSign = new BST();
    }
}
