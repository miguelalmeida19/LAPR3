package lapr.project.controller;

import lapr.project.data.ExportToDatabase;
import lapr.project.model.Company;
import lapr.project.model.Port;
import lapr.project.store.PortStore;
import lapr.project.ui.console.console_utils.TextUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static lapr.project.ui.console.console_utils.Utils.print;


public class PortController {
    private final PortStore portStore;
    private final List<Port> portList;

    public PortController() {
        Company company = App.getInstance().getCompany();
        portStore = company.getPortStore();
        portList = new ArrayList<>();
    }

    public void importPorts(String file) {
        print(TextUtils.ANSI_GREEN + "\nImporting Ports . . ." + TextUtils.ANSI_BLUE + "\n");
        File file1 = new File(file);
        int count = 0;
        try {
            portList.clear();
            Scanner scanner = new Scanner(file1);
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lineArray = line.split(",");

                String continent = lineArray[0];
                String country = lineArray[1];
                String code = lineArray[2];
                String portName = lineArray[3];
                double lat = Double.parseDouble(lineArray[4]);
                double log = Double.parseDouble(lineArray[5]);

                Port port = new Port(continent, country, code, portName, lat, log);
                portStore.addPort(port);
                portList.add(port);
                count++;
                print("\r" + count + " successfully imported Ports");
            }
            portStore.getKdt().insertBalanced(portStore.getPortHashMap());
            ExportToDatabase exportToDatabase = new ExportToDatabase();

            exportToDatabase.exportPortsToDatabase(portList);

        } catch (Exception e) {
            print(e.getMessage());
        }
    }
}