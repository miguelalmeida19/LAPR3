package lapr.project.ui.console;

import lapr.project.controller.CMNextWeekController;
import lapr.project.ui.console.console_utils.TextUtils;
import lapr.project.ui.console.console_utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static lapr.project.ui.console.console_utils.Utils.print;

public class CMNextWeekUI implements Runnable {

    private static final CMNextWeekController controller = new CMNextWeekController();
    static final String LINE = "--------------------------------------------------";
    static final String BAR = "  |  ";

    @Override
    public void run() {

        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Loaded", 1));
        options.add(new MenuItem("Offloaded", 2));

        int option = 0;

        while (option != -1) {
            option = Utils.showAndSelectIndex(options, "\n\nPlease, enter the option: ");

            if ((option >= 0) && (option < options.size())) {
                getOccupancyRate(options.get(option).getMonth());
            }
        }
    }

    public static void getOccupancyRate(int option) {

        List<String> map;

        print(TextUtils.ANSI_PURPLE + "\nThe Loading/Unloading Map for next week:\n");

        try {

            if (option == 1) {
                map = controller.getMapLoading();

            } else {
                map = controller.getMapUnloading();
            }

            for (String a : map) {
                String[] split = a.split("#");

                print(TextUtils.ANSI_RED + LINE + TextUtils.ANSI_RESET);
                print(TextUtils.ANSI_WHITE + "Vehicle Identification: " + split[1] + TextUtils.ANSI_RESET);
                print(TextUtils.ANSI_RED + LINE + TextUtils.ANSI_RESET);
                print(TextUtils.ANSI_WHITE + "Date of the Trip: " + split[0] + TextUtils.ANSI_RESET);
                print(TextUtils.ANSI_RED + LINE + TextUtils.ANSI_RESET);
                print(TextUtils.ANSI_WHITE + "Arrival: " + split[2] + TextUtils.ANSI_RESET);
                print(TextUtils.ANSI_RED + LINE + TextUtils.ANSI_RESET);
                print(TextUtils.ANSI_WHITE + "ContainerID: " + split[3] + TextUtils.ANSI_RESET + TextUtils.ANSI_WHITE + BAR + "Container ISO_CODE: " + split[4] + TextUtils.ANSI_RESET);
                print(TextUtils.ANSI_RED + LINE + TextUtils.ANSI_RESET);
                print(TextUtils.ANSI_WHITE + "PosX: " + split[5] + BAR + TextUtils.ANSI_RESET + TextUtils.ANSI_WHITE + "PosY: " + split[6] + BAR + TextUtils.ANSI_RESET + TextUtils.ANSI_WHITE + "PosZ: " + split[7] + TextUtils.ANSI_RESET);
                print("\n");
            }

        } catch (Exception e) {
            print(e.getMessage());
        }
    }
}
