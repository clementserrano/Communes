/**
 * Created by serra on 19/05/2017.
 */
public class Commune {
    private String id;
    private String nom;
    private double longitude;
    private double latitude;
    private int population;

    private int distance; // pour la skiplist dijkstra

    public Commune(String id, String nom, int population, double longitude, double latitude) {
        this.id = id;
        this.nom = nom;
        this.population = population;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getPopulation() {
        return population;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
