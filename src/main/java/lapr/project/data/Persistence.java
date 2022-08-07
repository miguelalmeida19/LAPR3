package lapr.project.data;
import lapr.project.auth.AuthFacade;
import lapr.project.auth.UserSession;
import lapr.project.auth.domain.model.User;
import lapr.project.controller.App;
import lapr.project.model.Message;
import lapr.project.model.Port;
import lapr.project.model.Ship;
import lapr.project.store.PortStore;
import lapr.project.store.ShipStore;
import lapr.project.ui.console.console_utils.TextUtils;

import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

import static lapr.project.ui.console.console_utils.Utils.print;

public class Persistence {
    private Persistence(){
        //not needed. static class
    }
    public static void getShipsFromDataBase(Statement statement, ShipStore shipStore) {
        try {
            String sql;
            sql = "SELECT * FROM Ship";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Ship ship = new Ship(
                        rs.getInt("mmsi"),
                        rs.getString("name"),
                        rs.getString("imo"),
                        rs.getString("call_sign"),
                        rs.getString("vessel_type"),
                        rs.getDouble("length"),
                        rs.getDouble("width"),
                        rs.getDouble("draft"),
                        "100");

                shipStore.getShipList().add(ship);
                shipStore.getBstMMSI().put(ship.getMMSI(), ship);
                shipStore.getBstIMO().put(ship.getIMO(), ship);
                shipStore.getBstCallSign().put(ship.getCallsign(), ship);
            }
        } catch (Exception e) {
            print(TextUtils.ANSI_RED + "[!] Error in the process of importing ships from the database. The error message is: "+TextUtils.ANSI_WHITE+e.getMessage()+TextUtils.ANSI_RESET);
        }

    }

    public static void getMessagesFromDataBase(Statement statement, ShipStore shipStore){
        String sql;
        try {
            for (Ship s: shipStore.getShipList()){
                sql = "SELECT * FROM Messages WHERE shipimo = '" + s.getIMO() + "'";
                ResultSet rs1 = statement.executeQuery(sql);
                while (rs1.next()){
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");


                    String date = rs1.getString("base_date_time");
                    date = date.split(":")[0] + ":" + date.split(":")[1] + date.split(":")[2].replace("00","");
                    Message message = new Message(
                            LocalDateTime.parse(date, formatter),
                            rs1.getDouble("latitude"),
                            rs1.getDouble("longitude"),
                            rs1.getDouble("sog"),
                            rs1.getDouble("cog"),
                            rs1.getDouble("heading"),
                            rs1.getString("transceiver_class"));
                    s.addMessage(message);
                }
            }
        }catch (Exception e){
            print(TextUtils.ANSI_RED + "[!] Error in the process of importing messages from the database. The error message is: "+TextUtils.ANSI_WHITE+e.getMessage()+TextUtils.ANSI_RESET);

        }

    }

    public static void getPortsFromDataBase(Statement statement, PortStore portStore) {
        try {
            String sql;
            sql = "SELECT * FROM Port";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Port port = new Port(
                        rs.getString("continent"),
                        rs.getString("country_name"),
                        String.valueOf(rs.getInt("id")),
                        rs.getString("name"),
                        rs.getDouble("latitude"),
                        rs.getDouble("longitude"));

                portStore.getPortList().add(port);
                double[] coordinates = new double[2];
                coordinates[0] = port.getLatitude();
                coordinates[1] = port.getLongitude();
                portStore.getPortHashMap().put(coordinates, port);
            }
            portStore.getKdt().insertBalanced(portStore.getPortHashMap());
        } catch (Exception e) {
            print(TextUtils.ANSI_RED + "[!] Error in the process of importing ports from the database. The error message is: "+TextUtils.ANSI_WHITE+e.getMessage()+TextUtils.ANSI_RESET);

        }

    }

    public static void getUsersFromDataBase(Statement statement, AuthFacade authFacade) {
        try {
            String sql;
            sql = "SELECT email, password, employeeid, roleid FROM User_Account";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String email = rs.getString("email");
                String password = rs.getString("password");
                String roleid = rs.getString("roleid");

                String name = email.split("@")[0].toUpperCase(Locale.ROOT);
                if (roleid.length()>1){
                    roleid = "000" + roleid;
                }else {
                    roleid = "0000" + roleid;
                }

                authFacade.addUserWithRole(name, email, password, roleid);
            }
        } catch (Exception e) {
            print(TextUtils.ANSI_RED + "[!] Error in the process of importing ports from the database. The error message is: "+TextUtils.ANSI_WHITE+e.getMessage()+TextUtils.ANSI_RESET);

        }

    }

    public static UserSession doLogin(String email, String password){
        try {
            String query = "SELECT * FROM USER_ACCOUNT WHERE EMAIL='" + email + "' AND PASSWORD='" + password + "'";
            ResultSet rs = App.getInstance().getCompany().getStatement().executeQuery(query);
            if (rs.next()){
                App.getInstance().getCompany().getAuthFacade().addUserWithRole(email.split("@")[0], email, password, String.format("%05d", rs.getInt("ROLEID")));
                Optional<User> user = App.getInstance().getCompany().getAuthFacade().getUsers().getById(email);
                if (user.isPresent()){
                    return new UserSession(user.get());
                }
            }
        }catch (Exception ignored){
            //ignored

        }
        return null;
    }
}