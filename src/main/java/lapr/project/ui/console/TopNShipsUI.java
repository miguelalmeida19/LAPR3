package lapr.project.ui.console;

import lapr.project.controller.ShipController;
import lapr.project.controller.ShipSummaryController;
import lapr.project.model.Ship;
import lapr.project.model.ShipSummary;
import lapr.project.ui.console.console_utils.TextUtils;
import lapr.project.ui.console.console_utils.Utils;

import java.time.LocalDateTime;
import java.util.*;

import static lapr.project.ui.console.console_utils.Utils.print;

public class TopNShipsUI implements Runnable {
    private final ShipSummaryController shipSummaryController = new ShipSummaryController();
    private final ShipController shipController = new ShipController();


    public TopNShipsUI() {
        // Explain why this is empty
    }

    public void run() {
        try {
            int n;
            n = Utils.readIntegerFromConsole("How many ships you want to be placed in the Top list?");
            LocalDateTime startL;
            startL = Utils.readDateFromConsole("\nPlease enter the start date of the time delta you want.");
            LocalDateTime endL;
            endL = Utils.readDateFromConsole("\nPlease enter the start date of the time delta you want.");
            print("\n");
            print("▛▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝ ▜\n");
            print("\t\t\t\tTop " + TextUtils.ANSI_RED + n + TextUtils.ANSI_BLUE + " ships:\n");
            int index = 0;

            Collection<List<Ship>> listOfLists = shipController.getTopNShips(n, startL, endL).values();
            for (List<Ship> list : listOfLists) {
                print(TextUtils.ANSI_GREEN + "Vessel Type: " + TextUtils.ANSI_YELLOW + ((Ship) ((List<?>) listOfLists.toArray()[index]).get(0)).getVesselType() + TextUtils.ANSI_BLUE);
                print("\n" + String.format("%-30s %-30s %-30s", "SHIP NAME", "TRAVELED DISTANCE", "MEAN SOG"));
                for (Ship s : list) {
                    Map<ShipSummary, Object> map1 = shipSummaryController.shipSummary(String.valueOf(s.getMMSI()));
                    print(TextUtils.ANSI_RED);
                    print(String.format("%-30s %-30s %-30s", s.getName(), map1.get(ShipSummary.TRAVELED_DISTANCE) + " km", map1.get(ShipSummary.MEAN_SOG) + " km/h"));
                }
                index++;
                print(TextUtils.ANSI_PURPLE + "▬▬ι══════════════ι▬▬" + TextUtils.ANSI_BLUE);
            }
            print("▙▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝ ▟\n");
            Utils.print("\n******************************\nSuccess!");
        } catch (Exception e) {
            Utils.print(e.getMessage());
        }
    }
}