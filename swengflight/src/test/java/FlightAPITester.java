import org.junit.jupiter.api.Test;
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
}
