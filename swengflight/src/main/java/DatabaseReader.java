import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseReader {

    private static String planeDataPath = "data/planes.dat";
    private static Map<String, String[]> IATAPlanes;


    public static String getPlane(String IATACode, String ICAOCode) {
        if(IATAPlanes == null) {
            IATAPlanes = new HashMap<String, String[]>();

            try {
                // Create an object of filereader
                // class with CSV file as a parameter.
                FileReader filereader = new FileReader(planeDataPath);

                // create csvReader object passing
                // file reader as a parameter
                CSVReader csvReader = new CSVReader(filereader);

                List allData = csvReader.readAll();
                for (Object a : allData) {
                    for (Object s : ((String[])a)) {
                        System.out.print(s.toString() + " ");
                    }
                    System.out.println();
                }
            } catch (Exception e) {
                return null;
            }

        }



        return null;
    }
}
