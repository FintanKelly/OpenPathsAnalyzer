package openpaths;

import java.util.ArrayList;

/**
 * Class that will handle all of the manipulation and analysis of the OpenPath
 * data
 *
 * @author Fintan Kelly
 */
public class Analyzer {
    /*
     Variables:
     - ArrayList that will contain some amount of OpenPath data
     */

    private ArrayList<OpenPath> dataList;

    /*
     Main Constructor:
     - Takes a new ArrayList of OpenPath data
     */
    public Analyzer(ArrayList<OpenPath> newList) {
        dataList = newList;
    }

    /*
     Find Data:
     - Takes a date and a modifier, and then depending on the modifier, finds some amount of data
     */
    public ArrayList<OpenPath> findDataForModifier(String date, String modifier) {
        ArrayList<OpenPath> newList = new ArrayList<>();

        switch (modifier) {
            case "day":
                for (OpenPath location : dataList) {
                    if (location.getDate().toString("yyyy/MM/dd").contains(date)) {
                        newList.add(location);
                    }
                }
                break;
            case "month":
                for (OpenPath location : dataList) {
                    if (location.getDate().toString("yyyy/MM").contains(date)) {
                        newList.add(location);
                    }
                }
                break;
            case "year":
                for (OpenPath location : dataList) {
                    if (location.getDate().toString("yyyy").contains(date)) {
                        newList.add(location);
                    }
                }
                break;
            default:
                newList = dataList;
        }

        return newList;
    }

    /*
     Find Distance Traveled:
     - Takes a date and a modifier, then finds the correct data depending on the modifier
     - Calculates the total distance for the list of data
     */
    public float findTotalDistanceTraveled(String date, String modifier) {
        float totalDistance = 0;
        ArrayList<OpenPath> newList = findDataForModifier(date, modifier);

        for (int i = 0; i < newList.size() - 1; i++) {
            totalDistance += newList.get(i).findDistanceBetween(newList.get(i + 1));
        }

        return totalDistance;
    }

    /*
     Get List:
     - Returns the ArrayList of OpenPath data
     */
    public ArrayList<OpenPath> getDataList() {
        return dataList;
    }

    /*
     Find Entry:
     - Finds an OpenPath object in the ArrayList (small wrapper for get() method)
     */
    public OpenPath findDataEntry(int index) {
        return dataList.get(index);
    }
}
