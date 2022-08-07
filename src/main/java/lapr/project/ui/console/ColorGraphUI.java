package lapr.project.ui.console;

import lapr.project.controller.ColorMapController;
import lapr.project.ui.console.console_utils.TextUtils;
import lapr.project.ui.console.console_utils.Utils;

import java.util.Map;
import java.util.TreeSet;

import static lapr.project.ui.console.console_utils.Utils.print;

public class ColorGraphUI implements Runnable {

    @Override
    public void run() {
        ColorMapController c = new ColorMapController();
        int s = Utils.readIntegerFromConsole(TextUtils.ANSI_CYAN + "[*] " + TextUtils.ANSI_RESET + "Please insert the n: ");
        print(TextUtils.ANSI_CYAN + "[*] " + TextUtils.ANSI_RESET + "Generating the map and coloring it ...");
        Map<Object, Integer> map = c.colorMap(s, false);
        TreeSet<Integer> set = new TreeSet<>(map.values());
        map.keySet().stream().map(cs -> TextUtils.ANSI_CYAN + "[*] " + TextUtils.ANSI_RESET + " Vertex: " + cs + " - color: " + map.get(cs)).forEach(Utils::print);
        print(TextUtils.ANSI_YELLOW + "\n[*] " + TextUtils.ANSI_RESET + "Number of colors used: " + set.last());
    }
}
