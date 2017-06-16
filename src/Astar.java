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

    ArrayList<Commune> _chemin;
    public static void courtChemin(X X, U U, Commune depart, Commune arrivee) {
        ArrayList<Commune> openQueue = new ArrayList<>();
        ArrayList<Commune> closeQueue = new ArrayList<>();

        ArrayList<CommPonder> x = new ArrayList<>(X.size());
        Commune c;

        //Initialisation du tableau avec les communes, l'heuristique et le cout
        for (int i = 0;i < x.size(); i++)
        {
            CommPonder commPonder = new CommPonder(X.get(i));
            x.add(commPonder);
        }

        //on enfile la commune de départ
        openQueue.add(depart);

        while (!openQueue.isEmpty())
        {
            Commune com = openQueue.get(0);
            if(com == arrivee)
            {
                //fin du programme je sais pas comment gerer
            }


            ArrayList<Commune> voisins = U.getVoisins(com);
            for (int i =0; i < voisins.size(); i++)
            {
                Commune v = voisins.get(i);
                CommPonder voisin = getCommPonder(x,v);
                if (!openQueue.contains(voisin) /* && !( voisin._cout < voisin._cout de openQueue )) ou (( v existe dans openList && avec un cout inférieur )*/)
                {
                    /*
                    v.cout = u.cout +1
                    v.heuristique = v.cout + distance([v.x, v.y], [objectif.x, objectif.y])
                    */
                    openQueue.add((Commune) v);
                }

            }

            closeQueue.add(com);
        }
        //terminer le programme (avec erreur)


    }

    public static double  heuristique(Commune a, Commune b,U U)
    {
        Arete arete = new Arete(a,b);
        return U.get(arete).getDistance()/100;
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

}
