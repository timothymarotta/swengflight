import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Iterator;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void ConstructorTest(){
        //can't really make tests until flight is up
        //basic test
        User first = new User("123");
        //checks for empty string
        assertEquals("123", first.getId());
        assertThrows(IllegalArgumentException.class, () -> new User(""));
        //checking with string length one
        first = new User("a");
        assertEquals("a", first.getId());


    }

    @Test
    public void addTripTest(){
        User user = new User("123");
        Collection<Ticket> tickets = new LinkedList<Ticket>();
        tickets.add(new Ticket("first", "a", "1", null,  null));
        Trip firstTrip = new Trip(tickets);
        tickets = new LinkedList<Ticket>();
        tickets.add(new Ticket("second", "b", "2", null, null));
        Trip secondTrip = new Trip(tickets);
        tickets = new LinkedList<Ticket>();
        tickets.add(new Ticket("third", "c", "3", null, null));
        tickets.add(new Ticket("fourth", "d", "4", null, null));
        Trip multipleTicketTrip = new Trip(tickets);
        //checking that adding basic trips is fine
        user.addTrip(firstTrip);
        user.addTrip(secondTrip);
        //checking that adding a more complex trip is fine
        user.addTrip(multipleTicketTrip);
        Collection<Trip> trips = user.getTrips();
        Iterator<Trip> itr = trips.iterator();
        Trip first = itr.next();
        Trip second = itr.next();
        Trip complex = itr.next();
        assertEquals(firstTrip, first);
        assertEquals(secondTrip, second);
        assertEquals(multipleTicketTrip, complex);


    }

    @Test
    public void removeTripTest(){
        User user = new User("a");
        Collection<Ticket> tickets = new LinkedList<Ticket>();
        tickets.add(new Ticket("first", "a", "1", null,  null));
        Trip firstTrip = new Trip(tickets);
        tickets = new LinkedList<Ticket>();
        tickets.add(new Ticket("second", "b", "2", null, null));
        Trip secondTrip = new Trip(tickets);
        tickets = new LinkedList<Ticket>();
        tickets.add(new Ticket("third", "c", "3", null, null));
        tickets.add(new Ticket("fourth", "d", "4", null, null));
        Trip multipleTicketTrip = new Trip(tickets);
        //checking with just one ticket
        user.addTrip(firstTrip);
        user.removeTrip();
        Collection<Trip> trips = user.getTrips();
        assertTrue(trips.isEmpty());
        user.addTrip(firstTrip);
        user.addTrip(secondTrip);
        user.addTrip(multipleTicketTrip);
        //removing from end
        user.removeTrip(2);
        Iterator<Trip> itr = user.getTrips().iterator();
        Trip first = itr.next();
        Trip second = itr.next();
        assertEquals(first, firstTrip);
        assertEquals(second, secondTrip);
        assertFalse(itr.hasNext());
        user.addTrip(multipleTicketTrip);
        //removing from middle
        user.removeTrip(1);
        itr = user.getTrips().iterator();
        first = itr.next();
        second = itr.next();
        assertEquals(first, firstTrip);
        assertEquals(second, multipleTicketTrip);
        assertFalse(itr.hasNext());
        //removing from front
        user.removeTrip(0);
        itr = user.getTrips().iterator();
        first = itr.next();
        assertEquals(first, multipleTicketTrip);
        assertFalse(itr.hasNext());
        //remove bad index
        assertThrows(IndexOutOfBoundsException.class, () -> user.removeTrip(1));

    }

    @Test
    public void nextTripTest(){
        User user = new User("a");
        Collection<Ticket> tickets = new LinkedList<Ticket>();
        tickets.add(new Ticket("first", "a", "1", null,  null));
        Trip firstTrip = new Trip(tickets);
        tickets = new LinkedList<Ticket>();
        tickets.add(new Ticket("second", "b", "2", null, null));
        Trip secondTrip = new Trip(tickets);
        tickets = new LinkedList<Ticket>();
        tickets.add(new Ticket("third", "c", "3", null, null));
        tickets.add(new Ticket("fourth", "d", "4", null, null));
        Trip multipleTicketTrip = new Trip(tickets);
        //checking with empty list
        assertEquals(null, user.nextTrip());
        user.addTrip(firstTrip);
        //checking with one trip
        assertEquals(firstTrip, user.nextTrip());
        //checking with multiple trips in list
        user.addTrip(secondTrip);
        assertEquals(firstTrip, user.nextTrip());
    }

    @Test
    public void setIDTest(){
        User user = new User("a");
        //checking that a basic change works
        user.setId("123");
        assertEquals("123", user.getId());
        //checking that empty string fails
        assertThrows(IllegalArgumentException.class, ()-> user.setId(""));
    }

    /**
     * NOTE: this test is manual and files must be looked at to confirm, could not think of an automated test for this.
     */
    @Test
    public void exportDataTest() throws IOException {
        User testUser = new User("janesmith");
        LinkedList<Ticket> testTickets = new LinkedList<>();
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy_HH:mm:ss");

        //initialize test tickets
        testTickets.add(new Ticket("first", "a", "1", new Flight("EWR", "A","Detroit", "Newark", ZonedDateTime.now(), ZonedDateTime.now().plusMinutes(10), ZonedDateTime.now().plusHours(3)),  null));
        testTickets.add(new Ticket("second", "b", "2", new Flight("DTW", "A","Los Angeles", "Detroit", ZonedDateTime.now(), ZonedDateTime.now().plusMinutes(10), ZonedDateTime.now().plusHours(3)), null));
        testTickets.add(new Ticket("third", "c", "3", new Flight("LAX", "A","Newark", "Los Angeles", ZonedDateTime.now(), ZonedDateTime.now().plusMinutes(10), ZonedDateTime.now().plusHours(3)), null));

        //export with no trips
        testUser.exportData(dateFormat.format(new Date()));

        //export with one trip (single ticket)
        Collection<Ticket> singleTicketTrip = new LinkedList<>();
        singleTicketTrip.add(testTickets.get(0));
        Trip testTrip01 = new Trip(singleTicketTrip);

        testUser.addTrip(testTrip01);
        testUser.exportData(dateFormat.format(new Date()));

        //export with two trips (single ticket and double ticket)
        Collection<Ticket> multiTicketTrip = new LinkedList<>();
        multiTicketTrip.add(testTickets.get(1));
        multiTicketTrip.add(testTickets.get(2));
        Trip testTrip02 = new Trip(multiTicketTrip);

        testUser.addTrip(testTrip02);
        testUser.exportData(dateFormat.format(new Date()));

    }

}
