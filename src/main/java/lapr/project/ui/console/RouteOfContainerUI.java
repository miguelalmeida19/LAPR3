package lapr.project.ui.console;

import lapr.project.data.ImportFromDataBase;
import lapr.project.ui.console.console_utils.TextUtils;

import static lapr.project.ui.console.console_utils.Utils.print;
import static lapr.project.ui.console.console_utils.Utils.readLineFromConsole;

public class RouteOfContainerUI implements Runnable{
    @Override
    public void run() {
        String reg = readLineFromConsole(TextUtils.ANSI_CYAN + "What's your registration code?");
        String containerId = readLineFromConsole(TextUtils.ANSI_CYAN + "What's the container identifier?");

        print("\n" + TextUtils.ANSI_YELLOW + "Locating the container #" + containerId + "...");

        String path = ImportFromDataBase.getRouteOfContainer(containerId, reg);

        if (!path.contains("Warning")){
            print("\n" + path);
        }
        else {
            print("Warning");
        }
    }
}
