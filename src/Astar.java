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
        X chemin = new X();
        ArrayList<CommPonder> x = new ArrayList<>(X.size());
        boolean destTrouve = false;

        //Initialisation du tableau avec les communes, l'heuristique et le cout
        for (int i = 0;i < X.size(); i++)
        {
            CommPonder commPonder = new CommPonder(X.get(i));
            x.add(commPonder);
            System.out.println("CommPonder : Ville= "+ commPonder.get_commune().getNom());
        }

        //on enfile la commune de départ
        openQueue.add(getCommPonder(x,depart));
        //System.out.println("openQueue contient :" + openQueue.get(0).get_commune().getNom());
        while (!openQueue.isEmpty() && !destTrouve)
        {
            //System.out.println("début du while");
            CommPonder com = openQueue.get(0);
            if(com.get_commune() == arrivee)
            {
                System.out.println("On a trouvé la ville: " + arrivee.getNom());
                System.out.println("Son père est " + com.get_pere().get_commune().getNom());
                _chemin = retrouveChemin(com);
                //fin du programme je sais pas comment gerer mais refait le chemin
                destTrouve= true;
                //_chemin.add(com.get_commune());
            }
            else
            {
                System.out.println("Ville pas encore trouvée...");
                ArrayList<Commune> voisins = U.getVoisins(com.get_commune());
                for (int i = 0; i < voisins.size(); i++)
                {
                    System.out.println(i+1 + " -voisin de " + com.get_commune().getNom() + " : " + voisins.get(i).getNom());

                    Commune v = voisins.get(i);
                    CommPonder voisin = getCommPonder(x, v);
                    if (!contient("closeQueue",closeQueue, voisin.get_commune())) {

                        //System.out.println("rentre dans le if1");
                        if (contient("openQueue", openQueue, voisin.get_commune()) && (voisin.get_cout() > openQueue.get(indexOfCommune(openQueue, voisin.get_commune())).get_cout())) {

                            //System.out.println("rentre dans le if2");
                            voisin.set_cout(com.get_cout() + Utils.distance(com.get_commune(), voisin.get_commune()));
                            voisin.set_heuristique(voisin.get_cout() + Utils.distance(com.get_commune(), arrivee));
                            voisin.set_pere(com);
                            voisin.affiche();
                            openQueue.set(indexOfCommune(openQueue, voisin.get_commune()),voisin);

                            //System.out.println("Commune de " + com.get_commune().getNom());
                            //System.out.println("Voisin " + voisin.get_commune().getNom() + "heuristique = " + voisin.get_heuristique() + "cout = " + voisin.get_cout());
                            //chemin.add(voisin.get_commune());
                            //changeChemin(chemin, com.get_commune(), voisin.get_commune());
                            //System.out.println("on est sorti de changeChemin");
                        }
                        else
                        {
                            //System.out.println("rentre dans le if2");
                            voisin.set_cout(com.get_cout() + Utils.distance(com.get_commune(), voisin.get_commune()));
                            voisin.set_heuristique(voisin.get_cout() + Utils.distance(com.get_commune(), arrivee));
                            voisin.set_pere(com);
                            voisin.affiche();
                            //System.out.println("On ajout voisin à openQueue");
                            ajoute(voisin, openQueue);
                        }
                    }
                    else
                    {
                        //System.out.println("closeQueue et openQueue ne contiennent pas cette ville");
                    }
                    //afficheQueue("OpenQueue",openQueue);
                    //afficheQueue("CloseQueue",closeQueue);
                }

                closeQueue.add(com);
                //System.out.println("la vile en haut de open queue est : " + openQueue.get(0).get_commune().getNom());
                openQueue.remove(openQueue.size()-1);
                //System.out.println("la vile en haut de open queue est : " + openQueue.get(0).get_commune().getNom());

            }
            //afficheQueue(openQueue);
        }


    }

    private static int indexOfCommune(ArrayList<CommPonder> list, Commune commune)
    {

        int i = 0;
        int resultat = 0;
        while (i < list.size())
        {
            if (list.get(i).get_commune().equals(commune))
            {
                return resultat;
            }
            i++;
        }
        return -1;


    }
    private static void ajoute(CommPonder voisin, ArrayList<CommPonder> list) {
        int i = 0;
        boolean placer = false;
        //System.out.println("On rentre dans ajoute ");
        while (i < list.size() && !placer)
        {
            //System.out.println("i : " + i);
            if (list.get(i).get_heuristique() <= voisin.get_heuristique())
            {
                //System.out.println("Ajouté à la " + i +"ème place");
                list.add(i,voisin);
                placer = true;
            }
            i++;
        }
    }


    public static boolean contient(String titreList, ArrayList<CommPonder> list,Commune commune)
    {
        //System.out.println("On verifie si la liste contient: " + commune.getNom());
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


        if (trouve == true)
            System.out.println("La liste " + titreList + " contient" + commune.getNom());
        else
            System.out.println("La liste " + titreList + " ne contient pas " + commune.getNom());



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
        System.out.println("\nOn retrouve chemin");
        X chemin = new X();
        while (commune.get_pere() != null)
        {
            System.out.println(commune.get_commune().getNom());
            chemin.add(commune.get_commune());
            commune=commune.get_pere();
        }
        return chemin;
        //System.out.println(" on a ajouté " + voisinActuel.getNom() + "à _chemin");
    }


    public static void afficheQueue(String titre ,ArrayList<CommPonder> list)
    {
        System.out.println("l'arraylist " + titre + " contient :");
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
