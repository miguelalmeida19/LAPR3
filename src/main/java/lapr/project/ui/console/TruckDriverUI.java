package lapr.project.ui.console;

import lapr.project.ui.console.console_utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class TruckDriverUI implements Runnable {

    public TruckDriverUI(){
        // Explain why this is empty
    }

    public void run() {


        List<MenuItem> options = new ArrayList<>();

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
