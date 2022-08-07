package lapr.project.ui.console;

import lapr.project.controller.US412Controller;
import lapr.project.ui.console.console_utils.TextUtils;
import lapr.project.ui.console.console_utils.Utils;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static lapr.project.ui.console.console_utils.Utils.*;
import static lapr.project.ui.console.console_utils.Utils.executeQeryDatabase;

public class US414UI implements Runnable{

    private static final US412Controller controller = new US412Controller();

    @Override
    public void run() {
        try {
            print(TextUtils.ANSI_CYAN+"[*]"+TextUtils.ANSI_RESET+" Loading the ports...");


            ResultSet s = executeQeryDatabase("SELECT s.from_port FROM SEADISTS S GROUP BY S.FROM_PORT ");
            int n = 0;
            List<String> listPorts = new ArrayList<>();
            while(s.next()) {
                String sl = s.getString(1);
                print(TextUtils.ANSI_YELLOW + (n + 1) + "-  " +sl);
                listPorts.add(sl);
                n++;
            }
            int initialPortIndex = Utils.readIntegerFromConsole(TextUtils.ANSI_CYAN+"[>]"+TextUtils.ANSI_RESET+" Please select the initial port");
            String initialPort = listPorts.get(initialPortIndex-1);

            s = executeQeryDatabase("SELECT S.TO_PORT FROM SEADISTS S where s.from_port = '"+initialPort+"' GROUP BY S.TO_PORT");
            print(TextUtils.ANSI_CYAN+"[*] "+TextUtils.ANSI_RESET+"Please select the final port:");
            n = 0;
            listPorts.clear();
            while(s.next()) {
                String sl = s.getString(1);
                print(TextUtils.ANSI_YELLOW + (n + 1) + "-  " +sl);
                listPorts.add(sl);
                n++;
            }
            int finalPortIndex = Utils.readIntegerFromConsole(TextUtils.ANSI_CYAN+"[>]"+TextUtils.ANSI_RESET+" Please select the final port");
            String finalPort = listPorts.get(finalPortIndex-1);
            s = executeQeryDatabase("SELECT S.SEA_DISTANCE FROM SEADISTS S where s.from_port = '"+initialPort +"' AND s.to_port = '"+finalPort+"'");
            s.next();
            int distanceInKm = s.getInt(1);



            print(TextUtils.ANSI_CYAN+"\n[*]"+TextUtils.ANSI_RESET+" Distance between the selected port: "+distanceInKm+" Km\n");

            double velocity = inputDoubleWithDefault(TextUtils.ANSI_CYAN+"[>]"+TextUtils.ANSI_RESET+"Please insert the medium velocity of the ship in Km/h ",90);
            int travelTime = (int)Math.ceil((distanceInKm / velocity) * 3600);


            int numsections = Utils.readIntegerFromConsole(TextUtils.ANSI_CYAN+"[>]"+TextUtils.ANSI_RESET+" Please insert the number of sections of the trip: ");
            int numberOfContainers= Utils.readIntegerFromConsole(TextUtils.ANSI_CYAN+"[>]"+TextUtils.ANSI_RESET+" Please insert the number of containers: ");
            int containersExposed= Utils.readIntegerFromConsole(TextUtils.ANSI_CYAN+"[>]"+TextUtils.ANSI_RESET+" Please insert the number of containers on the top: ");

            int interiorTemp;
            int option = interiorTemperature();
            if (option==0){
                interiorTemp = -5;
            }else {
                interiorTemp = 7;
            }

            double segundos= (double) travelTime/numsections;
            double sumNormal = 0;

            for(int i = 0; i < numsections; i++) {
                double temp = Double.parseDouble(Utils.readLineFromConsole(TextUtils.ANSI_CYAN+ "[>] "+TextUtils.ANSI_RESET+" Please insert the temperature of the section: "));
                if (interiorTemp==7){
                    sumNormal += controller.energiaTotalComPotencia7(temp, segundos)*containersExposed;
                    sumNormal += controller.energiaTotalFornecerParametros(temp, segundos) * (numberOfContainers-containersExposed);
                }else {
                    sumNormal += controller.energiaTotalComPotenciaMenos5(temp, segundos)*containersExposed;
                    sumNormal += controller.energiaTotalFornecerRefrigeradoParametros(temp, segundos) * (numberOfContainers-containersExposed);
                }
            }

            print("\nA energia total a fornecer na trip é de " + String.format("%6.3e",sumNormal) + " J.");


        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int interiorTemperature(){
        List<String> options = new ArrayList<>();
        options.add("-5ºC");
        options.add("7ºC");

        return Utils.showAndSelectIndex(options, "\n\nSelect the interior temperature of the containers");
    }
}