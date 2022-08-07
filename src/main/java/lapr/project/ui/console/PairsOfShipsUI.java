package lapr.project.ui.console;

import lapr.project.controller.ShipController;
import lapr.project.model.Ship;
import lapr.project.ui.console.console_utils.TextUtils;

import java.util.HashMap;
import java.util.Map;

import static lapr.project.ui.console.console_utils.Utils.print;


public class PairsOfShipsUI implements Runnable{
    private final ShipController shipController = new ShipController();

    @Override
    public void run() {
        try {
            print(TextUtils.ANSI_PURPLE + "This is the list of the pairs of Ships with routes with close departure/arrival coordinates and with different Travelled Distance." + TextUtils.ANSI_BLUE + "\n");
            HashMap<Ship, HashMap<Double, Ship>> map = (HashMap<Ship, HashMap<Double, Ship>>) shipController.getPairsOfShips();
            print("----------------------------------------------------------------------------------------------" + TextUtils.ANSI_PURPLE);
            print(String.format("%-20s %-20s %-20s", "SHIP 1 MMSI", "SHIP 2 MMSI", "DIFFERENCE TRAVELED DISTANCE"));
            print();
            print(TextUtils.ANSI_BLUE + "----------------------------------------------------------------------------------------------");
            for (Map.Entry<Ship, HashMap<Double, Ship>> s: map.entrySet()){
                int index = 0;
                for (Ship s1 : s.getValue().values()){
                    print(String.format("%-20s %-20s %-20s",
                            s.getKey().getMMSI(), s1.getMMSI(), s.getValue().keySet().toArray()[index]));
                    print();
                    index++;
                }
            }
        }
        catch (Exception ignored){
        }
    }
}