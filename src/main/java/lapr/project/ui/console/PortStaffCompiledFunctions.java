package lapr.project.ui.console;

import lapr.project.ui.console.console_utils.TextUtils;
import lapr.project.ui.console.console_utils.Utils;
import org.apache.commons.lang3.SystemUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import static lapr.project.ui.console.console_utils.Utils.print;

public class PortStaffCompiledFunctions implements Runnable{

    @Override
    public void run() {
        try {
            if (SystemUtils.IS_OS_LINUX) {

                String comd = TextUtils.ANSI_CYAN + "[>]" + TextUtils.ANSI_RESET + " Please type the cargo manifest id: ";
                int id = Utils.readIntegerFromConsole(comd);
                comd = TextUtils.ANSI_CYAN + "[>]" + TextUtils.ANSI_RESET + " Please type the X position of the ship you want to verify: ";
                int x = Utils.readIntegerFromConsole(comd);
                comd = TextUtils.ANSI_CYAN + "[>]" + TextUtils.ANSI_RESET + " Please type the Y position of the ship you want to verify: ";
                int y = Utils.readIntegerFromConsole(comd);
                comd = TextUtils.ANSI_CYAN + "[>]" + TextUtils.ANSI_RESET + " Please type the Z position of the ship you want to verify: ";
                int z = Utils.readIntegerFromConsole(comd);



                ProcessBuilder builder = new ProcessBuilder(System.getProperty("user.dir") + "/bin/main", String.valueOf(id),String.valueOf(x) ,String.valueOf(y), String.valueOf(z));
                builder.directory(new File(System.getProperty("user.dir") + "/bin"));

                Process process = builder.start();

                InputStream is = process.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String line;
                while ((line = br.readLine()) != null) {
                    print(line);
                }
                print("Program terminated!");
            } else {
                print(TextUtils.ANSI_RED + "[!]" + TextUtils.ANSI_RESET + " This function only runs in linux. Please install a " + TextUtils.ANSI_GREEN + "REAL" + TextUtils.ANSI_RESET + " operating system and try again.");
            }
        }catch (Exception e){
            print(TextUtils.ANSI_RED+"[!] "+TextUtils.ANSI_RESET+"Something went wrong: "+e.getMessage());
        }
    }
}
