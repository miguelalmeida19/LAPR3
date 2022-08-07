package lapr.project.ui.console;
import lapr.project.controller.US412Controller;
import lapr.project.ui.console.console_utils.TextUtils;
import lapr.project.ui.console.console_utils.Utils;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static lapr.project.ui.console.console_utils.Utils.*;

public class US413UI implements Runnable {

    private static final US412Controller controller = new US412Controller();

    @Override
    public void run() {
        try {

            print(TextUtils.ANSI_CYAN + "[*]" + TextUtils.ANSI_RESET + " Loading the ports...");

            ResultSet s = executeQeryDatabase("SELECT s.from_port FROM SEADISTS S GROUP BY S.FROM_PORT");
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
            double velocity = inputDoubleWithDefault(TextUtils.ANSI_CYAN+"[>]"+TextUtils.ANSI_RESET+"Please insert the medium velocity of the ship in Km/h ",90);
            int tempoViagem = (int)Math.ceil((distanceInKm / velocity) * 3600);

            print(TextUtils.ANSI_CYAN + "[*]" + TextUtils.ANSI_RESET + " Distance between the selected port: " + distanceInKm + " Km");
            int numsections = Utils.readIntegerFromConsole(TextUtils.ANSI_CYAN + "[>]" + TextUtils.ANSI_RESET + " Please insert the number of sections of the trip: ");

            double sumNormal = 0;
            double sumRefrigerado = 0;

            for (int i = 0; i < numsections; i++) {
                double temp = Double.parseDouble(Utils.readLineFromConsole(TextUtils.ANSI_CYAN + "[>] " + TextUtils.ANSI_RESET + " Please insert the temperature of the section: "));
                sumNormal += controller.energiaTotalFornecerParametros(temp, (float) tempoViagem / numsections);
                sumRefrigerado += controller.energiaTotalFornecerRefrigeradoParametros(temp, (float) tempoViagem / numsections);

            }

            int numSete = inputWithDefault(TextUtils.ANSI_CYAN + "[*]" + TextUtils.ANSI_RESET + " Please insert the number of containers at 7º C",25);
            int numMenosCinco = inputWithDefault(TextUtils.ANSI_CYAN + "[*]" + TextUtils.ANSI_RESET + " Please insert the number of containers at -5º C",25);


            print(TextUtils.ANSI_CYAN+"[*]"+TextUtils.ANSI_RESET+" Total energy to be supplied, to one  container with an operating temperature of 7 °C");
            print(sumNormal + " J");
            print(TextUtils.ANSI_CYAN+"[*]"+TextUtils.ANSI_RESET+" Total energy to be supplied, to "+numSete+"containers with an operating temperature of 7 °C");
            print(numSete*sumNormal + " J");

            print("The total energy to be supplied, to one container with an operating temperature of - 5 °C");
            print(sumRefrigerado + " J");
            print(TextUtils.ANSI_CYAN+"[*]"+TextUtils.ANSI_RESET+" Total energy to be supplied, to "+numMenosCinco+" containers with an operating temperature of - 5 °C");
            print(numMenosCinco*sumRefrigerado + " J");

        }catch(Exception e){
            print(TextUtils.ANSI_RED+"[!]"+TextUtils.ANSI_RESET+" An error occurred: "+e.getMessage());
        }
        }
}
