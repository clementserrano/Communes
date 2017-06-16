import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.*;

/**
 * Created by clementserrano on 19/05/2017.
 */
public class Utils {

    private static X _chemin;
    private static int _cout;

    public static int distance(Commune communeA, Commune communeB) {
        return (int) (60 * 1.852 * acos(sin(communeA.getLatitude()) * sin(communeB.getLatitude()) +
                cos(communeA.getLatitude()) * cos(communeB.getLatitude()) *
                        cos(communeB.getLongitude() - communeA.getLongitude())));
    }

    public static X readCSV(String filename) throws IOException {
        X X = new X();
        String line;
        FileReader fr = new FileReader(new File(filename));
        BufferedReader br = new BufferedReader(fr);
        br.readLine();
        while ((line = br.readLine()) != null) {
            String[] temp = line.split(";");
            X.add(new Commune(temp[0], temp[1], Integer.parseInt(temp[2]),
                    Double.parseDouble(temp[3].replace(",", ".")),
                    Double.parseDouble(temp[4].replace(",", "."))));
        }
        return X;
    }

    public static U buildGraphe(ArrayList<Commune> communes) {
        U graphe = new U();
        for (int i = 0; i < communes.size(); i++) {
            for (int j = 0; j < communes.size(); j++) {
                if (communes.get(i) != communes.get(j)) {
                    Arete temp = new Arete(communes.get(i), communes.get(j));
                    if (!graphe.contains(temp)) {
                        graphe.add(temp);
                    }
                }
            }
        }
        return graphe;
    }

    public static X filterPop(ArrayList<Commune> communes, int population) {
        X tmp = new X();
        for (Commune c : communes) {
            if (c.getPopulation() > population) {
                tmp.add(c);
            }
        }
        return tmp;
    }

    public static void writeCSV(U U) {
        try {
            FileWriter fw = new FileWriter(new File("src/data/DistancesCommunes.csv"));
            BufferedWriter bw = new BufferedWriter(fw);
            fw.write("Commune A;Commune B;Distance\n");
            for (Arete u : U) {
                bw.write(u.toString() + "\n");
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static U filterDist(U graphe, int distance) {
        U tmp = new U();
        for (Arete u : graphe) {
            if (u.getDistance() < distance) {
                tmp.add(u);
            }
        }
        return tmp;
    }

    public static void getCourtChemin(Commune depart, Commune arrivee, HashMap<Commune, Integer> lambda, HashMap<Commune, Commune> pere) {
        X chemin = new X();
        int cout = 0;
        Commune iterator = arrivee;
        while (iterator != depart) {
            chemin.add(iterator);
            cout += lambda.get(iterator);
            iterator = pere.get(iterator);
        }
        Collections.reverse(chemin);

        _chemin = chemin;
        _cout = cout;
    }

    public static X getChemin() {
        return _chemin;
    }

    public static int getCout() {
        return _cout;
    }
}
