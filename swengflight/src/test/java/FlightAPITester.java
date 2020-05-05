import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class FlightAPITester {

    @Test
    public void getCredentialsTest() {
        String access_key = FlightAPI.getKey();
        System.out.println(access_key);
        assertTrue(access_key != null);
    }

    @Test
    public void testGetFlightByNumber() throws Exception {
        Flight f = FlightAPI.getFlightByNumber("1161");
        assertEquals(f.airport, "Arturo Merino Benitez");
    }

    @Test
    public void testGetFlightsByArrDep() throws Exception {
        List<Flight> flights = FlightAPI.getFlightByDepArr("General Edward Lawrence Logan International Airport", "Los Angeles International Airport");
        for (Flight f :
                flights) {
            assertEquals(f.getDepartureCity(), "Logan International");
            assertEquals(f.getArrivalCity(), "Los Angeles International");
//            System.out.println(f.getDepartureCity() + " to " + f.getArrivalCity());
        }
    }
}
