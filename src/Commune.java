/**
 * Created by serra on 19/05/2017.
 */
public class Commune {
    private String id;
    private String commune;
    private double longitude;
    private double latitude;
    private int population;
    
    public Commune(String id, String commune, int population, double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
        this.id = id;
        this.commune = commune;
        this.population = population;
    }

    public int getPopulation() {
        return population;
    }

    public String getId() {
        return id;
    }

    public String getCommune() {
        return commune;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }
}
