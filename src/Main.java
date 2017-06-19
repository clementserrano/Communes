import java.io.IOException;
import java.util.Scanner;

public class Main {

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

        // DIJKSTRA avec SkipList
        // Calcul les plus court chemins entre le départ et tous les sommets du graphe
        Dijkstra.courtCheminSkipList(X, U, depart);
        // Retourne le plus court chemin entre le départ et l'arrivé
        Utils.getCourtChemin(U, depart, arrivee, Dijkstra.getLambda(), Dijkstra.getPere());

        X chemin = Utils.getChemin();
        int cout = Utils.getCout();

        // Affichage du plus court chemin
        for (int i = 0; i < chemin.size(); i++) {
            System.out.print(chemin.get(i).getNom());
            if (i < chemin.size() - 1) {
                System.out.print(" -> ");
            }
        }

        System.out.println("\nDistance : " + cout);

        /*Astar astar = new Astar();
        System.out.println("Ville départ = " + depart.getNom() + " Ville arrivée = " + arrivee.getNom());
        astar.courtChemin(X,U,depart,arrivee);

        X cheminAStar = astar.getChemin();
        for (int i = 0;i < cheminAStar.size(); i++)
        {
            System.out.println(cheminAStar.get(i).getNom());
        }*/
    }
}
