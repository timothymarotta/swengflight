import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseReaderTester {

    @Test
    public void getPlaneTest() {
        assertEquals("Boeing 787-10", DatabaseReader.getPlane("78J", "B78X"));
        assertEquals(null, DatabaseReader.getPlane("Dont", "Work"));
    }

    @Test
    public void getCountryTest() {
        assertEquals("Puerto Rico", DatabaseReader.getCountry("PR", "RQ"));
        assertEquals("Ashmore and Cartier Islands", DatabaseReader.getCountry(null, "AT"));
        assertEquals(null, DatabaseReader.getCountry("Dont", "Work"));
        assertEquals(null, DatabaseReader.getCountry(null, null));
    }

    @Test
    public void getAirlineTest() {
        assertEquals("All Nippon Airlines", DatabaseReader.getAirline("ANA"));
    }
}
