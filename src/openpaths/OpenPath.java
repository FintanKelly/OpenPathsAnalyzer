package openpaths;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 * Class that will hold all of the location data and contains some methods to
 * then analyze that data.
 *
 * @author Fintan Kelly
 */
public class OpenPath {

    /*
     Variables:
     - DateTime object that contains the entire date including hours, minutes, and seconds
     - String to represent what mobile device was used to capture the location data
     - Vector3 object that contains the longitude, latitude, and altitude values
     */
    private DateTime date;
    private String device;
    private Vector3 vectorPoint;

    /*
     Main Constructor:
     - Takes a Vector3, DateTime, and String to represent the location, date and time, and device
     */
    public OpenPath(Vector3 newLocation, DateTime newDate, String newDevice) {
        vectorPoint = newLocation;
        date = newDate;
        device = newDevice;
    }

    public String findDeviceDifference(String secondDevice) {
        return "Device 1: " + device + "\n" + "Device 2: " + secondDevice;
    }

    /*
     Physical Distance:
     - Finds the physical distance between two locations
     */
    public float findDistanceBetween(OpenPath secondLocation) {
        return vectorPoint.findDistance(secondLocation.getVectorPoint());
    }

    /*
     Time Distance:
     - Finds the complete time distance between two dates
     */
    public DateTime findTimeBetween(DateTime secondDate) {
        return new DateTime(findYearsBetween(secondDate), findMonthsBetween(secondDate), findDaysBetween(secondDate),
                findHoursBetween(secondDate), findMinutesBetween(secondDate), findSecondsBetween(secondDate), DateTimeZone.forID("-05:00"));
    }

    /*
     Years Between:
     - Finds the amount of years between two dates
     */
    public int findYearsBetween(DateTime secondDate) {
        return Math.abs(date.getYear() - secondDate.getYear());
    }

    /*
     Months Between:
     - Finds the amount of months out of the year between two dates
     */
    public int findMonthsBetween(DateTime secondDate) {
        return Math.abs(date.getMonthOfYear() - secondDate.getMonthOfYear());
    }

    /*
     Days Between:
     - Finds the amount of days out of the month between two dates
     */
    public int findDaysBetween(DateTime secondDate) {
        return Math.abs(date.getDayOfMonth() - secondDate.getDayOfMonth());
    }

    /*
     Hours Between:
     - Finds the amount of hours out of the day between two dates
     */
    public int findHoursBetween(DateTime secondDate) {
        return Math.abs(date.getHourOfDay() - secondDate.getHourOfDay());
    }

    /*
     Minutes Between:
     - Finds the amount of minutes out of the hour between two dates
     */
    public int findMinutesBetween(DateTime secondDate) {
        return Math.abs(date.getMinuteOfHour() - secondDate.getMinuteOfHour());
    }

    /*
     Seconds Between:
     - Finds the amount of seconds out of the minute between two dates
     */
    public int findSecondsBetween(DateTime secondDate) {
        return Math.abs(date.getSecondOfMinute() - secondDate.getSecondOfMinute());
    }

    /*
     String Representation:
     - Returns a String representing the Vector3 location, full date and time, and the device
     */
    @Override
    public String toString() {
        return vectorPoint.toString() + "Date: " + date.toString("yyyy/MM/dd HH:mm:ss") + "\n" + "Device: " + device + "\n";
    }

    /*
     Return Vector3:
     - Returns the Vector3 location
     */
    public Vector3 getVectorPoint() {
        return vectorPoint;
    }

    /*
     Return DateTime:
     - Returns the DateTime for the date and time
     */
    public DateTime getDate() {
        return date;
    }

    /*
     Return Device:
     - Returns the String of the device used
     */
    public String getDevice() {
        return device;
    }
}
