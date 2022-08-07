package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.model.ShipSummary;
import lapr.project.store.ShipStore;
import lapr.project.utils.MovementSortingTotalMovements;
import lapr.project.utils.MovementSortingTravelDistance;

import java.util.*;

public class MovementsController {

    private final ShipStore shipStore;
    private final List<Ship> shipList;

    /**
     * Constructor
     */
    public MovementsController() {
        Company company = App.getInstance().getCompany();
        shipStore = company.getShipStore();
        shipList = company.getShipStore().getShipList();
    }

    /**
     * Method that sorts lists for all ships the MMSI, the total number of movements, Travelled Distance and Delta Distance.
     * Acceptance criteria [ESINF]: ordered by Travelled Distance and total number of movements (descending/ascending).
     * @return LinkedHashMap with all information sorted
     */
    public LinkedHashMap<Ship, List<String>> getData() {

        ArrayList<Ship> movements = new ArrayList<>(shipList);
        movements.sort(new MovementSortingTravelDistance());
        ArrayList<Ship> temporary = new ArrayList<>();
        ArrayList<Ship> finish = new ArrayList<>();

        for (int i = 0; i < movements.size() - 1; i++) {
            if (shipStore.shipSummary(String.valueOf(movements.get(i).getMMSI())).get(ShipSummary.TRAVELED_DISTANCE).equals(shipStore.shipSummary(String.valueOf(movements.get(i + 1).getMMSI())).get(ShipSummary.TRAVELED_DISTANCE))) {
                temporary.add(movements.get(i));
                if(i+1 == movements.size()-1){
                    temporary.add(movements.get(i+1));
                }

            } else if (temporary.size() == 0) {
                finish.add(movements.get(i));

            }
        }
        temporary.sort(new MovementSortingTotalMovements());
        finish.addAll(temporary);

        LinkedHashMap<Ship, List<String>> hashMap = new LinkedHashMap<>();
        for (Ship ship : finish){
            List<String> informations = new ArrayList<>();
            informations.add(String.valueOf(ship.getMMSI()));
            informations.add(String.valueOf(shipStore.shipSummary(String.valueOf(ship.getMMSI())).get(ShipSummary.TOTAL_NUMBER_MOVEMENTS)));
            informations.add(String.valueOf(shipStore.shipSummary(String.valueOf(ship.getMMSI())).get(ShipSummary.TRAVELED_DISTANCE)));
            informations.add(String.valueOf(shipStore.shipSummary(String.valueOf(ship.getMMSI())).get(ShipSummary.DELTA_DISTANCE)));
            hashMap.put(ship, informations);
        }
        return hashMap;
    }
}
