package openpaths;

/**
 * Test the CSVReader and accompanying classes for functionality
 *
 * @author Fintan Kelly
 */
public class Tester {

    /*
     Main:
     - Create a temporary CSVReader and read in all the data
     */
    public static void main(String[] args) {
        //CSVReader temp = new CSVReader(args[0]);
        //temp.readCSVData();

        JSONReader temp2 = new JSONReader();
        temp2.readJSONData();
    }
}
