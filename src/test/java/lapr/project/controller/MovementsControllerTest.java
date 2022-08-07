package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.model.ShipSummary;
import lapr.project.store.ShipStore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class MovementsControllerTest {

    public Company company;
    public ShipStore shipStore;
    public ShipController shipController = new ShipController();
    public MovementsController movementsController = new MovementsController();


    @BeforeEach
    public void setUp() {

        company = App.getInstance().getCompany();
        shipStore = company.getShipStore();
        shipController.importShips("files/testShipTest.csv");
    }

    @Test
    public void testConstructor() {
        assertFalse((new MovementsController()).getData().isEmpty());
    }

    @Test
    public void testGetData() {
        assertFalse((new MovementsController()).getData().isEmpty());
    }

    @Test
    public void checkDescendingOrderTravelledDistanceCorrect() {
        System.out.println("Check if the List is in Descending Order per Travelled Distance");
        LinkedHashMap<Ship, List<String>> hashMap = movementsController.getData();

        ArrayList<Double> Codes = new ArrayList<>();
        Double Cod1 = 3558.13752;
        Double Cod2 = 2657.35103;
        Double Cod3 = 26.36737;
        Codes.add(Cod1);
        Codes.add(Cod2);
        Codes.add(Cod3);
        int i = 0;

        for (Map.Entry<Ship, List<String>>  m : hashMap.entrySet()) {
            Ship ship = m.getKey();
            assert Codes.get(i).equals(shipStore.shipSummary(String.valueOf(ship.getMMSI())).get(ShipSummary.TRAVELED_DISTANCE));
            i++;
        }
    }

    @Test
    public void checkDescendingOrderTravelledDistanceIncorrect() {
        System.out.println("Check if the List is in Descending Order per Travelled Distance");
        LinkedHashMap<Ship, List<String>> hashMap = movementsController.getData();

        ArrayList<Double> Codes = new ArrayList<>();
        Double Cod1 = 3558.13752;
        Double Cod2 = 2657.35103;
        Double Cod3 = 26.36737;
        Codes.add(Cod3);
        Codes.add(Cod1);
        Codes.add(Cod2);
        int i = 0;

        for (Map.Entry <Ship, List<String>> m : hashMap.entrySet()) {
            Ship ship = m.getKey();
            assert !Codes.get(i).equals(shipStore.shipSummary(String.valueOf(ship.getMMSI())).get(ShipSummary.TRAVELED_DISTANCE));
            i++;
        }
    }

    @Test
    public void checkDescendingOrderTotalMovementsCorrect() {
        System.out.println("Check if the List is in Ascending Order per Total Movements Distance");
        LinkedHashMap<Ship, List<String>> hashMap = movementsController.getData();

        ArrayList<String> Codes = new ArrayList<>();
        String Cod1 = "211331641";
        String Cod2 = "211331642";
        String Cod3 = "211331643";
        Codes.add(Cod3);
        Codes.add(Cod1);
        Codes.add(Cod2);
        int i = 0;

        for (Map.Entry <Ship, List<String>> m : hashMap.entrySet()) {
            Ship ship = m.getKey();
            assert Codes.get(i).equals(String.valueOf(ship.getMMSI()));
            i++;
        }
    }

    @Test
    public void checkDescendingOrderTotalMovementsIncorrect() {
        shipStore.clearShipList();
        System.out.println("Check if the List is in Ascending Order per Total Movements Distance");
        LinkedHashMap<Ship, List<String>> hashMap = movementsController.getData();

        ArrayList<String> Codes = new ArrayList<>();
        String Cod1 = "211331641";
        String Cod2 = "211331642";
        String Cod3 = "211331643";
        Codes.add(Cod1);
        Codes.add(Cod2);
        Codes.add(Cod3);
        int i = 0;

        for (Map.Entry <Ship, List<String>> m : hashMap.entrySet()) {
            Ship ship = m.getKey();
            assert !Codes.get(i).equals(String.valueOf(ship.getMMSI()));
            i++;
        }
    }
}