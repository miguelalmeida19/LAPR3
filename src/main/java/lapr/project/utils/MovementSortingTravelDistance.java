package lapr.project.utils;

import lapr.project.controller.App;
import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.model.ShipSummary;
import lapr.project.store.ShipStore;

import java.util.Comparator;

public class MovementSortingTravelDistance implements Comparator<Ship> {

    private final ShipStore shipStore;

    public MovementSortingTravelDistance(){
        Company company = App.getInstance().getCompany();
        shipStore = company.getShipStore();
    }
    @Override
    public int compare(Ship o1, Ship o2) {
        return ((Double) shipStore.shipSummary(String.valueOf(o2.getMMSI())).get(ShipSummary.TRAVELED_DISTANCE)).compareTo((Double) shipStore.shipSummary(String.valueOf(o1.getMMSI())).get(ShipSummary.TRAVELED_DISTANCE));
    }
}
