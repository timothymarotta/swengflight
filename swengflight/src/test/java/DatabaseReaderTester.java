import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseReaderTester {

    @Test
    public void getPlaneTest() {
        assertEquals("Boeing 787-10", DatabaseReader.getPlane("78J", "B78X"));
        assertEquals(null, DatabaseReader.getPlane("Dont", "Work"));
    }
}
