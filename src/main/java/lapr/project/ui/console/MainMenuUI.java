package lapr.project.ui.console;

import lapr.project.ui.console.console_utils.AuthUI;
import lapr.project.ui.console.console_utils.Utils;

import java.util.ArrayList;
import java.util.List;


public class MainMenuUI {
    private int option = 0;

    public MainMenuUI() {
        // Explain why this is empty
    }

    public void run() {

        do {
            showMenu();
        }
        while (option != -1);
    }

    void showMenu() {
        try {
            List<lapr.project.ui.console.MenuItem> options = new ArrayList<>();
            options.add(new MenuItem("Do Login", new AuthUI()));
            options.add(new MenuItem("Know the Development Team", new DevTeamUI()));
            option = Utils.showAndSelectIndex(options, "\n\nMain Menu");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        }catch (Exception e){
            showMenu();
        }

    }
}
