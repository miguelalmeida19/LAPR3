package lapr.project.ui.console;

import lapr.project.ui.console.console_utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ChiefElectricalEnginnerUI implements Runnable {

    public ChiefElectricalEnginnerUI(){
        // Explain why this is empty
    }

    public void run() {


        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("US412", new US412UI()));
        options.add(new MenuItem("US414", new US414UI()));
        options.add(new MenuItem("Compiled functions", new Sprint4CompiledFunction()));

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
