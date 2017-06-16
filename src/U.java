import java.util.ArrayList;

/**
 * Created by clementserrano on 19/05/2017.
 */
public class U extends ArrayList<Arete> {

    @Override
    public boolean contains(Object obj) {
        Arete u = (Arete) obj;
        for (Arete arete : this) {
            if (u.equals(arete)) return true;
        }
        return false;
    }

    public Arete get(Arete usi) {
        for (Arete u : this) {
            if (u.equals(usi)) {
                return u;
            }
        }
        return null;
    }

    public ArrayList<Commune> getVoisins(Commune x) {
        ArrayList<Commune> voisins = new ArrayList<>();
        for (Arete u : this) {
            if (u.getA() == x) voisins.add(u.getB());
            else if (u.getB() == x) voisins.add(u.getA());
        }
        return voisins;
    }
}
