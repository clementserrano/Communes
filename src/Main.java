import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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

        communes = Utils.filterPop(communes, 20000);
        System.out.println(communes.size());

        Graphe graphe = Utils.buildGraphe(communes);

        try {
            FileWriter fw = new FileWriter(new File("src/data/DistancesCommunes.txt"));
            BufferedWriter bw = new BufferedWriter(fw);
            for (Tuple t : graphe) {
                bw.write(t.toString()+"\n");
            }
            bw.close();
            fw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
