import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JUnitInitialTester {
    @Test
    public void TestJUnitTestConstructor() {
        JUnitInitialTest t = new JUnitInitialTest(true);
        JUnitInitialTest f = new JUnitInitialTest(false);

        assertTrue(t.getValue());
        assertFalse(f.getValue());

    }
}
