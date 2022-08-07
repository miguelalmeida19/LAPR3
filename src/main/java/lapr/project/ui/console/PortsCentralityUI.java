package lapr.project.ui.console;

import lapr.project.structures.graph.BetweennessCentrality;
import lapr.project.ui.console.console_utils.TextUtils;

import java.util.LinkedHashMap;
import java.util.Map;

import static lapr.project.ui.console.console_utils.Utils.print;
import static lapr.project.ui.console.console_utils.Utils.readIntegerFromConsole;

public class PortsCentralityUI implements Runnable{

    @Override
    public void run() {

        print("\nPorts that have greater centrality in the freight network");

        try {
            int n = readIntegerFromConsole(TextUtils.ANSI_CYAN + "Type how many ports you want to see with greater centrality:");
            BetweennessCentrality betweennessCentrality = new BetweennessCentrality();
            LinkedHashMap<Object, Integer> map = betweennessCentrality.computeBetweennessCentrality(n);

            print(TextUtils.ANSI_PURPLE + "\n" + String.format("%-30s %-30s","Port", "Centrality"));
            for (Map.Entry<Object, Integer> o: map.entrySet()){
                print(TextUtils.ANSI_BLUE + String.format("%-30s %-30s",o.getKey(), o.getValue()));
            }
        }catch (Exception e){
            print(TextUtils.ANSI_RED + "\n[ERROR] The Freight Network has not been build yet.");
        }
    }
}
