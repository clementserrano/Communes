import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by clementserrano on 19/05/2017.
 */
public class Astar {


    private static X _chemin;
    public static void courtChemin(X X, U U, Commune depart, Commune arrivee) {
        ArrayList<CommPonder> openQueue = new ArrayList<>();
        ArrayList<CommPonder> closeQueue = new ArrayList<>();
        ArrayList<CommPonder> x = new ArrayList<>(X.size());
        boolean destTrouve = false;

        //Initialisation du tableau avec les communes, l'heuristique et le cout
        for (int i = 0;i < X.size(); i++)
        {
            CommPonder commPonder = new CommPonder(X.get(i));
            x.add(commPonder);
        }

        //on initialise les attribut de la commPonder du "depart"
        CommPonder commPonderDepart=getCommPonder(x,depart);
        commPonderDepart.set_cout(0);
        commPonderDepart.set_heuristique(Utils.distance(commPonderDepart.get_commune(), arrivee)); // heuristique = distance entre commune actuelle et commune arrivee à vol d'opiseau

        //on enfile la commune de départ
        openQueue.add(commPonderDepart);

        //tant que la liste ouverte( liste des commets a parcourir n'est pas vide et qu'on a pas trouve la destination
        while (!openQueue.isEmpty() && !destTrouve)
        {
            //on depile
            CommPonder com = openQueue.get(0);

            //si on arrive a destination
            if (com.get_commune() == arrivee)
            {
                _chemin = retrouveChemin(com);
                destTrouve = true;
            }
            else //sinon
            {
                //on recupere tout les voisins de la commune prise dans la liste ouverte
                ArrayList<Commune> voisins = U.getVoisins(com.get_commune());

                // parcours des voisins un a un
                for (int i = 0; i < voisins.size(); i++) {

                    //creation et initialisation du voisin pondere
                    Commune v = voisins.get(i);
                    CommPonder voisin = getCommPonder(x, v);

                    voisin.set_cout(com.get_cout() + Utils.distance(com.get_commune(), voisin.get_commune()));
                    voisin.set_heuristique(voisin.get_cout() + Utils.distance(voisin.get_commune(), arrivee));
                    voisin.set_pere(com);

                    //si la ville n'a jamais ete archive dans la liste fermee
                    if (!contient(closeQueue, voisin.get_commune()))
                    {
                        // si le voisin a deja ete ajoute a la liste par un autre pere et que son cout est superieur a notre chaine actuelle
                        if (contient(openQueue, voisin.get_commune())
                                && (voisin.get_cout() < openQueue.get(indexOfCommune(openQueue, voisin.get_commune())).get_cout())) {

                            //on modifie la communeponderee dans la file ouverte
                            openQueue.set(indexOfCommune(openQueue, voisin.get_commune()), voisin);

                        }
                        else // si elle n'a jamais ete regardee
                        {
                            ajoute(voisin, openQueue);
                        }
                    }
                }
                closeQueue.add(com);

                openQueue.remove(indexOfCommune(openQueue, com.get_commune()));
            }
        }
    }

    private static int indexOfCommune(ArrayList<CommPonder> list, Commune commune)
    {

        int i = 0;
        while (i < list.size())
        {
            if (list.get(i).get_commune().equals(commune))
            {

                return i;
            }
            i++;
        }
        return -1;


    }
    private static void ajoute(CommPonder voisin, ArrayList<CommPonder> list) {
        int i = 0;
        boolean placer = false;

        while (i < list.size() && !placer)
        {


            if (list.get(i).get_heuristique() >= voisin.get_heuristique())
            {
                placer = true;
            }
            i++;
        }

        list.add(i,voisin);
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

    public static X retrouveChemin(CommPonder commune)
    {
        X chemin = new X();
        CommPonder tmp = commune;
        chemin.add(commune.get_commune());

        while (!(tmp.get_pere()== null))
        {
            chemin.add(tmp.get_commune());
            tmp.affiche();
            tmp = tmp.get_pere();

        }
        return chemin;

    }


    public static void afficheQueue(String titre ,ArrayList<CommPonder> list)
    {
        for (int i = 0; i < list.size(); i++)
        {
            list.get(i).affiche();
        }
    }

    public static X getChemin()
    {
        return _chemin;
    }
}
