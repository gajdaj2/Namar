package analysis;

import java.io.*;

public class Loader {

    public String ReadFile() throws IOException {
        File file = new File("logs\\app.log");
        BufferedReader br = null;
        String all = "";
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String st;
        while ((st = br.readLine()) != null) {
            all = all + st;
        }
        return all;
    }
}