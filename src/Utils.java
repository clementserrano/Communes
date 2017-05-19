import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Math.*;

/**
 * Created by clementserrano on 19/05/2017.
 */
public class Utils {

    public static double distance(Commune communeA, Commune communeB) {
        return 60 * 1.852 * acos(sin(communeA.getLatitude()) * sin(communeB.getLatitude()) +
                cos(communeA.getLatitude()) * cos(communeB.getLatitude()) *
                        cos(communeB.getLongitude() - communeA.getLongitude()));
    }

    public static ArrayList<Commune> readCSV(String filename) throws IOException {

        ArrayList<Commune> communeList = new ArrayList<>();
        String line;
        FileReader fr = new FileReader(new File(filename));
        BufferedReader br = new BufferedReader(fr);
        br.readLine();
        while ((line = br.readLine()) != null) {
            String[] temp = line.split(";");
            communeList.add(new Commune(temp[0], temp[1], Integer.parseInt(temp[2]),
                    Double.parseDouble(temp[3].replace(",", ".")),
                    Double.parseDouble(temp[4].replace(",", "."))));
        }
        return communeList;
    }

    public static Graphe buildGraphe(ArrayList<Commune> communes) {
        Graphe graphe = new Graphe();
        for (int i = 0; i < communes.size(); i++) {
            for (int j = 0; j < communes.size(); j++) {
                if(communes.get(i)!=communes.get(j)){
                    Tuple temp = new Tuple(communes.get(i), communes.get(j));
                    if (!graphe.contains(temp)) {
                        graphe.add(temp);
                    }
                }
            }
        }
        return graphe;
    }

    public static ArrayList<Commune> filterPop(ArrayList<Commune> communes, int population) {
        ArrayList<Commune> tmp = new ArrayList<>();
        for (Commune c : communes) {
            if (c.getPopulation() > population) {
                tmp.add(c);
            }
        }
        return tmp;
    }
}
