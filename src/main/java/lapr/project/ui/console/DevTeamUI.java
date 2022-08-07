package lapr.project.ui.console;
import lapr.project.ui.console.console_utils.TextUtils;
import lapr.project.ui.console.console_utils.Utils;

import static lapr.project.ui.console.console_utils.Utils.print;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class DevTeamUI implements Runnable{

    public DevTeamUI()
    {
        // Explain why this is empty
    }
    public void run() {
        try {
            print(TextUtils.ANSI_CYAN + "\t\n" +
                    " ___________  __    __    _______           ___      __  ___      ___  __        _______   ________   __     ______    _______     \n" +
                    "(\"     _   \")/\" |  | \"\\  /\"     \"|         |\"  |    /\"\"\\|\"  \\    /\"  |/\"\"\\      /\"      \\ |\"      \"\\ |\" \\   /\" _  \"\\  /\"     \"|    \n" +
                    " )__/  \\\\__/(:  (__)  :)(: ______)         ||  |   /    \\\\   \\  //  //    \\    |:        |(.  ___  :)||  | (: ( \\___)(: ______)    \n" +
                    "    \\\\_ /    \\/      \\/  \\/    |           |:  |  /' /\\  \\\\\\  \\/. .//' /\\  \\   |_____/   )|: \\   ) |||:  |  \\/ \\      \\/    |      \n" +
                    "    |.  |    //  __  \\\\  // ___)_       ___|  /  //  __'  \\\\.    ////  __'  \\   //      / (| (___\\ |||.  |  //  \\ _   // ___)_     \n" +
                    "    \\:  |   (:  (  )  :)(:      \"|     /  :|_/ )/   /  \\\\  \\\\\\   //   /  \\\\  \\ |:  __   \\ |:       :)/\\  |\\(:   _) \\ (:      \"|    \n" +
                    "     \\__|    \\__|  |__/  \\_______)    (_______/(___/    \\___)\\__/(___/    \\___)|__|  \\___)(________/(__\\_|_)\\_______) \\_______)    \n" +
                    "                                                                                                                                   \n" + TextUtils.ANSI_RESET);

            print("\n");
            writeTyping(TextUtils.ANSI_YELLOW + "</ Development Team: \\>\n" + TextUtils.ANSI_RESET, 100);
            writeTyping("\t Pedro Costa -", 100);
            Thread.sleep(300);
            writeTyping(" 1200949@isep.ipp.pt  🤩 \n", 100);
            Thread.sleep(100);

            writeTyping("\t André Gomes -", 100);
            Thread.sleep(300);
            writeTyping(" 1201108@isep.ipp.pt  😏\n", 100);
            Thread.sleep(100);

            writeTyping("\t José Gonçalves - ", 100);
            Thread.sleep(300);
            writeTyping("1201114@isep.ipp.pt  😎\n", 100);
            Thread.sleep(100);

            writeTyping("\t Miguel Almeida - ", 100);
            Thread.sleep(300);
            writeTyping("1201115@isep.ipp.pt  😴\n", 100);
            print("\n");
        } catch (InterruptedException ignored) {
        }
    }
    public void writeTyping(String sentence, int timeBetweenStrokesMilliSeconds) throws InterruptedException {
        for (int n = 0; n < sentence.length(); n++) {
            print(String.valueOf(sentence.charAt(n)));
            Thread.sleep(timeBetweenStrokesMilliSeconds); //40
        }
    }

}