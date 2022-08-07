package lapr.project.ui.console;

import lapr.project.controller.CenterOfMassController;
import lapr.project.controller.ShipBuoyancyController;
import lapr.project.ui.console.console_utils.TextUtils;
import lapr.project.ui.console.console_utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static lapr.project.ui.console.console_utils.Utils.print;
import static lapr.project.ui.console.console_utils.Utils.showAndSelectOne;

public class CenterOfMassUI implements Runnable{

    @Override
    public void run() {

        print(TextUtils.ANSI_YELLOW + "\nAbout the Ship:\n");

        double shipSizeX = Double.parseDouble(Utils.readLineFromConsole(TextUtils.ANSI_CYAN + "Type the size X in meters of the Ship. (ex: 150)" + TextUtils.ANSI_BLUE));
        double shipSizeY = Double.parseDouble(Utils.readLineFromConsole(TextUtils.ANSI_CYAN + "Type the size Y in meters of the Ship. (ex: 30)" + TextUtils.ANSI_BLUE));
        double shipSizeZ = Double.parseDouble(Utils.readLineFromConsole(TextUtils.ANSI_CYAN + "Type the size Z in meters of the Ship, without the 'control' bridge. (ex: 20)" + TextUtils.ANSI_BLUE));

        double shipWeight = Double.parseDouble(Utils.readLineFromConsole(TextUtils.ANSI_CYAN + "Type the weight in tonnes of the Ship, without the 'control' bridge. (ex: 8000)" + TextUtils.ANSI_BLUE));

        print(TextUtils.ANSI_YELLOW + "\nAbout the 'Control' Bridge:\n");

        double towerSizeX = Double.parseDouble(Utils.readLineFromConsole(TextUtils.ANSI_CYAN + "Type the size X in meters of the 'Control' Bridge. (ex: 20)" + TextUtils.ANSI_BLUE));
        double towerSizeY = Double.parseDouble(Utils.readLineFromConsole(TextUtils.ANSI_CYAN + "Type the size Y in meters of the 'Control' Bridge. (ex: 25)" + TextUtils.ANSI_BLUE));
        double towerSizeZ = Double.parseDouble(Utils.readLineFromConsole(TextUtils.ANSI_CYAN + "Type the size Z in meters of the 'Control' Bridge. (ex: 20)" + TextUtils.ANSI_BLUE));

        double towerWeight = Double.parseDouble(Utils.readLineFromConsole(TextUtils.ANSI_CYAN + "Type the weight in tonnes of the 'Control' bridge. (ex: 1000)" + TextUtils.ANSI_BLUE));

        List<String> options = new ArrayList<>();
        options.add("stern");
        options.add("middle");
        options.add("bow");
        String option = (String) showAndSelectOne(options, TextUtils.ANSI_YELLOW + "\nFinally about the position of the Bridge:\n");

        double[] centerOfMass = CenterOfMassController.centerOfMassOfVessel(shipSizeX, shipSizeY, shipSizeZ, shipWeight, towerSizeX, towerSizeY, towerSizeZ, towerWeight, option);
        print(TextUtils.ANSI_PURPLE + "\nCenter of Mass X: " + TextUtils.ANSI_CYAN + centerOfMass[0]);
        print(TextUtils.ANSI_PURPLE + "Center of Mass Y: " + TextUtils.ANSI_CYAN + centerOfMass[1]);
        print(TextUtils.ANSI_PURPLE + "Center of Mass Z: " + TextUtils.ANSI_CYAN + centerOfMass[2]);

        int numberOfContainers = Utils.readIntegerFromConsole(TextUtils.ANSI_YELLOW + "\nNow, specify how many containers you want to be placed in the vessel, so the center of mass can be recalculated." + TextUtils.ANSI_CYAN);

        double[] centerOfMass1 = CenterOfMassController.centerOfMassOfVesselWithContainers(shipSizeX, shipSizeY, shipSizeZ, shipWeight, towerSizeX, towerSizeY, towerSizeZ, towerWeight, option, numberOfContainers);
        print(TextUtils.ANSI_PURPLE + "\nCenter of Mass X: " + TextUtils.ANSI_CYAN + centerOfMass1[0]);
        print(TextUtils.ANSI_PURPLE + "Center of Mass Y: " + TextUtils.ANSI_CYAN + centerOfMass1[1]);
        print(TextUtils.ANSI_PURPLE + "Center of Mass Z: " + TextUtils.ANSI_CYAN + centerOfMass1[2]);

        print(TextUtils.ANSI_YELLOW + "\nAbout Ship Buoyancy: " + TextUtils.ANSI_CYAN);
        double[] info = ShipBuoyancyController.calculate(shipSizeX, shipSizeY, shipSizeZ, shipWeight, towerWeight, numberOfContainers);
        print(TextUtils.ANSI_YELLOW + "Pressure: " + TextUtils.ANSI_CYAN + info[0] + " bar");
        print(TextUtils.ANSI_YELLOW + "Total mass placed on the vessel: " + TextUtils.ANSI_CYAN + info[1] + " T");
        print(TextUtils.ANSI_YELLOW + "Height difference: " + TextUtils.ANSI_CYAN + info[2] + " m");
    }
}
