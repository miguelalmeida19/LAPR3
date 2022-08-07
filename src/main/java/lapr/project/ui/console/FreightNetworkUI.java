package lapr.project.ui.console;

import lapr.project.controller.FreightNetworkController;
import lapr.project.ui.console.console_utils.TextUtils;
import lapr.project.ui.console.console_utils.Utils;

import static lapr.project.ui.console.console_utils.Utils.print;

public class FreightNetworkUI implements Runnable{
    private final FreightNetworkController freightNetworkController = new FreightNetworkController();

    @Override
    public void run() {
        print(TextUtils.ANSI_CYAN + "You are about to build a new Freight Network and replace the last one." + TextUtils.ANSI_BLUE);
        int option;
        do {
            option = Utils.readIntegerFromConsole(TextUtils.ANSI_GREEN + "[TYPE 1 TO CONTINUE] " + TextUtils.ANSI_RED + "[TYPE 0 TO CANCEL]" + TextUtils.ANSI_BLUE + "\n");
        }while (option!=1 && option!=0);
        if (option==0){
            new TrafficManagerUI().run();
        }
        int n = Utils.readIntegerFromConsole("How many closest ports you want to be connected with each port of a country?");

        print(TextUtils.ANSI_YELLOW + "\nBuilding Freight Network...");

        freightNetworkController.build(n);

        print(TextUtils.ANSI_GREEN + "\nSuccess!");
        print(TextUtils.ANSI_WHITE + "[You can checkout the 'matrix.csv' file in the package 'files' to see the representation of the freight network!]");
    }
}
