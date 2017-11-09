package Location;

/**
 * Created by gshestakov on 11/9/2017.
 */

public class LocationObject {
    public LocationObject(double lat, double lng) {
        Lat = lat;
        Lng = lng;
    }

    public double getLat() {
        return Lat;
    }

    public void setLat(double lat) {
        Lat = lat;
    }

    public double getLng() {
        return Lng;
    }

    public void setLng(double lng) {
        Lng = lng;
    }

    private double Lat;
    private double Lng;
}
