import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DatabaseReader {

    private final static String planeDataPath = "data/planes.dat";
    private final static String countryDataPath = "data/countries.dat";
    private final static String airlineDataPath = "data/airlines.dat";
    private final static String airportDataPath = "data/airports.dat";
    private final static String routeDataPath = "data/routes.dat";
    private static List<String[]> planes;
    private static List<String[]> countries;
    private static List<String[]> airlines;
    private static List<String[]> airports;
    private static List<String[]> routes;


    public static String getPlane(String IATACode, String ICAOCode) {
        //Cache data in planes list. Only read file the first time
        if(planes == null) {
            planes = readDataFromFile(planeDataPath);
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
            countries = readDataFromFile(countryDataPath);
        }

        //Use java stream api to filter list for correct plane
        String[] country = countries.stream()
                .filter((p) -> (p[1].equals(iso_code) || p[1].equals("\\N") || iso_code == null) && (p[2].equals(dafif_code) || dafif_code == null))
                .findFirst()
                .orElse(null);

        return country == null ? null : country[0];
    }

    public static String getAirline(String alias) {
        if(airlines == null) {
            airlines = readDataFromFile(airlineDataPath);
        }

        //Use java stream api to filter list for correct plane
        String[] airline = airlines.stream()
                .filter((p) -> p[4].equals(alias))
                .findFirst()
                .orElse(null);

        return airline == null ? null : airline[1];
    }

    public static String getAirport(String IATACode, String ICAOCode) {
        if(airports == null) {
            airports = readDataFromFile(airportDataPath);
        }

        String[] airport = airports.stream()
                .filter((p) -> p[4].equals(IATACode) && p[5].equals(ICAOCode))
                .findFirst()
                .orElse(null);

        return airport == null ? null : airport[1];
    }

    public static String getIATAFromAirport(String airportString) {
        if(airports == null) {
            airports = readDataFromFile(airportDataPath);
        }

        String[] iata = airports.stream()
                .filter((p) -> p[1].toLowerCase().equals(airportString.toLowerCase()))
                .findFirst()
                .orElse(null);

        return iata == null ? null : iata[4];
    }

    public static List<String[]> readDataFromFile(String path) {
        List<String[]> output = new ArrayList<String[]>();

        try {
            // Create an object of filereader
            // class with CSV file as a parameter.
            FileReader filereader = new FileReader(path);

            // create csvReader object passing
            // file reader as a parameter
            CSVReader csvReader = new CSVReader(filereader);
            output = (csvReader.readAll());
        } catch (Exception e) {
            return null;
        }
        return output;
    }
}
