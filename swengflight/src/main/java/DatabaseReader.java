import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseReader {

    private static String planeDataPath = "data/planes.dat";
    private static List<String[]> planes;


    public static String getPlane(String IATACode, String ICAOCode) {
        //Cache data in planes list. Only read file the first time
        if(planes == null) {
            planes = new ArrayList<String[]>();

            try {
                // Create an object of filereader
                // class with CSV file as a parameter.
                FileReader filereader = new FileReader(planeDataPath);

                // create csvReader object passing
                // file reader as a parameter
                CSVReader csvReader = new CSVReader(filereader);
                planes = (csvReader.readAll());
            } catch (Exception e) {
                return null;
            }
        }

        //Use java stream api to filter list for correct plane
        String[] plane = planes.stream()
                .filter((p) -> p[1].equals(IATACode) && (p[2].equals(ICAOCode) || p[2].equals("\\N")))
                .findFirst()
                .orElse(null);

        return plane == null ? null : plane[0];
    }
}
