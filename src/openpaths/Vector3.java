package openpaths;

/**
 *
 * @author Fintan Kelly
 */
public class Vector3 {
    private float longitude, latitude, altitude;
    
    public Vector3(float newLongitude, float newLatitude, float newAltitude) {
        longitude = newLongitude;
        latitude = newLatitude;
        altitude = newAltitude;
    }
    
    public float findDistance(Vector3 secondVector) {
        return (float)Math.abs((Math.pow((Math.pow(latitude - secondVector.getLatitude(), 2) + Math.pow(longitude - secondVector.getLongitude(), 2) + Math.pow(altitude - secondVector.getAltitude(), 2)), 0.5)));
    }
    
    public float getLongitude() {
        return longitude;
    }
    
    public float getLatitude() {
        return latitude;
    }
    
    public float getAltitude() {
        return altitude;
    }
    
    @Override
    public String toString() {
        return "Longitude: " + longitude + "\n" + "Latitude: " + latitude + "\n" + "Altitude: " + altitude + "\n";
    }
}
