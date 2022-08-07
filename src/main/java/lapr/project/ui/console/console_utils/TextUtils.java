package lapr.project.ui.console.console_utils;

import lapr.project.controller.App;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.util.Objects;

import static lapr.project.ui.console.console_utils.Utils.print;

public class TextUtils {
   public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public TextUtils(){
        try {
            animationIntro("\n" +
                    "░█████╗░░█████╗░██████╗░░██████╗░░█████╗░  ░██████╗██╗░░██╗██╗██████╗░░██████╗\n" +
                    "██╔══██╗██╔══██╗██╔══██╗██╔════╝░██╔══██╗  ██╔════╝██║░░██║██║██╔══██╗██╔════╝\n" +
                    "██║░░╚═╝███████║██████╔╝██║░░██╗░██║░░██║  ╚█████╗░███████║██║██████╔╝╚█████╗░\n" +
                    "██║░░██╗██╔══██║██╔══██╗██║░░╚██╗██║░░██║  ░╚═══██╗██╔══██║██║██╔═══╝░░╚═══██╗\n" +
                    "╚█████╔╝██║░░██║██║░░██║╚██████╔╝╚█████╔╝  ██████╔╝██║░░██║██║██║░░░░░██████╔╝\n" +
                    "░╚════╝░╚═╝░░╚═╝╚═╝░░╚═╝░╚═════╝░░╚════╝░  ╚═════╝░╚═╝░░╚═╝╚═╝╚═╝░░░░░╚═════╝░", ANSI_CYAN, ANSI_BLUE);
            playSound("/horn.wav");
        } catch (Exception ignored) {
        }
    }

    public static void animationIntro(String text, String color, String color1) {
        for (String line : text.split("\n")) {
            if (!line.equals("")){
                String l1 = line.split(" {2}")[0];
                String l2 = line.split(" {2}")[1];

                String finalS = color + l1 + "  " + color1 + l2 + color1;
                print(finalS);
            }
        }
    }

    private static byte anim;

    public static void animate() {
        switch (anim) {
            case 1:
                print("Type here:)");
                break;
            case 2:
                print("Type here:(");
                break;
            case 3:
                print("Type here:|");
                break;
            default:
                anim = 0;
                print("Type here:");
        }
        anim++;
    }

    static Clip clip ;

    public static void playSound(String filepath){
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        String status = (String) App.getInstance().getCompany().getProperties().get("sound.enabled");
        if(status.equals("true")) {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(TextUtils.class.getResourceAsStream(filepath)));
                // NOTICE: I am only initializing and NOT declaring (no capital Clip)
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // call stop method to stop clip form playing
    public static void dummyMethod(){
        clip.stop();
    }
}
