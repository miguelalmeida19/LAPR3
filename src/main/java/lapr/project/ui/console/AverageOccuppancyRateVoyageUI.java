package lapr.project.ui.console;

import lapr.project.data.ImportFromDataBase;
import lapr.project.ui.console.console_utils.TextUtils;

import static lapr.project.ui.console.console_utils.Utils.*;

public class AverageOccuppancyRateVoyageUI implements Runnable {
    @Override
    public void run() {
        print(TextUtils.ANSI_CYAN + "\nYou are about to see the average occupancy rate per voyage" + TextUtils.ANSI_BLUE);
        String threshold= readLineFromConsole(TextUtils.ANSI_CYAN + "\nType the threshold (if you want default value, press Enter): " + TextUtils.ANSI_BLUE);
        if (!threshold.equals("")){
            print(TextUtils.ANSI_PURPLE + "\nVoyages: \n" + TextUtils.ANSI_CYAN + ImportFromDataBase.getAverageOccupancyRateOfVoyage(Integer.parseInt(threshold)));
        }
        else{
            print(TextUtils.ANSI_PURPLE + "\nVoyages: \n" + TextUtils.ANSI_CYAN + ImportFromDataBase.getAverageOccupancyRateOfVoyage(66));
        }
    }
}