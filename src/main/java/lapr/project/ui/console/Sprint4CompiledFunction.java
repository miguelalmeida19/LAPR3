package lapr.project.ui.console;

import lapr.project.ui.console.console_utils.TextUtils;
import lapr.project.ui.console.console_utils.Utils;
import org.apache.commons.lang3.SystemUtils;


import static lapr.project.ui.console.console_utils.Utils.print;

public class Sprint4CompiledFunction implements Runnable{

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
                    String[] command = {"/bin/sh", "-c",
                            "gnome-terminal"+ " -- bash"+"  -c \"cd "+System.getProperty("user.dir") + "/bin"+" && ./mainSprint4 "+ id+" "+x+" "+y+" "+ z + ";read -p 'Press any key to continue... ' -n1 -s\""+" --wait"};
                    Runtime rt = Runtime.getRuntime();

                    rt.exec(command);

                } else {
                    print(TextUtils.ANSI_RED + "[!]" + TextUtils.ANSI_RESET + " This function only runs in linux. Please install a " + TextUtils.ANSI_GREEN + "REAL" + TextUtils.ANSI_RESET + " operating system and try again.");
                }
            }catch (Exception e){
                print(TextUtils.ANSI_RED+"[!] "+TextUtils.ANSI_RESET+"Something went wrong: "+e.getMessage());
            }
        }


}
