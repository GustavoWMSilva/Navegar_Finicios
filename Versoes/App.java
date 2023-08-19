import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {

    public static void main(String args[]) {
        Path path = Paths.get("caso0.txt");
        BufferedReader reader;

        try {
            reader = Files.newBufferedReader(path, Charset.defaultCharset());

            String line = null;
            String data[] = reader.readLine().split("\\s+");
            Integer[][] water = new Integer[Integer.parseInt(data[0])][Integer.parseInt(data[1])];
            int j = 0;

            Integer[][] pos = new Integer[10][2];

            while ((line = reader.readLine()) != null) {
                data = line.split("");

                for (int i = 0; i < 150; i++) {

                    if (data[i].equals(".")) {
                        water[j][i] = 0;
                    } else if (data[i].equals("*")) {
                        water[j][i] = -1;
                    } else {
                        water[j][i] = Integer.parseInt(data[i]);
                        pos[Integer.parseInt(data[i])][0] = j;
                        pos[Integer.parseInt(data[i])][1] = i;

                    }

                }
                j++;
            }

            for (int i = 1; i < 10; i++) {
                System.out.print(water[pos[i][0]][pos[i][1]]);
            }

            j = pos[1][0];
            int i = pos[1][1];
            int v = pos[2][0];
            int w = pos[2][1];

            System.out.println(water[j][i]);
            while (water[j][i] != 2) {
                while (j != v && water[j][i] != -1) {
                    j--;
                    while (i != w && water[j][i] != -1) {
                        i--;
                    }
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
