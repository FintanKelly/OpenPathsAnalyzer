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
        int earthRadius = 6571;
        
        float latitudeDistance = (float) Math.toRadians(latitude - secondVector.getLatitude());
        float longitudeDistance = (float) Math.toRadians(longitude - secondVector.getLongitude());
        float distance = (float) (Math.pow(Math.sin(latitudeDistance / 2), 2) + 
                            (Math.cos(latitude) * Math.cos(secondVector.getLatitude())) * 
                            Math.pow((Math.sin(longitudeDistance / 2)), 2));
        distance = (float) (earthRadius * (2 * Math.atan2(Math.sqrt(distance), Math.sqrt(1 - distance))));
        distance = (float) (Math.pow(distance, 2) + Math.pow((altitude - secondVector.getAltitude()), 2));
        return (float) Math.sqrt(distance);
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
