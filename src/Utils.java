import java.io.*;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by clementserrano on 19/05/2017.
 */
public class Utils {

    // Contient une liste de sommets ordonnés sur le plus court chemin
    private static X _chemin;
    // Cout du plus court chemin
    private static int _cout;

    // Calcul la distance entre 2 communes avec leur coordonnées GPS
    public static int distance(Commune communeA, Commune communeB) {
        double a = Math.PI / 180;
        double lat1 = communeA.getLatitude() * a;
        double lat2 = communeB.getLatitude() * a;
        double lon1 = communeA.getLongitude() * a;
        double lon2 = communeB.getLongitude() * a;

        double t1 = Math.sin(lat1) * Math.sin(lat2);
        double t2 = Math.cos(lat1) * Math.cos(lat2);
        double t3 = Math.cos(lon1 - lon2);
        double t4 = t2 * t3;
        double t5 = t1 + t4;
        double rad_dist = Math.atan(-t5 / Math.sqrt(-t5 * t5 + 1)) + 2 * Math.atan(1);

        return (int) ((rad_dist * 3437.74677 * 1.1508) * 1.6093470878864446);
    }

    // Lit le fichier CSV et retourne une liste des communes
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

    // Construit le graphe complet à partir des sommets fournis
    public static U buildGraphe(X X) {
        U U = new U();
        for (int i = 0; i < X.size(); i++) {
            for (int j = 0; j < X.size(); j++) {
                if (X.get(i) != X.get(j)) {
                    Arete temp = new Arete(X.get(i), X.get(j));
                    if (!U.contains(temp)) {
                        U.add(temp);
                    }
                }
            }
        }
        return U;
    }

    // Filtre la population en fonction du paramètre fourni
    public static X filterPop(X X, int population) {
        X tmp = new X();
        for (Commune c : X) {
            if (c.getPopulation() > population) {
                tmp.add(c);
            }
        }
        return tmp;
    }

    // Filtre les DOMTOM garantissant la presque connexité du graphe
    public static X filterDOMTOM(X X) {
        X tmp = new X();
        Commune paris = X.get("paris");
        for (Commune c : X) {
            if (distance(paris, c) < 1200) { // On ne prend pas les DOMTOM
                tmp.add(c);
            }
        }
        return tmp;
    }

    // Filtre les distance supérieur à la distance fournie
    public static U filterDist(U U, int distance) {
        U tmp = new U();
        for (Arete u : U) {
            if (u.getDistance() < distance) {
                tmp.add(u);
            }
        }
        return tmp;
    }

    // Détermine le plus court chemin d'une commune A à une commune B à partir des résultats de l'algo de Dijkstra
    public static void getCourtChemin(U U, Commune depart, Commune arrivee, HashMap<Commune, Integer> lambda, HashMap<Commune, Commune> pere) {
        X chemin = new X();
        int cout = 0;
        Commune iterator = arrivee;
        Commune iteratorPrec = null;
        while (iterator != depart) {
            chemin.add(iterator);
            if (iteratorPrec != null) cout += U.get(new Arete(iteratorPrec, iterator, false)).getDistance();
            iteratorPrec = iterator;
            iterator = pere.get(iterator);
        }
        chemin.add(iterator);
        cout += U.get(new Arete(iteratorPrec, iterator,false)).getDistance();
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
}
