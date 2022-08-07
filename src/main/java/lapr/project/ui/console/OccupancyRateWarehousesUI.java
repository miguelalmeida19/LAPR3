package lapr.project.ui.console;

import lapr.project.data.ImportFromDataBase;
import lapr.project.ui.console.console_utils.TextUtils;

import static lapr.project.ui.console.console_utils.Utils.print;

public class OccupancyRateWarehousesUI implements Runnable{
    @Override
    public void run() {
        print(TextUtils.ANSI_PURPLE + "\nThe occupancy Rate of Each Warehouse currently is:\n");
        String list = ImportFromDataBase.getOccupancyRateOfEachWarehouse();

        String[] array = list.split("\n");
        print(String.format("%-15s%20s", TextUtils.ANSI_CYAN + "WAREHOUSEID", TextUtils.ANSI_BLUE + "OCCUPANCY RATE"));
        for (String arr: array){
            print(TextUtils.ANSI_WHITE + "▬▬ι═════════❖══════════ι▬▬");
            print(String.format("%-15s%20s", TextUtils.ANSI_CYAN + arr.split(":")[0],TextUtils.ANSI_BLUE + arr.split(":")[1]));
        }
    }
}
