package lapr.project.ui.console.console_utils;

import lapr.project.controller.App;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static lapr.project.ui.console.console_utils.TextUtils.*;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Utils {
    public static int inputWithDefault(String text, int defaultValue){
        try
        {

            print(text + "(default value: "+defaultValue+"): ");
            InputStreamReader converter = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(converter);
            String line = in.readLine();
            Utils.log(line);
            if(line.equals("") ||line.isEmpty() ){
                return defaultValue;
            }else{
                return Integer.parseInt(line);
            }
        } catch (Exception e)
        {
            return inputWithDefault(text, defaultValue);
        }
    }
    public static double inputDoubleWithDefault(String text, int defaultValue){
        try
        {
            print(text + "(default value: "+defaultValue+"): ");
            InputStreamReader converter = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(converter);
            String line = in.readLine();
            Utils.log(line);
            if(line.equals("") ||line.isEmpty()){
                return defaultValue;
            }else{
                return Double.parseDouble(line);
            }
        } catch (Exception e)
        {
            return inputWithDefault(text, defaultValue);
        }
    }
    public static ResultSet executeQeryDatabase(String query) throws SQLException {
        Statement s = App.getInstance().getCompany().getDatabaseConnection().getConnection().createStatement();
        return s.executeQuery(query);
    }
    public static String readLineFromConsole(String prompt)
    {
        try
        {
            Utils.print("\n" + prompt);

            InputStreamReader converter = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(converter);
            String line = in.readLine();
            log(line);
            return line;
        } catch (Exception e)
        {
            return readLineFromConsole(prompt);
        }
    }

    public static int readIntegerFromConsole(String prompt)
    {
        try {
            String input = readLineFromConsole(prompt);

            return Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            return readIntegerFromConsole(prompt);
        }
    }

    public static LocalDateTime readDateFromConsole(String prompt)
    {
            try
            {
                print(prompt);
                String date = readLineFromConsole("Date: (dd/MM/YYYY)");
                String hour = readLineFromConsole("Hour: (HH:mm)");

                String full = date + " " + hour;

                DateTimeFormatter ft = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

                return LocalDateTime.parse(full, ft);
            } catch (Exception ex)
            {
                return readDateFromConsole(prompt);
            }
    }

    public static Object showAndSelectOne(List<?> list, String header)
    {
        showList(list,header);
        return selectsObject(list);
    }

    public static int showAndSelectIndex(List<?> list, String header)
    {
        showList(list,header);
        return selectsIndex(list);
    }

    public static void showList(List<?> list, String header)
    {
        playSound("/waiting.wav");
        Utils.print(TextUtils.ANSI_CYAN + header + TextUtils.ANSI_BLUE);

        int index = 0;
        for (Object o : list)
        {
            index++;

            Utils.print(TextUtils.ANSI_YELLOW + index + ". " + TextUtils.ANSI_BLUE + o.toString());
        }
        Utils.print("");
        Utils.print(TextUtils.ANSI_YELLOW + "0 - "  + TextUtils.ANSI_BLUE + "Cancel");
    }

    public static Object selectsObject(List<?> list)
    {
        String input;
        int value;
        do
        {
            input = Utils.readLineFromConsole("Type your option: ");
            value = Integer.parseInt(input);
        } while (value < 0 || value > list.size());

        if (value == 0)
        {
            return null;
        } else
        {
            return list.get(value - 1);
        }
    }

    public static int selectsIndex(List<?> list)
    {
        String input;
        int value;
        do
        {
            input = Utils.readLineFromConsole("Type your option: ");
            value = Integer.parseInt(input);
        } while (value < 0 || value > list.size());
        dummyMethod();
        playSound("/blip.wav");
        return value - 1;
    }

    public static void print(String text){
        System.out.println(text);
        log(text);


    }

    public static void print(int text){
        System.out.println(text);
        log(String.valueOf(text));



    }

    public static void print(double text){
        System.out.println(text);
        log(String.valueOf(text));

    }

    public static void print(){
        System.out.println();
        log("\n");

    }

    public static void print(Object o){
        System.out.println(o.toString());
        log(o.toString());
    }
    public static void log(String t)  {

        t = t.replace(TextUtils.ANSI_BLACK, "");
        t = t.replace(TextUtils.ANSI_GREEN, "");
        t = t.replace(TextUtils.ANSI_BLUE, "");
        t = t.replace(TextUtils.ANSI_CYAN, "");
        t = t.replace(TextUtils.ANSI_RESET, "");
        t = t.replace(TextUtils.ANSI_PURPLE, "");
        t = t.replace(TextUtils.ANSI_RED, "");
        t = t.replace(TextUtils.ANSI_WHITE, "");
        t = t.replace(TextUtils.ANSI_YELLOW, "");
        t = t + "\n";
        try {
            String savestr = "output.txt";
            File f = new File(savestr);

            PrintWriter out;
            if ( f.exists() && !f.isDirectory() ) out = new PrintWriter(new FileOutputStream(savestr, true));
            else out = new PrintWriter(savestr);
            out.append(t);
            out.close();
        }catch (Exception e){
            print("Erro logging: "+e.getMessage());
        }

    }

}
