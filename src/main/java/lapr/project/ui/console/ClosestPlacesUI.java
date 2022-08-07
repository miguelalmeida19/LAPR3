package lapr.project.ui.console;

import lapr.project.controller.ClosestPlacesController;
import lapr.project.structures.graph.Edge;
import lapr.project.ui.console.console_utils.TextUtils;
import lapr.project.ui.console.console_utils.Utils;
import lapr.project.utils.SortDistances;

import java.util.ArrayList;
import java.util.List;

import static lapr.project.ui.console.console_utils.Utils.print;

public class ClosestPlacesUI implements Runnable {
    static final String LINE = "--------------------------------------------";

    private final ClosestPlacesController controller = new ClosestPlacesController();
    private List<Edge<Object, Double>> list = new ArrayList<>();

    @Override
    public void run() {

        String continent = Utils.readLineFromConsole("Please, enter the Continent: ");
        int n = Utils.readIntegerFromConsole("Please enter how many close locations you want: ");

        try {

            controller.build();
            list = controller.getPlaces(continent);

            list.sort(new SortDistances());

        } catch (Exception e) {
            Utils.print(e.getMessage());
        }

        int cont = 0;

        if (!list.isEmpty()) {

            for (Edge<Object, Double> s : list) {

                if(n % 2 == 0){
                    if (cont < 2*n && cont % 2 == 0) {
                        print(TextUtils.ANSI_RED + LINE + TextUtils.ANSI_RESET);
                        print(TextUtils.ANSI_WHITE + s + TextUtils.ANSI_RESET);
                        print(TextUtils.ANSI_RED + LINE + TextUtils.ANSI_RESET);
                    }
                } else {
                    if (cont < 2*n && cont % 2 == 1) {
                        print(TextUtils.ANSI_RED + LINE + TextUtils.ANSI_RESET);
                        print(TextUtils.ANSI_WHITE + s + TextUtils.ANSI_RESET);
                        print(TextUtils.ANSI_RED + LINE + TextUtils.ANSI_RESET);
                    }
                }
                cont++;
            }
        } else {
            print(TextUtils.ANSI_RED + "There is no port associated with this continent :(" + TextUtils.ANSI_RESET);
        }

        new TrafficManagerUI().run();
    }
}
