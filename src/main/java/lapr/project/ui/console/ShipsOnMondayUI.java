package lapr.project.ui.console;

import lapr.project.controller.ShipsOnMondayController;
import lapr.project.ui.console.console_utils.TextUtils;
import lapr.project.ui.console.console_utils.Utils;

import java.util.List;

import static lapr.project.ui.console.console_utils.Utils.print;

public class ShipsOnMondayUI implements Runnable {

    private final ShipsOnMondayController controller = new ShipsOnMondayController();

    @Override
    public void run() {

        try {
            List<String> results = controller.getBoats();

            for(String str : results){
                print(TextUtils.ANSI_PURPLE + str + TextUtils.ANSI_RESET);
            }

        } catch (Exception e) {
            Utils.print(e.getMessage());
        }

        new TrafficManagerUI().run();
    }
}
