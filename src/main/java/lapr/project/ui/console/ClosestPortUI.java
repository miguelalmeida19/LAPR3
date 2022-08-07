package lapr.project.ui.console;

import lapr.project.controller.ClosestPortController;
import lapr.project.model.Port;
import lapr.project.ui.console.console_utils.TextUtils;
import lapr.project.ui.console.console_utils.Utils;

import java.time.LocalDateTime;

import static lapr.project.ui.console.console_utils.Utils.print;

public class ClosestPortUI implements Runnable {

    private final ClosestPortController c = new ClosestPortController();

    public ClosestPortUI() {
        // Explain why this is empty
    }

    @Override
    public void run() {
        String callSign = Utils.readLineFromConsole("Please, enter the callSign of the Ship: ");
        LocalDateTime time = Utils.readDateFromConsole("Please enter the date you want to use: ");
        try {
            Port p = c.closestPort(callSign, time);
            if (p != null) {
                print(TextUtils.ANSI_CYAN + "[*] Port found." + TextUtils.ANSI_RESET);
                print(TextUtils.ANSI_WHITE + "   -Port name: " + p.getPort() + TextUtils.ANSI_RESET);
                print(TextUtils.ANSI_WHITE + "   -Port Coordinates: " + p.getLatitude() + ", " + p.getLongitude() + TextUtils.ANSI_RESET);
                print(TextUtils.ANSI_WHITE + "   -Port code: " + p.getCode() + TextUtils.ANSI_RESET);
                print(TextUtils.ANSI_WHITE + "   -Country/Continent: " + p.getCountry() + "/" + p.getContinent() + TextUtils.ANSI_RESET);
                print("\n");
            } else {
                print(TextUtils.ANSI_RED + "[!] No port was found. Please check if the callSign is correct or if all the data needed is imported into the system.." + TextUtils.ANSI_RESET);
            }
        } catch (Exception e) {
            print(TextUtils.ANSI_RED + "[!] " + e.getMessage() + TextUtils.ANSI_RESET);
        }

        new TrafficManagerUI().run();
    }
}
