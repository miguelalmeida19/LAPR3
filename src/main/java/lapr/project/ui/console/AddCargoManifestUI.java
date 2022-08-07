package lapr.project.ui.console;

import lapr.project.data.ImportFromDataBase;
import lapr.project.ui.console.console_utils.TextUtils;

import static lapr.project.ui.console.console_utils.Utils.*;

public class AddCargoManifestUI implements Runnable {
    @Override
    public void run() {
        print(TextUtils.ANSI_PURPLE + "\nPlease enter the following information so you can add a new Cargo Manifest to the System" + TextUtils.ANSI_BLUE);
        int vehicleId = readIntegerFromConsole("VEHICLEID:");
        String warehouseId = readLineFromConsole("WAREHOUSEID:");
        String baseDateTime = readLineFromConsole("BASE DATE TIME [yyyy-mm-dd hh:mm]:");
        String type = readLineFromConsole("TYPE:");

        String response = ImportFromDataBase.addCargoManifest(vehicleId, warehouseId, baseDateTime, type);
        if (response.contains("Ship") || response.contains("Warehouse")){
            print("\n" + TextUtils.ANSI_RED + response.split(":")[1].split("!")[0].split("ORA")[0].replace("\n", "!"));
        }
        else {
            print(TextUtils.ANSI_GREEN + "\nSUCCESS!");
        }
    }
}