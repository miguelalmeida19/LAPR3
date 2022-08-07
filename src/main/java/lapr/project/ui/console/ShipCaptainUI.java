package lapr.project.ui.console;

import lapr.project.ui.console.console_utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ShipCaptainUI implements Runnable {
    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Containers to be loaded/offloaded", new LoadedOffloadedContainersUI()));
        options.add(new MenuItem("Amount of Cargo Manifests in a given year", new CargoManifestsTransportedUI()));
        options.add(new MenuItem("Occupancy Rate of a Ship", new OccupancyRateUI()));
        options.add(new MenuItem("Create the crew account.", new CreateCrewAccountUI()));
        options.add(new MenuItem("Audit Trails", new AuditTrailsUI()));
        options.add(new MenuItem("Know the ammount of generators needed for a trip", new US415UI()));
        options.add(new MenuItem("Center of Mass", new CenterOfMassUI()));

        int option;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nShip Captain Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        }
        while (option != -1);
    }
}