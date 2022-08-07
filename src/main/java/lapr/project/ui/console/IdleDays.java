package lapr.project.ui.console;

import lapr.project.controller.App;
import lapr.project.ui.console.console_utils.TextUtils;
import lapr.project.ui.console.console_utils.Utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Calendar;

import static lapr.project.ui.console.console_utils.Utils.print;

public class IdleDays implements Runnable{

    @Override
    public void run()  {
        try{
            int codeVehicle = Utils.readIntegerFromConsole(TextUtils.ANSI_CYAN+"[>]"+TextUtils.ANSI_RESET+" Please type the vehicle id: ");
            print(TextUtils.ANSI_YELLOW+"[*] "+TextUtils.ANSI_RESET+"Processing...");
            Connection conn = App.getInstance().getCompany().getDatabaseConnection().getConnection();
            CallableStatement cstmt = conn.prepareCall("{? = call IDLEDAYS(?,?)}");
            cstmt.registerOutParameter(1, Types.FLOAT);
            cstmt.setInt(2, Calendar.getInstance().get(Calendar.YEAR));
            cstmt.setInt(3, codeVehicle);

            cstmt.executeUpdate();
            double code = cstmt.getFloat(1);
            if(code == -3){
                print(TextUtils.ANSI_RED+"[!] "+TextUtils.ANSI_RESET+"No data found for this ship in the specified year. Pleas insert a valid year and try again");
            }
            if(code == -2){
                print(TextUtils.ANSI_RED+"[!] "+TextUtils.ANSI_RESET+"No vehicle with this code was found. Please try again with a new vehicle id");
            }
            if(code == -1){
                print(TextUtils.ANSI_RED+"[!] "+TextUtils.ANSI_RESET+"The vehicle id introduced isn't for a ship. Please try again with a new vehicle id that belongs to a ship");
            }
            if(code >= 0){
                print(TextUtils.ANSI_CYAN+"[*] "+TextUtils.ANSI_RESET+"The number of idle days of this ship in the current year is: "+code+ " days.");
            }
        }catch(SQLException e){
            print(TextUtils.ANSI_RED+"[*] "+TextUtils.ANSI_RESET+"Error in the connection with the database. Error: "+e.getMessage());
        }

    }
}
