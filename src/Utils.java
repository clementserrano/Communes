import static java.lang.Math.*;

/**
 * Created by serra on 19/05/2017.
 */
public class Utils {

    public static double distance(double longitudeA, double latitudeA, double longitudeB, double latitudeB){
        return 60*acos(sin(latitudeA)*sin(latitudeB)+cos(latitudeA)*cos(latitudeB)*cos(longitudeB-longitudeA));
    }

    public static void readCSV(){
        
    }
}
