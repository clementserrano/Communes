import java.util.ArrayList;

/**
 * Created by tardy on 16/06/2017.
 */
public class CommPonder {
    private Commune _commune;
    private double _heuristique;
    private int _cout;

    public CommPonder(Commune commune)
    {
        _commune = commune;
        _heuristique = 0;
        _cout = 0;
    }

    public Commune get_commune() {
        return _commune;
    }

    public void set_commune(Commune _commune) {
        this._commune = _commune;
    }

    public double get_heuristique() {
        return _heuristique;
    }

    public void set_heuristique(double _heuristique) {
        this._heuristique = _heuristique;
    }

    public int get_cout() {
        return _cout;
    }

    public void set_cout(int _cout) {
        this._cout = _cout;
    }
}
