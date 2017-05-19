import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Math.*;

/**
 * Created by serra on 19/05/2017.
 */
public class Utils {

    public static double distance(double longitudeA, double latitudeA, double longitudeB, double latitudeB) {
        return 60 * acos(sin(latitudeA) * sin(latitudeB) + cos(latitudeA) * cos(latitudeB) * cos(longitudeB - longitudeA));
    }

    public static ArrayList<Commune> readCSV(String filename) throws IOException {

        ArrayList<Commune> communeList = new ArrayList<>();
        String line;
        FileReader fr = new FileReader(new File(filename));
        BufferedReader br = new BufferedReader(fr);
        br.readLine();
        while ((line = br.readLine()) != null) {
            String[] temp = line.split(";");
            communeList.add(new Commune(temp[0], temp[1], Integer.parseInt(temp[2]), Double.parseDouble(temp[3]), Double.parseDouble(temp[4])));
        }
        return communeList;
    }

    public static double[][] buildGraphe(ArrayList<Commune> communes){

        return null;
    }
}
