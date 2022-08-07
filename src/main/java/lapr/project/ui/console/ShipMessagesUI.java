package lapr.project.ui.console;

import lapr.project.controller.PositionController;
import lapr.project.ui.console.console_utils.TextUtils;
import lapr.project.ui.console.console_utils.Utils;

import java.util.List;

import static lapr.project.ui.console.console_utils.Utils.print;

public class ShipMessagesUI implements Runnable {
    private final PositionController controller = new PositionController();
    static final String BLOCK = "----------------------------------------------------------------------------------------------";

    public void run() {
        try {
            printInfo();
            String date1 = askDates("start");
            String hour1 = askHour("start");
            String date2 = askDates("end");
            String hour2 = askHour("end");
            String ship = askShipCode();
            if((date2.isEmpty() && hour2.isEmpty())){
                List<String> messagesList = controller.DatePosition(date1, hour1, ship);
                print(BLOCK + TextUtils.ANSI_PURPLE);
                print(String.format("%-20s %-20s %-20s %-20s %-20s", "SHIP NAME", "DATE", "HOUR", "LATITUDE", "LONGITUDE"));
                print();
                print(TextUtils.ANSI_BLUE + BLOCK);
                for (String message : messagesList){
                    print(message);
                    print();
                }
            }
            else {
                List<String> messagesList1 = controller.PeriodPosition(date1, hour1, date2, hour2, ship);
                print(BLOCK + TextUtils.ANSI_PURPLE);
                print(String.format("%-20s %-20s %-20s %-20s %-20s", "SHIP NAME", "DATE", "HOUR", "LATITUDE", "LONGITUDE"));
                print();
                print(TextUtils.ANSI_BLUE + BLOCK);
                for (String message : messagesList1){
                    print(message);
                    print();
                }
            }
            print("\n******************************\nSuccess!");
        } catch (Exception e) {
            print(e.getMessage());
        }
    }

    public String askDates(String type){
        try {
            return Utils.readLineFromConsole(ShipDetailsUI.PLEASE_ENTER + type + " date in the following format: dd/MM/YYYY");
        }catch (Exception e){
            return Utils.readLineFromConsole(ShipDetailsUI.PLEASE_ENTER + type + " date in the following format: dd/MM/YYYY");
        }
    }

    public String askHour(String type){
        try {
            return Utils.readLineFromConsole(ShipDetailsUI.PLEASE_ENTER + type + " hour in the following format: hh:MM");
        }catch (Exception e){
            return Utils.readLineFromConsole(ShipDetailsUI.PLEASE_ENTER + type + " hour in the following format: hh:MM");
        }
    }

    public String askShipCode(){
        try {
            return Utils.readLineFromConsole("Now type the MMSI/IMO/CallSign of the ship you want:");
        }catch (Exception e){
            return Utils.readLineFromConsole("Now type the MMSI/IMO/CallSign of the ship you want:");
        }

    }

    public void printInfo(){
        print("┌─────────────────────────┐\n" +
                "│       "+ TextUtils.ANSI_YELLOW + "  [Info]" + TextUtils.ANSI_BLUE + "          │\n" +
                "├─────────────────────────┤\n" +
                ShipDetailsUI.SPACE +
                "│ You will be asked for   │\n" +
                "│ an interval of dates    │\n" +
                "│ and hours.              │\n" +
                ShipDetailsUI.SPACE +
                "│ If you want information │\n" +
                "│ about a specific date   │\n" +
                "│ and hour, just leave    │\n" +
                "│ the second ones empty!  │\n" +
                ShipDetailsUI.SPACE +
                "└─────────────────────────┘");
    }
}