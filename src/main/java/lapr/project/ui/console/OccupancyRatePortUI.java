package lapr.project.ui.console;

import lapr.project.data.ImportFromDataBase;
import lapr.project.ui.console.console_utils.TextUtils;
import lapr.project.ui.console.console_utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static lapr.project.ui.console.console_utils.Utils.print;

public class OccupancyRatePortUI implements Runnable {

    @Override
    public void run() {


        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("January", 1));
        options.add(new MenuItem("February", 2));
        options.add(new MenuItem("March", 3));
        options.add(new MenuItem("April", 4));
        options.add(new MenuItem("May", 5));
        options.add(new MenuItem("June", 6));
        options.add(new MenuItem("July", 7));
        options.add(new MenuItem("August", 8));
        options.add(new MenuItem("September", 9));
        options.add(new MenuItem("October", 10));
        options.add(new MenuItem("November", 11));
        options.add(new MenuItem("December", 12));


        int option = 0;

        while (option != -1) {
            option = Utils.showAndSelectIndex(options, "\n\nPlease, enter the month to search for occupancy rate: ");

            if ((option >= 0) && (option < options.size())) {
                getOccupancyRate(options.get(option).getMonth());
                new PortManagerUI().run();
            }
        }
    }

    public static void getOccupancyRate(int month) {

        if (month != 0) {
            print(TextUtils.ANSI_PURPLE + "\nThe occupancy Rate of the Port:\n");
            String list = ImportFromDataBase.getOccupancyRateOfEachWarehouse(month);

            String[] array = list.split("\n");
            print(String.format("%-15s%20s", TextUtils.ANSI_CYAN + "WAREHOUSEID", TextUtils.ANSI_BLUE + "OCCUPANCY RATE"));
            for (String arr : array) {
                print(TextUtils.ANSI_WHITE + "▬▬ι═════════❖══════════ι▬▬");
                print(String.format("%-15s%20s", TextUtils.ANSI_CYAN + arr.split(":")[0], TextUtils.ANSI_BLUE + arr.split(":")[1]));
            }
        }
    }
}
