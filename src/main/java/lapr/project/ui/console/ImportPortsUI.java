package lapr.project.ui.console;

import lapr.project.controller.PortController;
import lapr.project.controller.ShipController;
import lapr.project.ui.console.console_utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImportPortsUI implements Runnable {
    private final PortController controller = new PortController();
    private String fileName;
    private final List<String> options;

    public ImportPortsUI() {
        options = new ArrayList<>();
    }

    public void run() {
        try {
            selectOption();
            controller.importPorts("files/" + fileName);
            Utils.print("\n******************************\nSuccess!");
        } catch (Exception e) {
            Utils.print(e.getMessage());
        }
    }

    public void selectOption() {
        getFiles();
        try {
            int index = Utils.showAndSelectIndex(options, "Select one of these files containing ports:");
            fileName = options.get(index);
        }catch (Exception e){
            selectOption();
        }


    }

    public void getFiles(){
        ShipController shipController = new ShipController();
        for (File f : shipController.getFiles()){
            if (!options.contains(f.getName())) {
                options.add(f.getName());
            }
        }
    }
}
