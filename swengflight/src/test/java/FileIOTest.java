import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

public class FileIOTest {
    /**
     * NOTE: this test is manual and files must be looked at to confirm, could not think of an automated test for this.
     */
    @Test
    public void toJsonFileTest() throws IOException {
        User testUser = new User("janesmith");
        LinkedList<Ticket> testTickets = new LinkedList<>();
        String now = String.valueOf(Instant.now().getEpochSecond());

        //initialize test tickets
        testTickets.add(new Ticket("first", "a", "1", new Flight("EWR", "A","Detroit", "Newark", ZonedDateTime.now(), ZonedDateTime.now().plusMinutes(10), ZonedDateTime.now().plusHours(3)),  null));
        testTickets.add(new Ticket("second", "b", "2", new Flight("DTW", "A","Los Angeles", "Detroit", ZonedDateTime.now(), ZonedDateTime.now().plusMinutes(10), ZonedDateTime.now().plusHours(3)), null));
        testTickets.add(new Ticket("third", "c", "3", new Flight("LAX", "A","Newark", "Los Angeles", ZonedDateTime.now(), ZonedDateTime.now().plusMinutes(10), ZonedDateTime.now().plusHours(3)), null));

        //export with no trips
        JsonUtil.toJsonFile("src/test/resources/" + now +".json", testUser);

        //export with one trip (single ticket)
        Collection<Ticket> singleTicketTrip = new LinkedList<>();
        singleTicketTrip.add(testTickets.get(0));
        Trip testTrip01 = new Trip(singleTicketTrip);

        testUser.addTrip(testTrip01);
        JsonUtil.toJsonFile("src/test/resources/" + now +".json", testUser);

        //export with two trips (single ticket and double ticket)
        Collection<Ticket> multiTicketTrip = new LinkedList<>();
        multiTicketTrip.add(testTickets.get(1));
        multiTicketTrip.add(testTickets.get(2));
        Trip testTrip02 = new Trip(multiTicketTrip);

        testUser.addTrip(testTrip02);
        JsonUtil.toJsonFile("src/test/resources/" + now +".json", testUser);

    }

    @Test
    public void fromJsonFileTest() throws IOException {
        User testUser = new User("janesmith");
        LinkedList<Ticket> testTickets = new LinkedList<>();
        String now = String.valueOf(Instant.now().getEpochSecond());

        //initialize test tickets
        testTickets.add(new Ticket("first", "a", "1", new Flight("EWR", "A","Detroit", "Newark", ZonedDateTime.now(), ZonedDateTime.now().plusMinutes(10), ZonedDateTime.now().plusHours(3)),  null));
        testTickets.add(new Ticket("second", "b", "2", new Flight("DTW", "A","Los Angeles", "Detroit", ZonedDateTime.now(), ZonedDateTime.now().plusMinutes(10), ZonedDateTime.now().plusHours(3)), null));
        testTickets.add(new Ticket("third", "c", "3", new Flight("LAX", "A","Newark", "Los Angeles", ZonedDateTime.now(), ZonedDateTime.now().plusMinutes(10), ZonedDateTime.now().plusHours(3)), null));

        //export with no trips
        JsonUtil.toJsonFile("src/test/resources/" + now +".json", testUser);

        //reimport
        User toCheck = JsonUtil.fromJsonFile("src/test/resources/" + now +".json", User.class);
        assertEquals(toCheck, testUser);


        //export with one trip (single ticket)
        Collection<Ticket> singleTicketTrip = new LinkedList<>();
        singleTicketTrip.add(testTickets.get(0));
        Trip testTrip01 = new Trip(singleTicketTrip);

        testUser.addTrip(testTrip01);
        JsonUtil.toJsonFile("src/test/resources/" + now +".json", testUser);

        //reimport
        toCheck = JsonUtil.fromJsonFile("src/test/resources/" + now +".json", User.class);
//        assertEquals(toCheck, testUser);

        //export with two trips (single ticket and double ticket)
        Collection<Ticket> multiTicketTrip = new LinkedList<>();
        multiTicketTrip.add(testTickets.get(1));
        multiTicketTrip.add(testTickets.get(2));
        Trip testTrip02 = new Trip(multiTicketTrip);

        testUser.addTrip(testTrip02);
        JsonUtil.toJsonFile("src/test/resources/" + now +".json", testUser);

        //reimport
        toCheck = JsonUtil.fromJsonFile("src/test/resources/" + now +".json", User.class);
//        assertEquals(toCheck, testUser);

    }
}
