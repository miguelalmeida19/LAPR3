package lapr.project.ui.console;

import lapr.project.controller.MovementsController;
import lapr.project.model.Ship;
import lapr.project.ui.console.console_utils.TextUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static lapr.project.ui.console.console_utils.Utils.print;

public class MovementsUI implements Runnable {
    private final MovementsController controller = new MovementsController();

    public void run() {
        try {
            LinkedHashMap<Ship, List<String>> list = controller.getData();
            for (Map.Entry<Ship, List<String>> ship : list.entrySet()) {
                print(TextUtils.ANSI_RED + "--------------------------------------------------" + TextUtils.ANSI_WHITE);
                print("For the ship with the MMSI: " + TextUtils.ANSI_CYAN + ship.getValue().get(0));
                print(TextUtils.ANSI_RED + "--------------------------------------------------" + TextUtils.ANSI_BLUE);
                print("Number of movements: " + TextUtils.ANSI_CYAN + ship.getValue().get(1) + TextUtils.ANSI_BLUE);
                print("Travelled Distance: " + TextUtils.ANSI_CYAN + ship.getValue().get(2) + TextUtils.ANSI_BLUE + "\n");
                print("Delta Distance: " + TextUtils.ANSI_CYAN + ship.getValue().get(3) + TextUtils.ANSI_BLUE + "\n");
            }
            print("\n******************************\nSuccess!");
        } catch (Exception e) {
            print(e.getMessage());
        }
    }
}