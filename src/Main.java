import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Commune> communes = null;
        try {
            communes = Utils.readCSV("src/data/CommunesFrance.csv");
        }catch(IOException e){
            e.printStackTrace();
        }

        communes = Utils.filterPop(communes,20000);
        System.out.println(communes.size());

        Graphe graphe = Utils.buildGraphe(communes);

        for(Tuple t : graphe){
            System.out.println(t);
        }

    }
}
