import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {

    public static void main(String[] args) {
        X X = null;
        try {
            X = Utils.readCSV("src/data/CommunesFrance.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        X = Utils.filterPop(X, 50000);
        X = Utils.filterDOMTOM(X);
        System.out.println(X.size());

        U U = Utils.buildGraphe(X);
        U = Utils.filterDist(U, 300);
        //Utils.writeCSV(U);

        Scanner reader = new Scanner(System.in);

        Commune depart;
        do {
            System.out.println("Entrer la ville de départ : ");
            depart = X.get(reader.next());
        } while (depart == null);

        Commune arrivee;
        do {
            System.out.println("Entrer la ville d'arrivée : ");
            arrivee = X.get(reader.next());
        } while (arrivee == null);

        Dijkstra.courtChemin(X, U, depart);

        for (Map.Entry<Commune, Integer> e : Dijkstra.getLambda().entrySet()) {
            System.out.println(e.getKey().getId() + " -> " + e.getValue());
        }

        System.out.println("");

        for (Map.Entry<Commune, Commune> e : Dijkstra.getPere().entrySet()) {
            System.out.println(e.getKey().getId() + " -> " + e.getValue().getId());
        }

        Utils.getCourtChemin(depart, arrivee, Dijkstra.getLambda(), Dijkstra.getPere());

        X chemin = Utils.getChemin();
        int cout = Utils.getCout();

        for (int i = 0; i < chemin.size(); i++) {
            System.out.print(chemin.get(i).getNom());
            if (i < chemin.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println("\nDistance : " + cout);
    }
}
