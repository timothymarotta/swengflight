package placeholder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdderTest(){

    @Test
    void addTest(){
        Adder adr = new Adder("nice");
        assertEquals(3, adr.add(1, 2));
            }

        }