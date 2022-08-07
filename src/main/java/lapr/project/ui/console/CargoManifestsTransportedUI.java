package lapr.project.ui.console;

import lapr.project.controller.CargoManifestsTransportedController;
import lapr.project.ui.console.console_utils.TextUtils;
import lapr.project.ui.console.console_utils.Utils;

import static lapr.project.ui.console.console_utils.Utils.print;

public class CargoManifestsTransportedUI implements Runnable {

    private final CargoManifestsTransportedController controller = new CargoManifestsTransportedController();

    @Override
    public void run() {

        try{
            controller.getID();

            String year = Utils.readLineFromConsole("Please, enter the year to search for cargo manifests: ");

            controller.getCargoManifestTransported(year);

            if (controller.getContManifests() != 0) {
                print(TextUtils.ANSI_WHITE + "\n[*] Amount of Cargo Manifests in " + year + ": " + controller.getContManifests() + TextUtils.ANSI_RESET);
                print(TextUtils.ANSI_WHITE + "\n[*] Average amount of Containers per Cargo Manifests in " + year + ": " + controller.getAverage() + TextUtils.ANSI_RESET);
                print("\n");

            } else {
                throw new IllegalArgumentException("[!] No cargo manifests were found for the given year.");
            }

        } catch (Exception e) {
            Utils.print(e.getMessage());
        }

        new ShipCaptainUI().run();
    }
}

