package lapr.project.ui.console;

import lapr.project.controller.App;
import lapr.project.controller.ContainerStatusController;
import lapr.project.ui.console.console_utils.TextUtils;
import lapr.project.ui.console.console_utils.Utils;

import static lapr.project.ui.console.console_utils.Utils.print;

public class ContainerStatusUI implements Runnable{

    @Override
    public void run() {
        String containerID = Utils.readLineFromConsole(TextUtils.ANSI_CYAN+"[*] "+TextUtils.ANSI_RESET+"Please insert the container ID: ");
        ContainerStatusController s = new ContainerStatusController(App.getInstance().getCompany().getDatabaseConnection());
        try {
            String containerSatus = s.containerStatus(containerID);
            print(TextUtils.ANSI_CYAN +"[*] "+TextUtils.ANSI_RESET + containerSatus);

        }catch (Exception e){
            print(TextUtils.ANSI_RED+ "[!] "+TextUtils.ANSI_RESET+" Error when trying to get the container status. The message error was: "+e.getMessage());
        }
    }
}
