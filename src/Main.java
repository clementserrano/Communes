import java.io.IOException;
import java.util.Scanner;

public class Main {  // ASTAR

    public static void main(String[] args) {
        // Récupère les communes à partir du fichier CSV
        X X = null;
        try {
            X = Utils.readCSV("src/data/CommunesFrance.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Filtre les communes de population supérieure à 50 000
        X = Utils.filterPop(X, 50000);
        // Filtre les DOMTOM
        X = Utils.filterDOMTOM(X);
        System.out.println(X.size() + " communes");

        // Construit le graphe complet
        U U = Utils.buildGraphe(X);
        // Filtre les arêtes de distance inférieure à 300
        U = Utils.filterDist(U, 300);
        System.out.println("Graphe construit !\n");
        //Utils.writeCSV(U);

        // Récupère les villes de départ et d'arrivée à partir de leur id entré
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

        // ASTAR
        Astar astar = new Astar();
        System.out.println("Ville départ = " + depart.getNom() + " Ville arrivée = " + arrivee.getNom());
        astar.courtChemin(X,U,depart,arrivee);

        X cheminAStar = astar.getChemin();
        for (int i = 0;i < cheminAStar.size(); i++)
        {
            System.out.println(cheminAStar.get(i).getNom());
        }

    }
}
