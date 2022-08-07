package lapr.project.ui.console;
import lapr.project.controller.US412Controller;
import lapr.project.ui.console.console_utils.TextUtils;

import static lapr.project.ui.console.console_utils.Utils.print;

public class US412UI implements Runnable {

    @Override
    public void run() {
        print(TextUtils.ANSI_CYAN + "Total energy to be delivered, to a container with an operating temperature of 7 °C: " + TextUtils.ANSI_BLUE + US412Controller.energiaTotalFornecer7C());
        print(TextUtils.ANSI_CYAN + "Total energy to be delivered, to a container with an operating temperature of -5 °C: " + TextUtils.ANSI_BLUE + US412Controller.energiaTotalFornecerMenos5C());
    }
}
