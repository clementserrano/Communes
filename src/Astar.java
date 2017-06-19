import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by clementserrano on 19/05/2017.
 */
public class Astar {


    /*URL

    https://fr.wikipedia.org/wiki/Algorithme_A*
    http://web.emn.fr/x-info/pdavid/Enseignement/IA/poly-ia/resolution/a-etoile.html
    http://khayyam.developpez.com/articles/algo/astar/
    http://www.redblobgames.com/pathfinding/a-star/introduction.html

     */

    private static X _chemin;
    public static void courtChemin(X X, U U, Commune depart, Commune arrivee) {
        ArrayList<CommPonder> openQueue = new ArrayList<>();
        ArrayList<CommPonder> closeQueue = new ArrayList<>();

        ArrayList<CommPonder> x = new ArrayList<>(X.size());
        boolean destTrouve = false;

        //Initialisation du tableau avec les communes, l'heuristique et le cout
        for (int i = 0;i < x.size(); i++)
        {
            CommPonder commPonder = new CommPonder(X.get(i));
            x.add(commPonder);
        }

        //on enfile la commune de départ
        openQueue.add(getCommPonder(x,depart));

        while (!openQueue.isEmpty() && !destTrouve)
        {
            CommPonder com = openQueue.get(0);
            if(com.get_commune() == arrivee)
            {
                //fin du programme je sais pas comment gerer mais refait le chemin
                destTrouve= true;
                _chemin.add(com.get_commune());
            }
            else
            {
                ArrayList<Commune> voisins = U.getVoisins(com.get_commune());
                for (int i = 0; i < voisins.size(); i++) {
                    Commune v = voisins.get(i);
                    CommPonder voisin = getCommPonder(x, v);
                    CommPonder voisinOpenQueue = openQueue.get(openQueue.indexOf(voisin));
                    if ((!contient(closeQueue, voisin.get_commune()))
                            ||
                            (!contient(openQueue, voisin.get_commune()) && (voisin.get_cout() > voisinOpenQueue.get_cout()))
                            ) {

                        voisin.set_cout(com.get_cout() + Utils.distance(com.get_commune(), voisin.get_commune()));
                        voisin.set_heuristique(voisin.get_cout() + Utils.distance(com.get_commune(), arrivee));

                        ajoute(voisin,openQueue);
                        changeChemin(com.get_commune(),voisin.get_commune());
                    }

                }

                closeQueue.add(com);
                openQueue.remove(0);
            }

        }


    }

    private static void ajoute(CommPonder voisin, ArrayList<CommPonder> list) {
        int i = 0;
        boolean placer = false;
        while (i < list.size() && !placer)
        {
            if (list.get(i).get_heuristique() < voisin.get_heuristique())
            {
                list.add(i,voisin);
                placer = true;
            }
            i++;
        }
    }


    public static boolean contient(ArrayList<CommPonder> list,Commune commune)
    {
        int i = 0;
        boolean trouve = false;
        while ( i < list.size() && !trouve )
        {
            if (list.get(i).get_commune().equals(commune))
            {
                trouve = true;
            }
            i ++;
        }
        return trouve;
    }

    public static CommPonder getCommPonder(ArrayList<CommPonder> list,Commune commune)
    {
        int i = 0;
        while ( i < list.size() )
        {
            if (list.get(i).get_commune().equals(commune))
            {

                return list.get(i);
            }
            i ++;
        }
        return null;

    }

    public static void changeChemin(Commune communeActuelle, Commune voisinActuel)
    {
        boolean comActTrouve = false;
        for (int i = 0; i < _chemin.size(); i++)
        {
            if (comActTrouve)
            {
                _chemin.remove(i);
            }
            if (communeActuelle == _chemin.get(i))
            {
                comActTrouve = true;
                _chemin.remove(i+1);
                _chemin.add(i+1,voisinActuel);
            }

        }

    }




    public static X getChemin()
    {
        return _chemin;
    }
}
