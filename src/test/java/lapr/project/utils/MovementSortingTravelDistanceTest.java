package lapr.project.utils;

import lapr.project.controller.App;
import lapr.project.controller.ShipController;
import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.store.ShipStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class MovementSortingTravelDistanceTest {

    public Company company;
    public ShipStore shipStore;
    public ShipController shipController = new ShipController();
    public MovementSortingTravelDistance movementSortingTravelDistance = new MovementSortingTravelDistance();

    @BeforeEach
    public void setUp() throws Exception {
        company = App.getInstance().getCompany();
        shipStore = company.getShipStore();
        shipController.importShips("files/testShipTest.csv");
    }

    @Test
    public void compare() {
        Ship ship = shipStore.getShip("211331642");
        Ship ship1 = shipStore.getShip("211331641");

        assert movementSortingTravelDistance.compare(ship, ship1) > 0;
    }

    @Test
    public void compare1() {
        Ship ship = shipStore.getShip("211331642");
        Ship ship1 = shipStore.getShip("211331641");

        assert movementSortingTravelDistance.compare(ship1, ship) < 0;
    }

    @Test
    public void compare2() {
        Ship ship = shipStore.getShip("211331642");
        Ship ship1 = shipStore.getShip("211331643");

        assert movementSortingTravelDistance.compare(ship1, ship) < 0;
    }
    @Test
    public void compare3() {
        Ship ship = shipStore.getShip("211331642");
        Ship ship1 = shipStore.getShip("211331643");

        assert movementSortingTravelDistance.compare(ship, ship1) > 0;
    }
}