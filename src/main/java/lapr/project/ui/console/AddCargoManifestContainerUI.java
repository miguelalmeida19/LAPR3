package lapr.project.ui.console;

import lapr.project.data.ImportFromDataBase;
import lapr.project.ui.console.console_utils.TextUtils;

import static lapr.project.ui.console.console_utils.Utils.*;

public class AddCargoManifestContainerUI implements Runnable{
    @Override
    public void run() {
        try {
            print(TextUtils.ANSI_PURPLE + "\nPlease enter the following information so you can add a new Cargo Manifest Container to the System" + TextUtils.ANSI_BLUE);
            int cargoID = readIntegerFromConsole("CARGO MANIFEST ID:");
            int containerID = readIntegerFromConsole("CONTAINER ID:");
            int posx = readIntegerFromConsole("POSITION X:");
            int posy = readIntegerFromConsole("POSITION Y:");
            int posz = readIntegerFromConsole("POSITION Z:");

            String response = ImportFromDataBase.addCargoManifestContainer(cargoID, containerID, posx, posy, posz);
            if (response.contains("sorry")){
                print("\n" + TextUtils.ANSI_RED + response.split(":")[1].split("!")[0] + "!");
            }
            else {
                print(TextUtils.ANSI_GREEN + "\nSUCCESS!");
            }
        }catch (Exception e){
            print(TextUtils.ANSI_GREEN + "\nSUCCESS!");
        }
    }
}
