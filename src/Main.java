import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        try {
            ArrayList<Commune> communes = Utils.readCSV("data/CommunesFrance.csv");
        }catch(IOException e){
            e.printStackTrace();
        }


    }
}
