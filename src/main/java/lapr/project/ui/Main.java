package lapr.project.ui;

import lapr.project.ui.console.MainMenuUI;
import lapr.project.ui.console.console_utils.TextUtils;

import java.io.File;


public class Main {

    public static void main(String[] args)
    {
        try
        {
            deleteOutputFile();
            new TextUtils();
            MainMenuUI menu = new MainMenuUI();
            menu.run();
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }
    private static void deleteOutputFile(){
        File myObj = new File("output.txt");
        myObj.delete();
    }
}
