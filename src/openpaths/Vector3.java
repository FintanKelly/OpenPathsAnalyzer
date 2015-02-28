package openpaths;

/**
 * Class that will handle the longitude, latitude, and altitude data
 *
 * @author Fintan Kelly
 */
public class Vector3 {
    /*
     Variables:
     - Floats that represent the location data
     */

    private float latitude, longitude, altitude;

    /*
     Main Constructor:
     - Takes a new set of floats for the longitude, latitude, and altitude
     */
    public Vector3(float newLatitude, float newLongitude, float newAltitude) {
        latitude = newLatitude;
        longitude = newLongitude;
        altitude = newAltitude;
    }

    /*
     Find Distance:
     - Finds the distance between two sets of location data
     */
    public double findDistance(Vector3 secondVector) {
        Vector3 firstPoint = locationToPoint(new Vector3(latitude, longitude, altitude));
        Vector3 secondPoint = locationToPoint(new Vector3(secondVector.getLatitude(), secondVector.getLongitude(), secondVector.getAltitude()));

        return Math.sqrt(Math.pow(firstPoint.getLatitude() - secondPoint.getLatitude(), 2) + Math.pow(firstPoint.getLongitude() - secondPoint.getLongitude(), 2) + Math.pow(firstPoint.getAltitude() - secondPoint.getAltitude(), 2)) / 1000;
    }

    /*
     Lat/Lon/Alt to Point:
     - Converts a latitude, longitude, and altitude point to a x, y, and z point
     */
    public Vector3 locationToPoint(Vector3 location) {
        Vector3 newPoint;

        double radius = location.getAltitude() + getEarthRadius(Math.toRadians(location.getLatitude()));
        double cosLat = Math.cos(Math.toRadians(location.getLatitude()));
        double sinLat = Math.sin(Math.toRadians(location.getLatitude()));
        double cosLon = Math.cos(Math.toRadians(location.getLongitude()));
        double sinLon = Math.sin(Math.toRadians(location.getLongitude()));
        double x = cosLon * cosLat * radius;
        double y = sinLon * cosLat * radius;
        double z = sinLat * radius;

        newPoint = new Vector3((float) x, (float) y, (float) z);

        return newPoint;
    }

    /*
     Find Earth Radius:
     - Finds the earth's radius
     */
    public double getEarthRadius(double latitudeR) {
        double a = 6378137.0;
        double b = 6356752.3;
        double t1 = a * a * Math.cos(latitudeR);
        double t2 = b * b * Math.sin(latitudeR);
        double t3 = a * Math.cos(latitudeR);
        double t4 = b * Math.sin(latitudeR);

        return Math.sqrt((t1 * t1 + t2 * t2) / (t3 * t3 + t4 * t4));
    }

    /*
     Return Longitude:
     - Returns the float that represents the longitude
     */
    public float getLongitude() {
        return longitude;
    }

    /*
     Return Latitude:
     - Returns the float that represents the latitude
     */
    public float getLatitude() {
        return latitude;
    }

    /*
     Return Altitude:
     - Returns the float that represents the altitude
     */
    public float getAltitude() {
        return altitude;
    }

    /*
     String Representation:
     - Returns a String that shows the longitude, latitude, and altitude
     */
    @Override
    public String toString() {
        return "Longitude: " + longitude + "\n" + "Latitude: " + latitude + "\n" + "Altitude: " + altitude + "\n";
    }
}
