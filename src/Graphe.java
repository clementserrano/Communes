import java.util.ArrayList;

/**
 * Created by serra on 19/05/2017.
 */
public class Graphe extends ArrayList<Tuple> {
    @Override
    public boolean contains(Object obj) {
        Tuple t = (Tuple) obj;
        for (Tuple tuple : this) {
            if (t.equals(tuple)) return true;
        }
        return false;
    }
}
