package lapr.project.utils;

import lapr.project.controller.App;
import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.store.ShipStore;

import java.util.Comparator;

public class MMSISorter implements Comparator<Ship> {
    private final ShipStore shipStore;

    /**
     * Constructor
     */
    public MMSISorter(){
        Company company = App.getInstance().getCompany();
        shipStore = company.getShipStore();
    }

    /**
     * Compares 2 MMSI from 2 Ships and orders them
     */
    @Override
    public int compare(Ship o1, Ship o2) {
        return Integer.compare(o1.getMMSI(), o2.getMMSI());
    }
}
