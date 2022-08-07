package lapr.project.ui.console;

import lapr.project.data.ImportFromDataBase;
import lapr.project.ui.console.console_utils.TextUtils;

import static lapr.project.ui.console.console_utils.Utils.*;

public class AverageOccuppancyRateUI implements Runnable {
    @Override
    public void run() {
        print(TextUtils.ANSI_CYAN + "\nYou are about to see the average occupancy rate per manifest of a given ship during a given period:" + TextUtils.ANSI_BLUE);
        String shipImo = readLineFromConsole(TextUtils.ANSI_WHITE + "Type ShipIMO:" + TextUtils.ANSI_BLUE);
        String start = readLineFromConsole(TextUtils.ANSI_WHITE + "Type the start date [YYYY-MM-DD HH:MM]" + TextUtils.ANSI_BLUE);
        String end = readLineFromConsole(TextUtils.ANSI_WHITE + "Type the end date [YYYY-MM-DD HH:MM]" + TextUtils.ANSI_BLUE);
        print(TextUtils.ANSI_PURPLE + "\nOccupancy Rate: " + TextUtils.ANSI_CYAN + ImportFromDataBase.getAverageOccupancyRate(shipImo, start, end));
    }
}
