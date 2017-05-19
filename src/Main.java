import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Commune> communes = null;
        try {
            communes = Utils.readCSV("src/data/CommunesFrance.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        communes = Utils.filterPop(communes, 50000);
        System.out.println(communes.size());

        Graphe graphe = Utils.buildGraphe(communes);
        graphe = Utils.filterDist(graphe,100);

        Utils.writeCSV(graphe);
    }
}
