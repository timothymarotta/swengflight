import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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

    @Test
    public void checkFlightsTest(){
        User user = new User("test");
        Flight houstonToMiami = new Flight("IAH", "A23", "MIA", "Houston", new Date(2020, 4, 12, 9, 25), new Date(2020, 4, 12, 9, 55));
        //Testing with one flight one trip
        Ticket testTicket = new Ticket("Josh Hayden", "American", "1234", houstonToMiami, new Date(2020, 4, 12));
        Collection<Ticket> ticks = new LinkedList<Ticket>();
        ticks.add(testTicket);
        Trip testTrip = new Trip(ticks);
        user.addTrip(testTrip);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        user.checkFlights();
        String expected = "Your flight from IAH to MIA boards at gate A23 on 4/12/2020 at 9:25 a.m., and departs on 4/12/2020 at 9:55 a.m..";
        assertEquals(expected, outContent.toString());
        //Testing with multiple flights in one trip
        Flight miamiToCleveland = new Flight("MIA", "B13", "CLE", "Miami", new Date(2020, 5, 4, 14, 30), new Date(2020, 5, 4, 15, 0));
        Ticket secondTicket = new Ticket("Josh Hayden", "American", "321", miamiToCleveland, new Date(2020, 5, 4));
        ticks.add(secondTicket);
        testTrip = new Trip(ticks);
        user = new User("test");
        user.addTrip(testTrip);
        expected = expected + "\n" + "Your flight from MIA to CLE boards at gate B13 on 5/4/2020 at 2:30 p.m., and departs on 5/4/2020 at 3:00 p.m..";
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        user.checkFlights();
        assertEquals(expected, outContent.toString());
    }




}
