import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DatabaseReader {

    private static String planeDataPath = "data/planes.dat";
    private static String countryDataPath = "data/countries.dat";
    private static String airlineDataPath = "data/airlines.dat";
    private static List<String[]> planes;
    private static List<String[]> countries;
    private static List<String[]> airlines;


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

    public static String getCountry(String iso_code, String dafif_code) {
        //Cache data in country list. Only read file the first time
        if(iso_code == null && dafif_code == null) return null;

        if(countries == null) {
            countries = new ArrayList<String[]>();

            try {
                // Create an object of filereader
                // class with CSV file as a parameter.
                FileReader filereader = new FileReader(countryDataPath);

                // create csvReader object passing
                // file reader as a parameter
                CSVReader csvReader = new CSVReader(filereader);
                countries = (csvReader.readAll());
            } catch (Exception e) {
                return null;
            }
        }

        //Use java stream api to filter list for correct plane
        String[] country = countries.stream()
                .filter((p) -> (p[1].equals(iso_code) || p[1].equals("\\N") || iso_code == null) && (p[2].equals(dafif_code) || dafif_code == null))
                .findFirst()
                .orElse(null);

        return country == null ? null : country[0];
    }

    public static String getAirline(String alias) {
        return null;
    }
}
