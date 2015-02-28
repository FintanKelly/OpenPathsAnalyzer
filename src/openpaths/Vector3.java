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

    private float longitude, latitude, altitude;

    /*
     Main Constructor:
     - Takes a new set of floats for the longitude, latitude, and altitude
     */
    public Vector3(float newLongitude, float newLatitude, float newAltitude) {
        longitude = newLongitude;
        latitude = newLatitude;
        altitude = newAltitude;
    }

    /*
     Find Distance:
     - Finds the distance between two sets of location data
     */
    public float findDistance(Vector3 secondVector) {
        return (float) Math.abs((Math.pow((Math.pow(latitude - secondVector.getLatitude(), 2) + Math.pow(longitude - secondVector.getLongitude(), 2) + Math.pow(altitude - secondVector.getAltitude(), 2)), 0.5)));
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
