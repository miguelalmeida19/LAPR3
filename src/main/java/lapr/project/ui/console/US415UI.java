package lapr.project.ui.console;

import lapr.project.controller.US412Controller;
import lapr.project.ui.console.console_utils.TextUtils;
import lapr.project.ui.console.console_utils.Utils;


import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import static lapr.project.ui.console.console_utils.Utils.*;


public class US415UI implements Runnable {
    private static final int POWER_PER_SUPPLY = 75000; //in W

    @Override
    public void run() {
        try {

            double externalTemperature = inputDoubleWithDefault(TextUtils.ANSI_CYAN + "[*] " + TextUtils.ANSI_RESET + "Please insert the external temperature ", 20);

            print(TextUtils.ANSI_CYAN + "[*]" + TextUtils.ANSI_RESET + " Loading the ports from the database...");

            ResultSet s = executeQeryDatabase("SELECT s.from_port FROM SEADISTS S GROUP BY S.FROM_PORT ");
            int n = 0;
            List<String> listPorts = new ArrayList<>();
            while (s.next()) {
                String sl = s.getString(1);
                print(TextUtils.ANSI_YELLOW + (n + 1) + "-  " + sl);
                listPorts.add(sl);
                n++;
            }
            int initialPortIndex = Utils.readIntegerFromConsole(TextUtils.ANSI_CYAN + "[>]" + TextUtils.ANSI_RESET + " Please select the initial port");
            String initialPort = listPorts.get(initialPortIndex - 1);

            s = executeQeryDatabase("SELECT S.TO_PORT FROM SEADISTS S where s.from_port = '" + initialPort + "' GROUP BY S.TO_PORT");
            print(TextUtils.ANSI_CYAN + "[*] " + TextUtils.ANSI_RESET + "Please select the final port:");
            n = 0;
            listPorts.clear();
            while (s.next()) {
                String sl = s.getString(1);
                print(TextUtils.ANSI_YELLOW + (n + 1) + "-  " + sl);
                listPorts.add(sl);
                n++;
            }
            int finalPortIndex = Utils.readIntegerFromConsole(TextUtils.ANSI_CYAN + "[>]" + TextUtils.ANSI_RESET + " Please select the final port");
            String finalPort = listPorts.get(finalPortIndex - 1);
            s = executeQeryDatabase("SELECT S.SEA_DISTANCE FROM SEADISTS S where s.from_port = '" + initialPort + "' AND s.to_port = '" + finalPort + "'");
            s.next();
            int distanceInKm = s.getInt(1);

            print(TextUtils.ANSI_CYAN + "[*]" + TextUtils.ANSI_RESET + " Distance between the selected port: " + distanceInKm + " Km");

            double velocity = inputDoubleWithDefault(TextUtils.ANSI_CYAN + "[>]" + TextUtils.ANSI_RESET + "Please insert the medium velocity of the ship in Km/h ", 90);
            double travelTime = Math.ceil((distanceInKm / velocity) * 3600);

            US412Controller c = new US412Controller();
            int numberContainersSevenDegrees = inputWithDefault(TextUtils.ANSI_CYAN + "[*] " + TextUtils.ANSI_RESET + "Please insert the number of containers" +
                    " at 7ºC ", 25);
            int numberContainersMinusFiveDegrees = inputWithDefault(TextUtils.ANSI_CYAN + "[*] " + TextUtils.ANSI_RESET + "Please insert the number of containers" +
                    " at -5ºC ", 15);

            double e = c.energiaTotalFornecerParametros(externalTemperature, travelTime);
            e = e * numberContainersSevenDegrees;
            double e2 = c.energiaTotalFornecerRefrigeradoParametros(externalTemperature, travelTime);
            e2 = e2 * numberContainersMinusFiveDegrees;
            print(TextUtils.ANSI_CYAN + "[*] " + TextUtils.ANSI_RESET + "Energy needed to keep the temperature on the containers at 7 degrees: " + e + " J");
            print(TextUtils.ANSI_CYAN + "[*] " + TextUtils.ANSI_RESET + "Energy needed to keep the temperature on the containers at -5 degrees: " + e2 + " J");
            print(TextUtils.ANSI_CYAN + "[*] " + TextUtils.ANSI_RESET + "Total energy needed: " + (e2 + e) + " J");
            print(TextUtils.ANSI_CYAN + "[*] " + TextUtils.ANSI_RESET + "Number of generators needed: " + (int) Math.ceil((e2 + e) / (POWER_PER_SUPPLY * travelTime)));

        } catch (Exception e) {
            print(TextUtils.ANSI_RED + "[!]" + TextUtils.ANSI_RESET + " An error occurred: " + e.getMessage());
        }
    }


}
