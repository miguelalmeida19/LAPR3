package lapr.project.ui.console;

import lapr.project.controller.ContainersController;
import lapr.project.data.ImportFromDataBase;
import lapr.project.model.Container;
import lapr.project.ui.console.console_utils.TextUtils;
import lapr.project.ui.console.console_utils.Utils;

import java.time.LocalDateTime;
import java.util.*;

import static lapr.project.ui.console.console_utils.Utils.print;

enum Status {
    TO_BE_LOADED("TO BE LOADED"), TO_BE_OFFLOADED("TO BE OFFLOADED");

    Status(String label) {
        this.label = label;
    }

    private final String label;

    public String getLabel() {
        return label;
    }
}

public class LoadedOffloadedContainersUI implements Runnable {

    private final ContainersController controller = new ContainersController();

    @Override
    public void run() {
        try {
            String code = ContainersController.getShipForCaptain();
            print("\n" + TextUtils.ANSI_PURPLE + "You are in the Ship: " +TextUtils.ANSI_BLUE + code);

            List<String> list = new ArrayList<>();
            list.add(Status.TO_BE_LOADED.getLabel());
            list.add(Status.TO_BE_OFFLOADED.getLabel());
            print("\n");
            int option = Utils.showAndSelectIndex(list, "What is the container state you are looking for?");
            HashMap<String, List<Container>> containers = new LinkedHashMap<>();
            LocalDateTime lt = LocalDateTime.now();
            if (option==0){
                containers = controller.getContainersFromStateAndShip(code, Status.TO_BE_LOADED.getLabel(), lt);
            }
            if(option==1){
                containers = controller.getContainersFromStateAndShip(code, Status.TO_BE_OFFLOADED.getLabel(), lt);
            }
            if (containers.isEmpty()){
                throw new IllegalArgumentException("There are not containers for your filters.");
            }
            String nextPort = (String) containers.keySet().toArray()[0];
            List<Container> containerList = containers.get(nextPort);
            print(TextUtils.ANSI_CYAN + "⎽⎼⎻⎺⎺⎻⎼⎽⎽⎼⎻⎺⎺⎻⎼⎽⎽⎼⎻⎺⎺⎻⎼⎽⎽⎼⎻⎺⎺⎻⎼⎽⎽⎼⎻⎺⎺⎻⎼⎽⎽⎼⎻⎺⎺⎻⎼⎽⎽⎼⎻⎺⎺⎻⎼⎽⎽⎼⎻⎺⎺⎻⎼⎽⎽⎼⎻⎺⎺⎻⎼⎽⎽⎼⎻⎺⎺⎻⎼⎽⎽⎼⎻⎺⎺⎻⎼⎽⎽⎼⎻⎺⎺⎻⎼⎽" + TextUtils.ANSI_BLUE);
            print(TextUtils.ANSI_BLUE + "Next Port [Based on the Actual Date]: " + TextUtils.ANSI_CYAN + nextPort);
            print(TextUtils.ANSI_CYAN + "⎽⎼⎻⎺⎺⎻⎼⎽⎽⎼⎻⎺⎺⎻⎼⎽⎽⎼⎻⎺⎺⎻⎼⎽⎽⎼⎻⎺⎺⎻⎼⎽⎽⎼⎻⎺⎺⎻⎼⎽⎽⎼⎻⎺⎺⎻⎼⎽⎽⎼⎻⎺⎺⎻⎼⎽⎽⎼⎻⎺⎺⎻⎼⎽⎽⎼⎻⎺⎺⎻⎼⎽⎽⎼⎻⎺⎺⎻⎼⎽⎽⎼⎻⎺⎺⎻⎼⎽⎽⎼⎻⎺⎺⎻⎼⎽" + TextUtils.ANSI_BLUE);
            for (Container c: containerList){
                print(TextUtils.ANSI_GREEN + "\n■━■━■━■━■━■━■━■━■━■━■━■━■━■━■━■━■━■" + TextUtils.ANSI_BLUE);
                print(TextUtils.ANSI_BLUE + "CONTAINER ID:" + TextUtils.ANSI_CYAN + c.getID());
                print(TextUtils.ANSI_BLUE + "PAYLOAD:" + TextUtils.ANSI_CYAN + c.getPayload());
                print(TextUtils.ANSI_BLUE + "TARE:" + TextUtils.ANSI_CYAN + c.getTare());
                print(TextUtils.ANSI_BLUE + "GROSS:" + TextUtils.ANSI_CYAN + c.getGross());
                print(TextUtils.ANSI_BLUE + "ISO CODE:" + TextUtils.ANSI_CYAN + c.getIsoCode());
                int[] position = ImportFromDataBase.getPositionOfContainer(String.valueOf(c.getID()));
                print(TextUtils.ANSI_BLUE + "CONTAINER POSITION:" + TextUtils.ANSI_CYAN + "[X=" + position[0] + ",Y=" + position[1] + ",Z=" + position[2] + "]");
                print(TextUtils.ANSI_GREEN + "■━■━■━■━■━■━■━■━■━■━■━■━■━■━■━■━■━■" + TextUtils.ANSI_BLUE);
            }
        } catch (Exception e) {
            print("\n" + TextUtils.ANSI_RED + e.getMessage() + TextUtils.ANSI_BLUE);
        }
    }
}