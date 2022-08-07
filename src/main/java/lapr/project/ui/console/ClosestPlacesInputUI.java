package lapr.project.ui.console;

import lapr.project.controller.ClosestPlacesInputController;
import lapr.project.controller.FreightNetworkController;
import lapr.project.store.FreightNetworkStore;
import lapr.project.structures.graph.matrix.MatrixGraph;
import lapr.project.ui.console.console_utils.TextUtils;
import lapr.project.ui.console.console_utils.Utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static lapr.project.ui.console.console_utils.Utils.print;

public class ClosestPlacesInputUI implements Runnable {

    private final ClosestPlacesInputController controller = new ClosestPlacesInputController();
    private final FreightNetworkController c = new FreightNetworkController();
    private static MatrixGraph<Object, Double> mg;
    private static final LinkedList<Object> objects = new LinkedList<>();
    private static List<Object> verticesList = new ArrayList<>();
    private static boolean land;
    private static boolean sea;
    private static int start;
    private static int end;

    @Override
    public void run() {
        build();
    }

    public void build() {

        if(FreightNetworkStore.getAb() == null){
            c.build(1000);
        }
        mg = FreightNetworkStore.getAb();
        verticesList = mg.vertices();

        for (int n = 0; n < verticesList.size(); n++) {
            print((n + 1) + " - " + verticesList.get(n));
        }
        getDeparture();
        getArrival();
        Places();
        Path();
        getPlaces();
    }

    public void getDeparture() {

        try {
            start = Utils.readIntegerFromConsole("Please insert the number of the start vertex: ");
        } catch (Exception e) {
            start = Utils.readIntegerFromConsole("Please insert the number of the start vertex: ");
        }
    }

    public void getArrival() {

        try {
            end = Utils.readIntegerFromConsole("Please insert the number of the end vertex: ");
        } catch (Exception e) {
            end = Utils.readIntegerFromConsole("Please insert the number of the end vertex: ");
        }
    }

    public void Places() {

        int numberPlaces;

        try {
            numberPlaces = Utils.readIntegerFromConsole("How many places do you want to pass throw: ");
        } catch (Exception e) {
            numberPlaces = Utils.readIntegerFromConsole("How many places do you want to pass throw: ");
        }

        for (int i = 0; i < numberPlaces; i++) {

            int input;

            try {
                input = Utils.readIntegerFromConsole("Please insert the number of the place you want to pass by:");
            } catch (Exception e) {
                input = Utils.readIntegerFromConsole("Please insert the number of the place you want to pass by:");
            }

            if (input > 0) {
                objects.add(verticesList.get(input - 1));
            }
        }
    }

    public void Path() {

        int opt;

        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Land Path", 1));
        options.add(new MenuItem("Maritime Path", 2));
        options.add(new MenuItem("Land or Sea Path", 3));

        int option = 0;

        while (option != -1) {
            option = Utils.showAndSelectIndex(options, "\n\nPlease, enter the option:");

            if ((option >= 0) && (option < options.size())) {
                opt = options.get(option).getMonth();

                if (opt == 1) {
                    land = true;
                } else if (opt == 2) {
                    sea = true;
                } else {
                    sea = true;
                    land = true;
                }
                break;
            }
        }
    }

    public void getPlaces() {

        print(TextUtils.ANSI_RED + "\nCalculating the shortest path... " + TextUtils.ANSI_RESET);

        Object startVertex = verticesList.get(start - 1);
        Object endVertex = verticesList.get(end - 1);

        LinkedList<Object> path;

        try {
            path = controller.shortestPathUsingNplaces(mg, startVertex, endVertex, objects, sea, land);

            if (path.isEmpty()) {
                throw new IllegalArgumentException("[!] There is no connection between departure and arrival :(");
            } else {
                for (Object a : path) {
                    print(TextUtils.ANSI_WHITE + a + TextUtils.ANSI_RESET);
                }
            }

        } catch (Exception e) {
            print(e.getMessage());
        }
    }
}