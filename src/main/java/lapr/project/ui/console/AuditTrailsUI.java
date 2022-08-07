package lapr.project.ui.console;

import lapr.project.controller.AuditTrailsController;
import lapr.project.ui.console.console_utils.TextUtils;
import lapr.project.ui.console.console_utils.Utils;

import java.util.List;

import static lapr.project.ui.console.console_utils.Utils.print;

public class AuditTrailsUI implements Runnable {

    private final AuditTrailsController controller = new AuditTrailsController();

    @Override
    public void run() {

        int cargoManifestId = Utils.readIntegerFromConsole("Please, enter the Cargo Manifest ID: ");
        int containerID = Utils.readIntegerFromConsole("Please, enter the Container ID: ");

        try {
            List<String> results = controller.getAuditTrails(cargoManifestId, containerID);

            for(String str : results){
                print(TextUtils.ANSI_PURPLE + str + TextUtils.ANSI_RESET);
            }

        } catch (Exception e) {
            Utils.print(e.getMessage());
        }

        new ShipCaptainUI().run();
    }
}
