package lapr.project.ui.console;

import lapr.project.ui.console.console_utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class FleetManagerUI implements Runnable {

    public FleetManagerUI(){
        // Explain why this is empty
    }

    public void run() {


        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Idle days of ship", new IdleDays()));
        options.add(new MenuItem("Average Occupancy Rate", new AverageOccuppancyRateUI()));
        options.add(new MenuItem("Average Occupancy Rate of Voyage", new AverageOccuppancyRateVoyageUI()));

        int option;
        do
        {
            option = Utils.showAndSelectIndex(options, "\n\nFleet Manager Menu:");

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }
        }
        while (option != -1 );
    }
}
