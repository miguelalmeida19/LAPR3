package lapr.project.store;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import lapr.project.controller.App;
import lapr.project.controller.ShipController;
import lapr.project.controller.ShipSummaryController;
import lapr.project.model.Ship;
import lapr.project.model.ShipSummary;
import lapr.project.structures.BST;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

class ShipStoreTest {



    @Test
    void testConstructor() {
        ShipStore actualShipStore = new ShipStore();
        assertTrue(actualShipStore.getShipList().isEmpty());
        BST bstMMSI = actualShipStore.getBstMMSI();
        BST bstIMO = actualShipStore.getBstIMO();
        BST bstCallSign = actualShipStore.getBstCallSign();
        assertNull(bstMMSI.root);
        assertNull(bstMMSI.lastReturnedNode);
        assertNull(bstIMO.root);
        assertNull(bstIMO.lastReturnedNode);
        assertNull(bstCallSign.root);
        assertNull(bstCallSign.lastReturnedNode);
    }

    @Test
    void testConstructor2() {
        ShipStore actualShipStore = new ShipStore();
        assertTrue(actualShipStore.getShipList().isEmpty());
        BST bstMMSI = actualShipStore.getBstMMSI();
        BST bstIMO = actualShipStore.getBstIMO();
        BST bstCallSign = actualShipStore.getBstCallSign();
        assertNull(bstMMSI.root);
        assertNull(bstMMSI.lastReturnedNode);
        assertNull(bstIMO.root);
        assertNull(bstIMO.lastReturnedNode);
        assertNull(bstCallSign.root);
        assertNull(bstCallSign.lastReturnedNode);

    }

    @Test
    void testConstructor3() {
        ShipStore actualShipStore = new ShipStore();
        assertTrue(actualShipStore.getShipList().isEmpty());
        assertNull(actualShipStore.getBstMMSI().root);
        assertNull(actualShipStore.getBstIMO().root);
        assertNull(actualShipStore.getBstCallSign().root);

    }


    @Test
    void testGetShipWrong() {
        assertNull((new ShipStore()).getShip("Code"));
        assertNull((new ShipStore()).getShip(".*[a-zA-Z]+.*"));
        assertThrows(IllegalArgumentException.class, () -> (new ShipStore()).getShip("42"));

    }

    @Test
    public void shipSummary() {
        ShipController shipController = new ShipController();
        shipController.importShips("files/testShip.csv");
        ShipSummaryController shipSummaryController = new ShipSummaryController();
        Map<ShipSummary, Object> summary = shipSummaryController.shipSummary("211331640");
        //as distancias estão certas

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime START_BASE_DATE_TIME = LocalDateTime.parse("31/12/2020 00:39", formatter);
        LocalDateTime END_BASE_DATE_TIME = LocalDateTime.parse("31/12/2020 18:03", formatter);
        Duration d = Duration.parse( "PT17H24M" );
        Map<ShipSummary, Object> shipSummaryObjectMap = new HashMap<>();
        shipSummaryObjectMap.put(ShipSummary.CODE, "211331640");
        shipSummaryObjectMap.put(ShipSummary.VESSEL_NAME, "SEOUL EXPRESS");
        shipSummaryObjectMap.put(ShipSummary.START_BASE_DATE_TIME, START_BASE_DATE_TIME);
        shipSummaryObjectMap.put(ShipSummary.END_BASE_DATE_TIME, END_BASE_DATE_TIME);
        shipSummaryObjectMap.put(ShipSummary.TOTAL_NUMBER_MOVEMENTS, Integer.parseInt("4"));
        shipSummaryObjectMap.put(ShipSummary.TOTAL_MOVEMENT_TIME, d);
        shipSummaryObjectMap.put(ShipSummary.MEAN_COG, Double.parseDouble("133.25"));
        shipSummaryObjectMap.put(ShipSummary.MEAN_SOG, Double.parseDouble("14.775"));
        shipSummaryObjectMap.put(ShipSummary.MAX_SOG, Double.parseDouble("19.7"));
        shipSummaryObjectMap.put(ShipSummary.MAX_COG, Double.parseDouble("147.6"));

        shipSummaryObjectMap.put(ShipSummary.DEPARTURE_LATITUDE, 36.59348);
        shipSummaryObjectMap.put(ShipSummary.DEPARTURE_LONGITUDE, -122.86674);
        shipSummaryObjectMap.put(ShipSummary.ARRIVAL_LATITUDE, 33.78012);
        shipSummaryObjectMap.put(ShipSummary.ARRIVAL_LONGITUDE, -118.72088);
        shipSummaryObjectMap.put(ShipSummary.DELTA_DISTANCE, 489.61566);

        shipSummaryObjectMap.put(ShipSummary.TRAVELED_DISTANCE, 491.95489000000003);

        for(ShipSummary s : ShipSummary.values()){
            System.out.println(s);
            assertTrue(summary.get(s).equals(shipSummaryObjectMap.get(s) ));
        }

    }
    @Test
    public void shipSummaryWrongCode() {
        ShipController shipController = new ShipController();
        shipController.importShips("files/testShip.csv");
        ShipSummaryController shipSummaryController = new ShipSummaryController();
        Ship ship = App.getInstance().getCompany().getShipStore().getShip("211331640");
        Map<ShipSummary, Object> summary = shipSummaryController.shipSummary("211331640");

        Map<ShipSummary, Object> shipSummaryObjectMap = new HashMap<>();
        shipSummaryObjectMap.put(ShipSummary.CODE, "211331640");


        assertFalse(summary.get(ShipSummary.CODE).equals("211331641") );


    }
    @Test
    public void shipSummaryWrongVESSEL_NAME() {
        ShipController shipController = new ShipController();
        shipController.importShips("files/testShip.csv");
        ShipSummaryController shipSummaryController = new ShipSummaryController();
        Map<ShipSummary, Object> summary = shipSummaryController.shipSummary("211331640");
        assertFalse(summary.get(ShipSummary.VESSEL_NAME).equals("CHEIROOO") );


    }
    @Test
    public void shipSummaryWrongSTART_BASE_DATE_TIME() {
        ShipController shipController = new ShipController();
        shipController.importShips("files/testShip.csv");
        ShipSummaryController shipSummaryController = new ShipSummaryController();
        Map<ShipSummary, Object> summary = shipSummaryController.shipSummary("211331640");


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime START_BASE_DATE_TIME = LocalDateTime.parse("31/01/2026 00:39", formatter);


        assertFalse(summary.get(ShipSummary.START_BASE_DATE_TIME).equals(START_BASE_DATE_TIME));


    }
    @Test
    public void shipSummaryWrongEND_BASE_DATE_TIME() {
        ShipController shipController = new ShipController();
        shipController.importShips("files/testShip.csv");
        ShipSummaryController shipSummaryController = new ShipSummaryController();
        Map<ShipSummary, Object> summary = shipSummaryController.shipSummary("211331640");


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime END_BASE_DATE_TIME = LocalDateTime.parse("31/01/2026 00:39", formatter);


        assertFalse(summary.get(ShipSummary.END_BASE_DATE_TIME).equals(END_BASE_DATE_TIME));


    }
    @Test
    public void shipSummaryWrongTOTAL_NUMBER_MOVEMENTS() {
        ShipController shipController = new ShipController();
        shipController.importShips("files/testShip.csv");
        ShipSummaryController shipSummaryController = new ShipSummaryController();
        Map<ShipSummary, Object> summary = shipSummaryController.shipSummary("211331640");



        assertFalse((int)summary.get(ShipSummary.TOTAL_NUMBER_MOVEMENTS) ==50245);


    }
    @Test
    public void shipSummaryWrongTOTAL_MOVEMENT_TIME() {
        ShipController shipController = new ShipController();
        shipController.importShips("files/testShip.csv");
        ShipSummaryController shipSummaryController = new ShipSummaryController();
        Map<ShipSummary, Object> summary = shipSummaryController.shipSummary("211331640");
        Duration d = Duration.parse( "PT20H26M" );


        assertFalse(summary.get(ShipSummary.TOTAL_MOVEMENT_TIME).equals(d));


    }
    @Test
    public void shipSummaryWrongMEAN_COG() {
        ShipController shipController = new ShipController();
        shipController.importShips("files/testShip.csv");
        ShipSummaryController shipSummaryController = new ShipSummaryController();
        Map<ShipSummary, Object> summary = shipSummaryController.shipSummary("211331640");


        assertFalse((double)summary.get(ShipSummary.MEAN_COG) ==0.0);


    }
    @Test
    public void shipSummaryWrongMEAN_SOG() {
        ShipController shipController = new ShipController();
        shipController.importShips("files/testShip.csv");
        ShipSummaryController shipSummaryController = new ShipSummaryController();
        Map<ShipSummary, Object> summary = shipSummaryController.shipSummary("211331640");


        assertFalse((double)summary.get(ShipSummary.MEAN_SOG) ==0.0);


    }
    @Test
    public void shipSummaryWrongMAX_SOG() {
        ShipController shipController = new ShipController();
        shipController.importShips("files/testShip.csv");
        ShipSummaryController shipSummaryController = new ShipSummaryController();
        Map<ShipSummary, Object> summary = shipSummaryController.shipSummary("211331640");


        assertFalse((double)summary.get(ShipSummary.MAX_SOG) ==0.0);


    }
    @Test
    public void shipSummaryWrongMAX_cog() {
        ShipController shipController = new ShipController();
        shipController.importShips("files/testShip.csv");
        ShipSummaryController shipSummaryController = new ShipSummaryController();
        Map<ShipSummary, Object> summary = shipSummaryController.shipSummary("211331640");


        assertFalse((double) summary.get(ShipSummary.MAX_COG) ==120.0);


    }
    @Test
    public void shipSummaryWrongDEPARTURE_LONGITUDE() {
        ShipController shipController = new ShipController();
        shipController.importShips("files/testShip.csv");
        ShipSummaryController shipSummaryController = new ShipSummaryController();
        Map<ShipSummary, Object> summary = shipSummaryController.shipSummary("211331640");


        assertFalse((double) summary.get(ShipSummary.DEPARTURE_LONGITUDE) ==0.0);


    }
    @Test
    public void shipSummaryWrongDEPARTURE_latitude() {
        ShipController shipController = new ShipController();
        shipController.importShips("files/testShip.csv");
        ShipSummaryController shipSummaryController = new ShipSummaryController();
        Map<ShipSummary, Object> summary = shipSummaryController.shipSummary("211331640");


        assertFalse((double)summary.get(ShipSummary.DEPARTURE_LATITUDE) ==0.0);


    }
    @Test
    public void shipSummaryWrongARRIVAL_latitude() {
        ShipController shipController = new ShipController();
        shipController.importShips("files/testShip.csv");
        ShipSummaryController shipSummaryController = new ShipSummaryController();
        Map<ShipSummary, Object> summary = shipSummaryController.shipSummary("211331640");


        assertFalse((double)summary.get(ShipSummary.ARRIVAL_LATITUDE) ==0.0);


    }
    @Test
    public void shipSummaryWrongARRIVAL_LONGITUDE() {
        ShipController shipController = new ShipController();
        shipController.importShips("files/testShip.csv");
        ShipSummaryController shipSummaryController = new ShipSummaryController();
        Map<ShipSummary, Object> summary = shipSummaryController.shipSummary("211331640");


        assertFalse((double)summary.get(ShipSummary.ARRIVAL_LONGITUDE) ==0.0);


    }
    @Test
    public void shipSummaryWrongDELTADISTANCE() {
        ShipController shipController = new ShipController();
        shipController.importShips("files/testShip.csv");
        ShipSummaryController shipSummaryController = new ShipSummaryController();
        Map<ShipSummary, Object> summary = shipSummaryController.shipSummary("211331640");


        assertFalse((double)summary.get(ShipSummary.DELTA_DISTANCE)==0.0);


    }
    @Test
    public void shipSummaryWrongTRAVELED_DISTANCE() {
        ShipController shipController = new ShipController();
        shipController.importShips("files/testShip.csv");
        ShipSummaryController shipSummaryController = new ShipSummaryController();
        Map<ShipSummary, Object> summary = shipSummaryController.shipSummary("211331640");


        assertFalse((double)summary.get(ShipSummary.TRAVELED_DISTANCE)==0.0);



    }

    @Test
    public void shipListNotEmpty(){
        ShipStore shipStore = new ShipStore();
        Ship ship = new Ship(210950000, "CHEIRO", "IMO9395044", "C4SQ2", "70", 166, 25, 9.5, "NA");
        shipStore.addShip(ship);
        assertFalse(Collections.EMPTY_LIST ==shipStore.getShipList());

    }

    @Test
    public void checkDuplicated(){

        ShipStore shipStore = new ShipStore();
        Ship ship = new Ship(210950000, "CHEIRO", "IMO9395044", "C4SQ2", "70", 166, 25, 9.5, "NA");
        Ship ship1 = new Ship(210950000, "CHEIRO", "IMO9395044", "C4SQ2", "70", 166, 25, 9.5, "NA");
        shipStore.addShip(ship);

        Exception exception = assertThrows(Exception.class, () -> shipStore.addShip(ship1));
        assertTrue("MMSI already in System".equals(exception.getMessage()));

    }

    @Test
    public void testGetTopNShips(){
        App.getInstance().getCompany().getShipStore().clearShipList();

        System.out.println("\n\n" + App.getInstance().getCompany().getShipStore().getShipList().size());

        ShipController shipController = new ShipController();
        ShipSummaryController shipSummaryController = new ShipSummaryController();
        ShipStore shipStore = new ShipStore();
        shipController.importShips("files/TopNTestFile.csv");

        Ship BOREAS = shipStore.getShip("305373000");
        Ship SEA_BURN = shipStore.getShip("309416000");
        Ship SPAR_ARIES = shipStore.getShip("257881000");

        String vesselType = "70";

        List<Ship> ships = new ArrayList<>();
        ships.add(BOREAS);
        ships.add(SPAR_ARIES);
        ships.add(SEA_BURN);

        LinkedHashMap<String, List<Ship>> linkedHashMapExpected = new LinkedHashMap<>();
        linkedHashMapExpected.put(vesselType, ships);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime START = LocalDateTime.parse("28/12/2020 01:00", formatter);
        LocalDateTime END = LocalDateTime.parse("31/12/2020 23:59", formatter);


        LinkedHashMap<String, List<Ship>> linkedHashMapActual = App.getInstance().getCompany().getShipStore().getTopNShips(3, START, END);

        assertTrue(linkedHashMapExpected.equals(linkedHashMapActual));
    }

    @Test
    public void testGetTopNShips1(){
        App.getInstance().getCompany().getShipStore().clearShipList();

        System.out.println("\n\n" + App.getInstance().getCompany().getShipStore().getShipList().size());

        ShipController shipController = new ShipController();
        ShipSummaryController shipSummaryController = new ShipSummaryController();
        ShipStore shipStore = new ShipStore();
        shipController.importShips("files/TopNTestFile1.csv");

        Ship BOREAS = shipStore.getShip("305373000");
        Ship SEA_BURN = shipStore.getShip("309416000");
        Ship SPAR_ARIES = shipStore.getShip("257881000");

        String vesselType = "70";

        List<Ship> ships = new ArrayList<>();
        ships.add(BOREAS);
        ships.add(SPAR_ARIES);
        ships.add(SEA_BURN);

        LinkedHashMap<String, List<Ship>> linkedHashMapExpected = new LinkedHashMap<>();
        linkedHashMapExpected.put(vesselType, ships);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime START = LocalDateTime.parse("28/12/2020 01:00", formatter);
        LocalDateTime END = LocalDateTime.parse("31/12/2020 23:59", formatter);


        LinkedHashMap<String, List<Ship>> linkedHashMapActual = App.getInstance().getCompany().getShipStore().getTopNShips(3, START, END);

        assertFalse(linkedHashMapExpected.equals(linkedHashMapActual));
    }

    @Test
    public void testGetPairsOfShips(){
        App.getInstance().getCompany().getShipStore().clearShipList();
        ShipController shipController = new ShipController();
        shipController.importShips("files/sships.csv");
        AbstractMap<Ship, HashMap<Double, Ship>> actual = new LinkedHashMap<>();
        LinkedHashMap<Ship, HashMap<Double, Ship>> expected = new LinkedHashMap<>();

        LinkedHashMap<Double, Ship> linkedHashMap1 = new LinkedHashMap();
        linkedHashMap1.put(4.324050000000057, App.getInstance().getCompany().getShipStore().getShip("305373000"));
        expected.put(App.getInstance().getCompany().getShipStore().getShip("228339600"), linkedHashMap1);

        LinkedHashMap<Double, Ship> linkedHashMap2 = new LinkedHashMap();
        linkedHashMap2.put(1.6823199999998906, App.getInstance().getCompany().getShipStore().getShip("636019825"));
        expected.put(App.getInstance().getCompany().getShipStore().getShip("256888000"), linkedHashMap2);

        LinkedHashMap<Double, Ship> linkedHashMap3 = new LinkedHashMap();
        linkedHashMap3.put(4.606329999999957, App.getInstance().getCompany().getShipStore().getShip("258692000"));
        expected.put(App.getInstance().getCompany().getShipStore().getShip("257881000"), linkedHashMap3);

        actual = App.getInstance().getCompany().getShipStore().getPairsOfShips();
        assertTrue(expected.equals(actual));
    }
}