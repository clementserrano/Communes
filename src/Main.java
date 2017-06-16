import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        X X = null;
        try {
            X = Utils.readCSV("src/data/CommunesFrance.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        X = Utils.filterPop(X, 50000);
        System.out.println(X.size());

        U U = Utils.buildGraphe(X);
        U = Utils.filterDist(U, 100);

        // Utils.writeCSV(U);
        
        Scanner reader = new Scanner(System.in);

        Commune depart;
        do {
            System.out.println("Entrer la ville de départ : ");
            depart = X.get(reader.next());
        } while (depart != null);

        Commune arrivee;
        do {
            System.out.println("Entrer la ville d'arrivée : ");
            arrivee = X.get(reader.next());
        } while (arrivee != null);

        Dijkstra.courtChemin(X, U, depart);
        Utils.getCourtChemin(depart, arrivee, Dijkstra.getLambda(), Dijkstra.getPere());

        X chemin = Utils.getChemin();
        int cout = Utils.getCout();

        for (Commune c : chemin) {
            System.out.print(c.getNom() + " -> ");
        }
        System.out.println("\nDistance : " + cout);
    }
}
