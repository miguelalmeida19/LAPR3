package lapr.project.utils;

import lapr.project.controller.App;
import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.model.ShipSummary;
import lapr.project.store.ShipStore;

import java.util.Comparator;

public class MovementSortingTotalMovements implements Comparator<Ship> {

    private final ShipStore shipStore;

    /**
     * Constructor
     */
    public MovementSortingTotalMovements() {
        Company company = App.getInstance().getCompany();
        shipStore = company.getShipStore();
    }

    /**
     * Compares the total number of movements
     * @param o1 - Ship 1
     * @param o2 - Ship 2
     * @return - Result
     */
    @Override
    public int compare(Ship o1, Ship o2) {
        return Integer.compare((int) shipStore.shipSummary(String.valueOf(o1.getMMSI())).get(ShipSummary.TOTAL_NUMBER_MOVEMENTS), (int) shipStore.shipSummary(String.valueOf(o2.getMMSI())).get(ShipSummary.TOTAL_NUMBER_MOVEMENTS));
    }
}
