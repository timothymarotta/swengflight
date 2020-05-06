import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paranamer.ParanamerModule;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.Buffer;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

public class FlightAPI {

    private static Credentials credentials;
    private static final String KEY_PATH = "data/api.credentials";

    public static String getKey() {
        try {
            if(credentials == null) {
                credentials = JsonUtil.fromJsonFile(KEY_PATH, Credentials.class);
            }
            return credentials.access_key;
        } catch (IOException e) {
            System.out.println("ERROR: Could not find credentials file");
        }
        return null;
    }

    public static Flight buildFlightFromData(APIFlight data) {
        ZonedDateTime boardingTime = ZonedDateTime.parse(data.departure.estimated);
        boardingTime.minusMinutes(30);
        ZonedDateTime departureTime = ZonedDateTime.parse(data.departure.estimated);
        ZonedDateTime arrivalTime = ZonedDateTime.parse(data.arrival.estimated);

        return new Flight(data.departure.airport, data.departure.gate, data.arrival.airport, data.departure.airport, boardingTime, departureTime, arrivalTime);
    }


    public static List<Flight> getFlightByDepArr(String depAirport, String arrAirport) throws Exception {
        String depIATA = DatabaseReader.getIATAFromAirport(depAirport);
        String arrIATA = DatabaseReader.getIATAFromAirport(arrAirport);

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("dep_iata", depIATA);
        parameters.put("arr_iata", arrIATA);


        String data = getFlightJSONByParameter(parameters);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new ParanamerModule());
        mapper.registerModule(new JavaTimeModule());
        API_Response response = mapper.readValue(data, API_Response.class);

        List<Flight> output = new ArrayList<Flight>(response.data.length);
        for (APIFlight f : response.data) {
            output.add(buildFlightFromData(f));
        }

        return output;
    }

    public static Flight getFlightByNumber(String flightNumber) throws Exception{
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("flight_number", flightNumber);


        String data = getFlightJSONByParameter(parameters);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new ParanamerModule());
        mapper.registerModule(new JavaTimeModule());
        API_Response response = mapper.readValue(data, API_Response.class);

        APIFlight flight = response.data[0];


        return buildFlightFromData(flight);
    }

    public static String getFlightJSONByParameter(Map<String, String> parameters) throws Exception{

        String backupPath = "data/";
        for (String v :
                parameters.values()) {
            backupPath += v + "-";
        }
        backupPath +=".flightdata";
        File backup = new File(backupPath);
        parameters.put("access_key", getKey());

        String flightJson;

        if(backup.exists()) {
            BufferedReader in = new BufferedReader(new FileReader(backup));
            StringBuilder content = new StringBuilder();
            String currentLine;
            while((currentLine = in.readLine()) != null) {
                content.append(currentLine);
            }
            flightJson = content.toString();
        } else {
            String apiResponse = getJSONFromAPI(parameters);
            FileWriter out = new FileWriter(backup);
            out.write(apiResponse);
            out.close();
            flightJson = apiResponse;
        }

        return flightJson;
    }

    private static String getJSONFromAPI(Map<String, String> parameters) throws Exception{
        System.out.println("WARNING: This method called on the API");

        URL url = new URL("http://api.aviationstack.com/v1/flights?" + ParameterStringBuilder.getParamsString(parameters));
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/json");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();

        return content.toString();
    }

}

class ParameterStringBuilder {
    public static String getParamsString(Map<String, String> params)
            throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }
}

class Credentials {
    public String access_key;
}

class API_Response {
    public Pagination pagination;
    public APIFlight[] data;
}

class Pagination {
    public int limit;
    public int offset;
    public int count;
    public int total;
}

class APIFlight {
    public String flight_date;
    public String flight_status;
    public DepartureData departure;
    public ArrivalData arrival;
    public APIAirline airline;
    public APIFlightMetaData flight;
    public APIAircraft aircraft;
    public APILiveFlightData live;
}

class DepartureData {
    public String airport;
    public String timezone;
    public String iata;
    public String icao;
    public String terminal;
    public String gate;
    public int delay;
    public String scheduled;
    public String estimated;
    public String actual;
    public String estimated_runway;
    public String actual_runway;
}

class ArrivalData {
    public String airport;
    public String timezone;
    public String iata;
    public String icao;
    public String terminal;
    public String gate;
    public String baggage;
    public int delay;
    public String scheduled;
    public String estimated;
    public String actual;
    public String estimated_runway;
    public String actual_runway;
}

class APIAirline {
    public String name;
    public String iata;
    public String icao;
}

class APIFlightMetaData {
    public String number;
    public String iata;
    public String icao;
    public APICodeShare codeshared;
}


//"codeshared":{"airline_name":"shandong airlines","airline_iata":"sc","airline_icao":"cdg","flight_number":"1161","flight_iata":"sc1161","flight_icao":"cdg1161"}}
class APICodeShare {
    public String airline_name;
    public String airline_iata;
    public String airline_icao;
    public String flight_number;
    public String flight_iata;
    public String flight_icao;
}

class APIAircraft {
    public String registration;
    public String iata;
    public String icao;
    public String icao24;
}

class APILiveFlightData {
    public String updated;
    public double latitude;
    public double longitude;
    public double altitude;
    public double direction;
    public double speed_horizontal;
    public double speed_vertical;
    public boolean is_ground;
}