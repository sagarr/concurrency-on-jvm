package completableFuture;

/**
 * @author Sagar Rohankar
 */
public class Location {

    private final double lon;
    private final double lat;

    public Location(final double lon, final double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

}
