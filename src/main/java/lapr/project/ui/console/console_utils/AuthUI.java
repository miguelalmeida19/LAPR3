package lapr.project.ui.console.console_utils;


import lapr.project.auth.domain.shared.Constants;
import lapr.project.auth.mappers.dto.UserRoleDTO;
import lapr.project.controller.AuthController;
import lapr.project.ui.console.*;
import lapr.project.ui.console.MenuItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */

public class AuthUI implements Runnable{
    private final AuthController ctrl;

    public AuthUI()
    {
        ctrl = new AuthController();
    }

    public void run()
    {
        boolean success = doLogin();

        if (success)
        {
            List<UserRoleDTO> roles = this.ctrl.getUserRoles();
            if ( (roles == null) || (roles.isEmpty()) )
            {
                Utils.print("User has not any role assigned.");
            }
            else
            {
                UserRoleDTO role = selectsRole(roles);
                if (!Objects.isNull(role))
                {
                    List<lapr.project.ui.console.MenuItem> rolesUI = getMenuItemForRoles();
                    this.redirectToRoleUI(rolesUI,role);
                }
                else
                {
                    Utils.print("User did not select a role.");
                }
            }
        }
        this.logout();
    }

    private List<lapr.project.ui.console.MenuItem> getMenuItemForRoles()
    {
        List<lapr.project.ui.console.MenuItem> rolesUI = new ArrayList<>();
        rolesUI.add(new lapr.project.ui.console.MenuItem(Constants.ROLE_CLIENT, new ClientUI()));
        rolesUI.add(new lapr.project.ui.console.MenuItem(Constants.ROLE_FLEET_MANAGER, new FleetManagerUI()));
        rolesUI.add(new lapr.project.ui.console.MenuItem(Constants.ROLE_TRAFFIC_MANAGER, new TrafficManagerUI()));
        rolesUI.add(new lapr.project.ui.console.MenuItem(Constants.ROLE_WAREHOUSE_STAFF, new WarehouseStaffUI()));
        rolesUI.add(new lapr.project.ui.console.MenuItem(Constants.ROLE_WAREHOUSE_MANAGER, new WarehouseManagerUI()));
        rolesUI.add(new lapr.project.ui.console.MenuItem(Constants.ROLE_PORT_STAFF, new PortStaffUI()));
        rolesUI.add(new lapr.project.ui.console.MenuItem(Constants.ROLE_PORT_MANAGER, new PortManagerUI()));
        rolesUI.add(new lapr.project.ui.console.MenuItem(Constants.ROLE_CHIEF_ELECTRICAL_ENGINEER, new ChiefElectricalEnginnerUI()));
        rolesUI.add(new lapr.project.ui.console.MenuItem(Constants.ROLE_TRUCK_DRIVER, new TruckDriverUI()));
        rolesUI.add(new lapr.project.ui.console.MenuItem(Constants.ROLE_SHIP_CAPTAIN, new ShipCaptainUI()));
        rolesUI.add(new lapr.project.ui.console.MenuItem(Constants.ROLE_CREW_ACCOUNT, new CrewUI()));

        return rolesUI;
    }

    private boolean doLogin()
    {
        Utils.print(TextUtils.ANSI_CYAN + "\nLogin UI:" + TextUtils.ANSI_BLUE);

        int maxAttempts = 3;
        boolean success;
        do
        {
            maxAttempts--;
            String id = Utils.readLineFromConsole("Enter UserId/Email: ");
            String pwd = Utils.readLineFromConsole("Enter Password: ");

            success = ctrl.doLogin(id, pwd);
            if (!success)
            {
                Utils.print("Invalid UserId and/or Password. \n You have  " + maxAttempts + " more attempt(s).");
            }

        } while (!success && maxAttempts > 0);
        return success;
    }

    private void logout()
    {
        ctrl.doLogout();
    }

    private void redirectToRoleUI(List<lapr.project.ui.console.MenuItem> rolesUI, UserRoleDTO role)
    {
        boolean found = false;
        Iterator<lapr.project.ui.console.MenuItem> it = rolesUI.iterator();
        while (it.hasNext() && !found)
        {
            MenuItem item = it.next();
            found = item.hasDescription(role.getDescription());
            if (found)
                item.run();
        }
        if (!found)
            Utils.print("There is no UI for users with role '" + role.getDescription() + "'");
    }

    private UserRoleDTO selectsRole(List<UserRoleDTO> roles)
    {
        if (roles.size() == 1)
            return roles.get(0);
        else
            return (UserRoleDTO) Utils.showAndSelectOne(roles, "Select the role you want to adopt in this session:");
    }


}