import java.util.ArrayList;

/**
 * Created by clementserrano on 16/06/2017.
 */
public class X extends ArrayList<Commune> {

    // Le cityID sert d'identifiant dans la liste des sommets
    public Commune get(String cityId) {
        for (Commune c : this) {
            if (c.getId().equals(cityId)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String res = "";
        for (Commune c : this) {
            res += c.getId() + "\n";
        }
        return res;
    }
}
