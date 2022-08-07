package lapr.project.structures;

import lapr.project.model.Ship;
import lapr.project.store.ShipStore;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BSTTest {
    public Ship ship;
    public ShipStore shipStore = new ShipStore();
    public BST bstMMSI;
    public BST bstIMO;
    public BST bstCallSign;

    @Before
    public void setUp() {
        ship = new Ship(123456789, "CargoFest", "IMO1234567", "123XY", "1234", 5.5, 4.5, 100.3, "100");
        Ship ship2 = new Ship(123456782, "CargoFest", "IMO1234568", "123XZ", "1234", 5.5, 4.5, 100.3, "100");
        Ship ship3 = new Ship(123456785, "CargoFest", "IMO1234569", "123XX", "1234", 5.5, 4.5, 100.3, "100");
        shipStore.addShip(ship);
        shipStore.addShip(ship2);
        shipStore.addShip(ship3);
        bstMMSI = shipStore.getBstMMSI();
        bstIMO = shipStore.getBstIMO();
        bstCallSign = shipStore.getBstCallSign();
    }

    @Test
    public void getByCallSign() {
        Assert.assertEquals(ship, bstCallSign.search("123XY"));
    }

    @Test
    public void getByCallSignWrong() {
        Assert.assertNotEquals(ship, bstCallSign.search("123YY"));
    }

    @Test
    public void getByMMSI() {
        Assert.assertEquals(ship, bstMMSI.search(123456789));
    }

    @Test
    public void getByMMSIWrong() {
        Assert.assertNotEquals(ship, bstMMSI.search(123456799));
    }

    @Test
    public void getByIMO() {
        Assert.assertEquals(ship, bstIMO.search("IMO1234567"));
    }

    @Test
    public void getByIMOWrong() {
        Assert.assertNotEquals(ship, bstIMO.search("IMO1224567"));
    }

    @Test
    public void put() {
        Ship ship1 = new Ship(123456289, "CargoFest", "IMO1234567", "123XY", "1234", 5.5, 4.5, 100.3, "100");
        bstMMSI.put(123456289, ship1);
        Assert.assertEquals(ship1, bstMMSI.search(123456289));
    }
}