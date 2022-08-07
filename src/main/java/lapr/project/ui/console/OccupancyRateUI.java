package lapr.project.ui.console;

import lapr.project.controller.ContainersController;
import lapr.project.controller.OccupancyRateController;
import lapr.project.ui.console.console_utils.TextUtils;
import lapr.project.ui.console.console_utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static lapr.project.ui.console.console_utils.Utils.print;

public class OccupancyRateUI implements Runnable{
    private final OccupancyRateController controller = new OccupancyRateController();

    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(System.in);
            String code = ContainersController.getShipForCaptain();
            print("\n" + TextUtils.ANSI_PURPLE + "You are in the Ship: " +TextUtils.ANSI_BLUE + code);
            List<String> list = new ArrayList<>();
            list.add("Give a cargo manifest");
            list.add("Give a specific moment");
            print("\n");
            int option = Utils.showAndSelectIndex(list, "How do you want to get the occupancy rate?");
            double ocupancyRate = 0;
            if (option==0){
                print("\n" + TextUtils.ANSI_PURPLE + "What is the cargo manifest id?" +TextUtils.ANSI_BLUE);
                String cargoID = scanner.nextLine();
                ocupancyRate = controller.getOccupancyRateByCargoManifest(code, cargoID);
            }
            if(option==1){
                print("\n" + TextUtils.ANSI_PURPLE + "What is the date and hour? [FORMAT: dd/MM/yyyy HH:mm]" +TextUtils.ANSI_BLUE);
                String date = scanner.nextLine();
                ocupancyRate = controller.getOccupancyRateByDate(code, date);
            }
            print(TextUtils.ANSI_GREEN +"\n" + "▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
            print(TextUtils.ANSI_GREEN + "█ OCCUPANCY RATE: " + TextUtils.ANSI_CYAN + String.format("%.2f", ocupancyRate) + "%");
        } catch (Exception e) {
            print("\n" + TextUtils.ANSI_RED + e.getMessage() + TextUtils.ANSI_BLUE);
        }
    }
}
