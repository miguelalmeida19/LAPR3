package lapr.project.ui.console;

import lapr.project.data.ImportFromDataBase;
import lapr.project.ui.console.console_utils.TextUtils;

import static lapr.project.ui.console.console_utils.Utils.print;

public class ContainersLeavingWarehouseUI implements Runnable {
    @Override
    public void run() {
        print(TextUtils.ANSI_PURPLE + "\nContainers Leaving Warehouses in the next 30 days:\n");
        String list = ImportFromDataBase.getEstimateOfContainersLeaving();

        String[] array = list.split("Info about each container:")[0].split("\n");
        String info = list.split("Info about each container:")[1];
        print(String.format("%-15s%20s", TextUtils.ANSI_CYAN + "WAREHOUSEID", TextUtils.ANSI_BLUE + "CONTAINERS"));
        for (String arr : array) {
            print(TextUtils.ANSI_WHITE + "▬▬ι═════════❖══════════ι▬▬");
            print(String.format("%-15s%20s", TextUtils.ANSI_CYAN + arr.split(":")[0], TextUtils.ANSI_BLUE + arr.split(":")[1].replace("%", "")));
        }
        print(TextUtils.ANSI_PURPLE + "\nInfo about each container:");
        print(TextUtils.ANSI_WHITE + info);
    }
}
