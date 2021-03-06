package openpaths;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 * Import and read the CSV file that contains all of the location data that will
 * be visualized and analyzed.
 *
 * @author Fintan Kelly
 */
public class CSVReader {

    /*
     Read CSV:
     - If the csv file exists, read it in through the Buffered and File Reader
     - While there is still data to read, break it down and create an OpenPaths object
     - Add that object to an ArrayList, where they can be easily accessed
     - Temporary print statements to show some possible methods of analyzing the data
     */
    public void readCSVData() {
        String csvToParse = "openpaths_Harmtouch.csv";
        BufferedReader csvReader = null;
        String line;
        final ArrayList<OpenPath> locations = new ArrayList<>();

        try {
            csvReader = new BufferedReader(new FileReader(csvToParse));

            while ((line = csvReader.readLine()) != null) {
                if (line.contains("lat") || (!locations.isEmpty() && locations.get(locations.size() - 1).getVectorPoint().getLatitude() == Float.parseFloat(line.substring(0, line.indexOf(","))))) {
                } else {
                    String[] attributes = line.split(",");

                    Vector3 location = new Vector3(Float.parseFloat(attributes[0]), Float.parseFloat(attributes[1]), Float.parseFloat(attributes[2]));

                    int year = Integer.parseInt((attributes[3].substring(0, 4)));
                    int month = Integer.parseInt(attributes[3].substring(5, 7));
                    int day = Integer.parseInt(attributes[3].substring(8, 10));
                    int hour = Integer.parseInt(attributes[3].substring(11, 13));
                    int minute = Integer.parseInt(attributes[3].substring(14, 16));
                    int second = Integer.parseInt(attributes[3].substring(17, 19));
                    DateTime newDate = new DateTime(year, month, day, hour, minute, second, DateTimeZone.forID("-05:00"));

                    locations.add(new OpenPath(location, newDate, attributes[4].substring(1)));
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                csvReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        final Analyzer analyzer = new Analyzer(locations);

        System.out.println(analyzer.findDataEntry(0));
        System.out.println(analyzer.findDataEntry(1));

        System.out.println("Time between: " + analyzer.findDataEntry(0).getDate().toString("yyyy/MM/dd HH:mm:ss") + " and " + analyzer.findDataEntry(analyzer.getDataList().size() - 1).getDate().toString("yyyy/MM/dd HH:mm:ss") + "\n"
                + analyzer.findDataEntry(0).findTimeBetween(analyzer.findDataEntry(analyzer.getDataList().size() - 1).getDate()).toString("yyyy/MM/dd HH:mm:ss") + "\n");

        System.out.println("Distance between: " + analyzer.findDataEntry(0).getDate().toString("yyyy/MM/dd HH:mm:ss") + " and " + analyzer.findDataEntry(analyzer.getDataList().size() - 1).getDate().toString("yyyy/MM/dd HH:mm:ss") + "\n"
                + analyzer.findDataEntry(0).findDistanceBetween(analyzer.findDataEntry(analyzer.getDataList().size() - 1)) + "\n");

        System.out.println("Devices used: \n" + analyzer.findDataEntry(0).findDeviceDifference(analyzer.findDataEntry(0).getDevice()) + "\n");

        ArrayList<OpenPath> firstDay = new ArrayList<OpenPath>() {
            {
                int i = 0;
                while (analyzer.findDataEntry(i).findDaysBetween(analyzer.findDataEntry(i + 1).getDate()) == 0) {
                    add(locations.get(i));
                    i++;
                }
            }
        };

        System.out.println("Total Distance Traveled during " + firstDay.get(0).getDate().toString("EEE MMM yyyy") + ": " + analyzer.findTotalDistanceTraveled(analyzer.findDataEntry(0).getDate().toString("yyyy/MM/dd"), "day"));
        System.out.println("Total Distance Traveled during " + firstDay.get(0).getDate().toString("MMM yyyy") + ": " + analyzer.findTotalDistanceTraveled(analyzer.findDataEntry(0).getDate().toString("yyyy/MM"), "month"));
        System.out.println("Total Distance Traveled during " + firstDay.get(0).getDate().toString("yyyy") + ": " + analyzer.findTotalDistanceTraveled(analyzer.findDataEntry(0).getDate().toString("yyyy"), "year"));
    }
}
