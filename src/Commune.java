/**
 * Created by serra on 19/05/2017.
 */
public class Commune {
    private String id;
    private String commune;
    private double longitude;
    private double latitude;

    public Commune(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
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
