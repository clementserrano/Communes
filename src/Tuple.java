/**
 * Created by clementserrano on 19/05/2017.
 */
public class Tuple {
    private Commune A;
    private Commune B;
    private double distance;

    public Tuple(Commune A, Commune B) {
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

    public double getDistance() {
        return distance;
    }

    @Override
    public boolean equals(Object obj) {
        Tuple com = (Tuple) obj;
        if ((A == com.getA() && B == com.getB()) || A == com.getB() && B == com.getA()) return true;
        return false;
    }

    @Override
    public String toString() {
        return A.getNom() + " - " + B.getNom() + " : " + distance;
    }
}
