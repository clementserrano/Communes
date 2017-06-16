import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by clementserrano on 19/05/2017.
 */
public class Dijkstra {

    private static HashMap<Commune, Integer> _lambda;
    private static HashMap<Commune, Commune> _pere;

    public static void courtChemin(X X, U U, Commune s) {
        X Z = new X();
        HashMap<Commune, Integer> lambda = new HashMap<Commune, Integer>();
        HashMap<Commune, Commune> pere = new HashMap<Commune, Commune>();

        for (Commune c : X) {
            if (c != s) Z.add(c);
        }

        lambda.put(s, 0);

        for (Commune i : Z) {
            Arete usi = new Arete(s, i);
            if (U.contains(usi)) {
                lambda.put(i, U.get(usi).getDistance());
                pere.put(i, s);
            } else {
                lambda.put(i, Integer.MAX_VALUE);
            }
        }

        while (!Z.isEmpty()) {
            Commune x = null;
            int min = Integer.MAX_VALUE;
            for (Map.Entry<Commune, Integer> e : lambda.entrySet()) {
                if (e.getValue() < min && Z.contains(e.getKey())) {
                    x = e.getKey();
                    min = e.getValue();
                }
            }
            Z.remove(x);
            for (Commune i : U.getVoisins(x)) {
                if (Z.contains(i)) {
                    Arete usi = new Arete(x, i);
                    int dist = lambda.get(x) + U.get(usi).getDistance();
                    if (dist < lambda.get(i)) {
                        lambda.put(i, dist);
                        pere.put(i, x);
                    }
                }
            }
        }

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
