package lapr.project.ui.console;

import lapr.project.controller.ShipSummaryController;
import lapr.project.model.ShipSummary;
import lapr.project.ui.console.console_utils.TextUtils;
import lapr.project.ui.console.console_utils.Utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

import static lapr.project.ui.console.console_utils.Utils.print;

public class ShipSummaryUI implements Runnable {
    private final ShipSummaryController shipController = new ShipSummaryController();

    public void run() {
        try {
            String code;
            code = Utils.readLineFromConsole("Enter the code of the Ship you are looking for: ");
            Map<ShipSummary, Object> map = shipController.shipSummary(code);
            print("----------------------------------------" + TextUtils.ANSI_PURPLE);
            print(map.get(ShipSummary.CODE).toString());
            print(TextUtils.ANSI_BLUE + "----------------------------------------\n");
            print();
            long s =((Duration) map.get(ShipSummary.TOTAL_MOVEMENT_TIME)).getSeconds();
            int hours =(int) s / 3600;
            int minutes = (int)(s % 3600) / 60;
            int seconds =(int) s % 60;
            print("Summary of the ship: \n");
            print(
                    "VESSEL_NAME:             " + map.get(ShipSummary.VESSEL_NAME) + "\n" +
                    "CODE_INTRODUCED:         " + map.get(ShipSummary.CODE) + "\n" +
                    "START_BASE_DATE_TIME:    " + ((LocalDateTime) map.get(ShipSummary.START_BASE_DATE_TIME)).getDayOfMonth()+"/"+((LocalDateTime) map.get(ShipSummary.START_BASE_DATE_TIME)).getMonth() +"/"+
                            ((LocalDateTime) map.get(ShipSummary.START_BASE_DATE_TIME)).getYear() + "  "+((LocalDateTime) map.get(ShipSummary.START_BASE_DATE_TIME)).getHour()+":"+((LocalDateTime) map.get(ShipSummary.START_BASE_DATE_TIME)).getMinute()+":"
                                                +((LocalDateTime) map.get(ShipSummary.START_BASE_DATE_TIME)).getSecond()+"\n" +

                    "END_BASE_DATE_TIME:      " + ((LocalDateTime) map.get(ShipSummary.END_BASE_DATE_TIME)).getDayOfMonth()+"/"+((LocalDateTime) map.get(ShipSummary.END_BASE_DATE_TIME)).getMonth() +"/"+
                            ((LocalDateTime) map.get(ShipSummary.END_BASE_DATE_TIME)).getYear() + "  "+((LocalDateTime) map.get(ShipSummary.END_BASE_DATE_TIME)).getHour()+":"+((LocalDateTime) map.get(ShipSummary.END_BASE_DATE_TIME)).getMinute()+":"
                    +((LocalDateTime) map.get(ShipSummary.END_BASE_DATE_TIME)).getSecond()+"\n" +

                    "TOTAL_MOVEMENT_TIME:     " + hours+"h:"+minutes+"m:"+seconds+"s"+ "\n" +
                    "TOTAL_NUMBER_MOVEMENTS:  " + map.get(ShipSummary.TOTAL_NUMBER_MOVEMENTS) +" Movements." + "\n" +
                    "MAX_SOG:                 " + map.get(ShipSummary.MAX_SOG) + "\n" +
                    "MAX_COG:                 " + map.get(ShipSummary.MAX_COG) + "\n" +
                    "MEAN_COG:                " + map.get(ShipSummary.MEAN_COG) + "\n" +
                    "MEAN_SOG:                " + map.get(ShipSummary.MEAN_SOG) + "\n" +
                    "DEPARTURE_LATITUDE:      " + map.get(ShipSummary.DEPARTURE_LATITUDE) + "\n" +
                    "DEPARTURE_LONGITUDE:     " + map.get(ShipSummary.DEPARTURE_LONGITUDE) + "\n" +
                    "ARRIVAL_LATITUDE:        " + map.get(ShipSummary.ARRIVAL_LATITUDE) + "\n" +
                    "ARRIVAL_LONGITUDE:       " + map.get(ShipSummary.ARRIVAL_LONGITUDE) + "\n" +
                    "TRAVELED_DISTANCE:       " + map.get(ShipSummary.TRAVELED_DISTANCE) + "\n" +
                    "DELTA_DISTANCE:          " + map.get(ShipSummary.DELTA_DISTANCE));
            print();
            print("\n******************************\nSuccess!");
        } catch (Exception e) {
            print(e.getMessage());
        }
    }
}
