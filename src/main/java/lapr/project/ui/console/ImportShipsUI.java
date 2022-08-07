package lapr.project.ui.console;

import lapr.project.controller.ShipController;
import lapr.project.ui.console.console_utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImportShipsUI implements Runnable {
    private final ShipController controller = new ShipController();
    private String fileName;
    private final List<String> options;

    public ImportShipsUI() {
        options = new ArrayList<>();
    }

    public void run() {
        try {
            selectOption();
            controller.importShips("files/" + fileName);
            Utils.print("\n******************************\nSuccess!");
        } catch (Exception e) {
            Utils.print(e.getMessage());
        }
    }

    public void selectOption() {
        getFiles();
        try {
            int index = Utils.showAndSelectIndex(options, "Select one of these files containing ships:");
            fileName = options.get(index);
        }catch (Exception e){
            selectOption();
        }


    }

    public void getFiles(){
        for (File f : controller.getFiles()){
            if (!options.contains(f.getName())) {
                options.add(f.getName());
            }
        }
    }
}