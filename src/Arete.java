/**
 * Created by clementserrano on 19/05/2017.
 */
public class Arete {

    private Commune A;
    private Commune B;
    private int distance;

    public Arete(Commune A, Commune B) {
        this.A = A;
        this.B = B;
        this.distance = Utils.distance(A, B);
    }

    public Commune getA() {
        return A;
    }

    public Commune getB() {
        return B;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public boolean equals(Object obj) {
        Arete u = (Arete) obj;
        if ((A == u.getA() && B == u.getB()) || A == u.getB() && B == u.getA()) return true;
        return false;
    }

    @Override
    public String toString() {
        return A.getNom() + ";" + B.getNom() + ";" + distance;
    }
}
