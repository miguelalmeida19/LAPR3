package lapr.project.ui.console;

import lapr.project.ui.console.console_utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ClientUI implements Runnable {



    public void run() {


        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Get the situation of a container.", new ContainerStatusUI()));
        options.add(new MenuItem("Route of container.", new RouteOfContainerUI()));

        int option;
        do
        {
            option = Utils.showAndSelectIndex(options, "\n\nClient Menu:");

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }
        }
        while (option != -1 );
    }
}
