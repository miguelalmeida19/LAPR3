package lapr.project.ui.console;

import lapr.project.controller.PositionController;
import lapr.project.controller.ShipController;
import lapr.project.model.Message;
import lapr.project.ui.console.console_utils.TextUtils;
import lapr.project.ui.console.console_utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import static lapr.project.ui.console.console_utils.Utils.print;

public class ShipDetailsUI implements Runnable {
    private final ShipController controller = new ShipController();
    private final PositionController positionController = new PositionController();
    private String code = null;
    static final String SPACE = "│                         │\n";
    static final String PLEASE_ENTER = "Please enter the ";

    public void run() {
        try {
            do {
                selectOption();
            }while (controller.getDetails(code)==null);
            String details;
            details = controller.getDetails(code);
            print("\n" + details);
            print("All messages from ship " + TextUtils.ANSI_YELLOW + code + TextUtils.ANSI_BLUE);
            showMore();
            print("\n******************************\nSuccess!");
        } catch (Exception e) {
            print(e.getMessage());
        }
    }

    public void selectOption() {
        code = Utils.readLineFromConsole("Enter the code of the Ship you are looking for: ");
    }

    private void printInfo() {
        print("┌─────────────────────────┐\n" +
                "│       " + TextUtils.ANSI_YELLOW + "  [Info]" + TextUtils.ANSI_BLUE + "          │\n" +
                "├─────────────────────────┤\n" +
                SPACE +
                "│ You will be asked for   │\n" +
                "│ an interval of dates    │\n" +
                "│ and hours.              │\n" +
                SPACE +
                "│ If you want information │\n" +
                "│ about a specific date   │\n" +
                "│ and hour, just leave    │\n" +
                "│ the second ones empty!  │\n" +
                SPACE +
                "└─────────────────────────┘");
    }

    private String askDates(String type) {
        try {
            return Utils.readLineFromConsole(PLEASE_ENTER + type + " date in the following format: dd/MM/YYYY");
        } catch (Exception e) {
            return Utils.readLineFromConsole(PLEASE_ENTER + type + " date in the following format: dd/MM/YYYY");
        }
    }

    private String askHour(String type) {
        try {
            return Utils.readLineFromConsole(PLEASE_ENTER + type + " hour in the following format: hh:MM");
        } catch (Exception e) {
            return Utils.readLineFromConsole(PLEASE_ENTER + type + " hour in the following format: hh:MM");
        }
    }

    private void showMore() {
        List<String> options = new ArrayList<>();
        options.add("All dates and all positions.");
        options.add("The position for a specific date or interval of dates.");

        int index = Utils.showAndSelectIndex(options, "Select what you want to see:");
        if (index == 0) {
            print(TextUtils.ANSI_BLUE + "\n");
            print("-----------------------------------------------------------------------------------------------------------------------------------------------" + TextUtils.ANSI_PURPLE);
            print(String.format("%-20s %-20s %-20s %-20s %-20s %-20s %-20s", "LATITUDE", "LONGITUDE", "SOG", "COG", "HEADING", "TRANSCEIVER", "LOCAL DATE TIME"));
            print(TextUtils.ANSI_BLUE + "-----------------------------------------------------------------------------------------------------------------------------------------------");
            TreeSet<Message> treeSet = controller.getAllMessages(code);
            for (Message m : treeSet) {
                print(m.toString());
            }
        } else if (index == 1) {
            printInfo();
            String date1 = askDates("start");
            String hour1 = askHour("start");
            String date2 = askDates("end");
            String hour2 = askHour("end");
            print("----------------------------------------------------------------------------------------------" + TextUtils.ANSI_PURPLE);
            print(String.format("%-20s %-20s %-20s %-20s %-20s", "SHIP NAME", "DATE", "HOUR", "LATITUDE", "LONGITUDE"));
            print();
            print(TextUtils.ANSI_BLUE + "----------------------------------------------------------------------------------------------");
            List<String> list;
            if ((date2.isEmpty() && hour2.isEmpty())) {
                list = positionController.DatePosition(date1, hour1, code);
            } else {
                list = positionController.PeriodPosition(date1, hour1, date2, hour2, code);
            }
            for (String s: list){
                print(s);
            }
        } else if (index == -1) {
            new TrafficManagerUI().run();
        } else {
            showMore();
        }
    }
}