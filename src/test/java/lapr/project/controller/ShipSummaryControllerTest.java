package lapr.project.controller;
import lapr.project.model.ShipSummary;
import lapr.project.store.ShipStore;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShipSummaryControllerTest {

    @Test
    public void shipSummary() {
       ShipController s = new ShipController();
       s.importShips("files/testShip.csv");
       ShipSummaryController s1 = new ShipSummaryController();
       String code = (String)s1.shipSummary("211331640").get(ShipSummary.CODE);
        assertTrue(code.equals("211331640"));
    }
    @Test
    public void shipSummaryNotTnull() {
       ShipStore shipStore = App.getInstance().getCompany().getShipStore();
       ShipController s = new ShipController();
       s.importShips("files/testShip.csv");
        ShipSummaryController s1 = new ShipSummaryController();

        assertTrue(null != s1.shipSummary("211331640"));

    }
}