package lapr.project.data;

import lapr.project.auth.domain.model.Password;
import lapr.project.auth.domain.shared.Constants;
import lapr.project.controller.App;
import lapr.project.model.Container;
import lapr.project.model.ISO_CODE;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

import static lapr.project.data.ExportToDatabase.*;
import static lapr.project.ui.console.console_utils.Utils.print;

public class ContainerGenerator {
    String d;

    public ContainerGenerator(int n) {
        Random rand = new Random();
        ArrayList<String> list = null;
        try {
            try (Scanner s = new Scanner(new File("files/clientNames.txt"))) {
                list = new ArrayList<>();
                while (s.hasNextLine()) {
                    list.add(s.nextLine());
                }
            }
        } catch (Exception ignored) {
        }
        StringBuilder query = new StringBuilder(BEGIN);

        for (int i = 0; i < n; i++) {
            d = "Container c" + i + " = new Container(";
            int id = rand.nextInt(9999999 - 1000000) + 1000000;
            double randomValue = 10 + (200 - 10) * rand.nextDouble();
            double randomValue1 = 10 + (200 - 10) * rand.nextDouble();
            double randomValue2 = 10 + (200 - 10) * rand.nextDouble();
            double randomValue3 = 10 + (200 - 10) * rand.nextDouble();
            double temperature = -5 + rand.nextDouble() * (7 - (-5));
            ISO_CODE isoCode = randomEnum(ISO_CODE.class);

            assert list != null;
            String email = list.get(i).replace(" ", "").replace(".", "").toLowerCase(Locale.ROOT) + "@isep.ipp.pt";
            Password password = new Password(list.get(i));
            String pass = password.getString();
            pass = pass.substring(0, Math.min(pass.length(), 10));

            query.append("\nINSERT INTO USER_ACCOUNT (EMAIL,PASSWORD,ROLEID) " + VALUES + "('").append(email).append("','").append(pass).append("',").append(Constants.ROLE_CLIENT_ID).append(");");

            Container c = new Container(id, String.valueOf(randomValue), randomValue1, randomValue2, randomValue3, isoCode, temperature, email);
            App.getInstance().getCompany().getContainerStore().getContainerList().add(c);
        }
        query.append(END);
        print(query);
        try {
            BufferedWriter bw;
            try (FileWriter fw = new FileWriter("files/clients.txt", true)) {
                bw = new BufferedWriter(fw);
            }
            bw.write(query.toString());
            bw.newLine();
            bw.close();
        } catch (Exception ignored) {
        }
    }

    public static <T extends Enum<ISO_CODE>> T randomEnum(Class<T> clazz) {
        Random random = new Random();
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
}
