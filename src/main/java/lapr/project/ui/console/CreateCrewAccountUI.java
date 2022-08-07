package lapr.project.ui.console;

import lapr.project.controller.App;
import lapr.project.controller.CreateCrewAccountController;
import lapr.project.ui.console.console_utils.TextUtils;

import java.sql.SQLException;

import static lapr.project.ui.console.console_utils.Utils.print;

public class CreateCrewAccountUI implements Runnable {

    @Override
    public void run() {
        CreateCrewAccountController controller = new CreateCrewAccountController();
        try {
            String[] values = controller.createCrewAccount(App.getInstance().getCompany().getDatabaseConnection(), App.getInstance().getCompany().getAuthFacade().getCurrentUserSession().getUserId().getString()
            );
            print(TextUtils.ANSI_CYAN + "[*] " + TextUtils.ANSI_RESET + "User account created successfully :)");
            print(TextUtils.ANSI_CYAN + "[*] " + TextUtils.ANSI_RESET + "Username: " + values[0] + "\n    Password: " + values[1]);
        } catch (SQLException throwables) {
            print(TextUtils.ANSI_RED + "[!] " + TextUtils.ANSI_RESET + " An error occurred: " + throwables.getMessage());

        }

    }
}
