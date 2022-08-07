package lapr.project.ui.console;
import lapr.project.ui.console.console_utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class PortManagerUI implements Runnable {

    public void run() {

        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Occupancy Rate of each Warehouse", new OccupancyRateWarehousesUI()));
        options.add(new MenuItem("Containers Leaving Warehouse", new ContainersLeavingWarehouseUI()));
        options.add(new MenuItem("Add new Cargo Manifest", new AddCargoManifestUI()));
        options.add(new MenuItem("Add new Cargo Manifest Container", new AddCargoManifestContainerUI()));
        options.add(new MenuItem("Occupancy Rate of Port", new OccupancyRatePortUI()));
        options.add(new MenuItem("Loading and Unloading Map for next Week", new CMNextWeekUI()));

        int option;
        do
        {
            option = Utils.showAndSelectIndex(options, "\n\nPort Manager Menu:");

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }
        }
        while (option != -1 );
    }
}