import java.util.HashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by clementserrano on 19/05/2017.
 */
public class Dijkstra {

    // Tableau des distance par rapport au départ
    private static HashMap<Commune, Integer> _lambda;

    // Tableau des prédecesseurs permettant de reconstruire le plus court chemin
    private static HashMap<Commune, Commune> _pere;

    public static void courtChemin(X X, U U, Commune s) {
        // Ensemble des sommets non validé
        X Z = new X();

        // Tableaux temporaires (on les attribue aux variables statiques à la fin de l'opération)
        HashMap<Commune, Integer> lambda = new HashMap<>();
        HashMap<Commune, Commune> pere = new HashMap<>();

        // SkipList, pour des soucis techniques on stock les distances par rapport au départ dans les communes
        ConcurrentSkipListSet<Commune> skiplist = new ConcurrentSkipListSet<>((o1, o2) -> {
            if (o1.getDistance() > o2.getDistance()) {
                return 1;
            } else {
                return -1;
            }
        });

        // On ajoute toutes les communes dans Z sauf le départ
        for (Commune c : X) {
            if (c != s) Z.add(c);
        }

        // On ajoute s dans la skiplist et on le valide en le mettant dans lambda
        s.setDistance(0);
        skiplist.add(s);
        lambda.put(s, 0);

        // Pour tous les sommets non validés
        for (Commune i : Z) {
            // On construit une arête temporaire pour la retrouver dans U
            Arete usi = new Arete(s, i);
            if (U.contains(usi)) { // Pour tous les voisins de s
                // On ajoute la valeur de l'arête à la commune et la commune à la skiplist
                i.setDistance(U.get(usi).getDistance());
                skiplist.add(i);
                // Ainsi que le prédecesseur qui est le départ
                pere.put(i, s);
            } else {
                // Sinon on met une valeur "infinie"
                i.setDistance(Integer.MAX_VALUE);
                skiplist.add(i);
            }
        }

        // Tant que tous les sommets ne sont pas validés
        while (!Z.isEmpty()) {
            /* On prend le premier sommet de la skiplist
            (la skiplist ne contient que des sommets validés,
            elle est triée donc le premier élément est le minimum) */
            Commune x = skiplist.pollFirst();
            // On valide le sommet
            Z.remove(x);
            lambda.put(x, x.getDistance());
            // Pour tous les voisins de x
            for (Commune i : U.getVoisins(x)) {
                // S'il n'est pas validé
                if (Z.contains(i)) {
                    // On met à jour sa distance si la distance par rapport à x est plus petite
                    Arete usi = new Arete(x, i);
                    int dist = x.getDistance() + U.get(usi).getDistance();
                    if (dist < i.getDistance()) {
                        i.setDistance(dist);
                        skiplist.add(i);
                        pere.put(i, x);
                    }
                }
            }
        }

        // On attribue les variables temporaires aux variable que le Main récupère
        _lambda = lambda;
        _pere = pere;
    }

    public static HashMap<Commune, Integer> getLambda() {
        return _lambda;
    }

    public static HashMap<Commune, Commune> getPere() {
        return _pere;
    }
}
