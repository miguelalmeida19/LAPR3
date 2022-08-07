package lapr.project.controller;

import lapr.project.model.ShipSummary;
import lapr.project.store.ShipStore;
import java.util.Map;

public class ShipSummaryController {
    private final ShipStore shipStore;

    public ShipSummaryController() {
        shipStore = App.getInstance().getCompany().getShipStore();
    }

    /**
     * This method calls the necessary method of the store to create the summary of the ship.
     * @param code code needed to create the summary. MMSI, IMO or CALL SIGN
     * @return a map with the summary of the ship.
     */
    public Map<ShipSummary, Object> shipSummary(String code) {
        return shipStore.shipSummary(code);
    }



}


