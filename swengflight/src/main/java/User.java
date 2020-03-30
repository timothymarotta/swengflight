import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class User {

    LinkedList<Trip> trips;
    String id;//can't be empty string

    public User(String id){
        trips = new LinkedList<Trip>();
        if (id == ""){
            throw new IllegalArgumentException("Can't use empty string for id");
        }
        else{
            this.id = id;
        }


    }
    //Should print flight information, when/where for boarding/departure, where for arrival for next trip
    public void checkFlights(){
        Trip currentTrip = this.nextTrip();
        Iterator<Ticket> itr = currentTrip.getTickets().iterator();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyy");
        while (itr.hasNext()){
            Ticket currentTicket = itr.next();
            Flight currentFlight = currentTicket.getFlight();
            String boardDateString = "plaaceholder";
            String message = "Your flight from " + currentFlight.getAirport() + " to " + currentFlight.getArrivalCity() + " boards at gate " + currentFlight.getGate() + " on ";
        }


    }
    //Adds a trip to the collection
    public void addTrip(Trip newTrip){
        trips.add(newTrip);
    }


    //removes trip at front
    public void removeTrip(){
        trips.removeFirst();
    }
    //removes trip at index, if index not accessible throws IndexOutOfBoundsException
    public void removeTrip(int index){
        trips.remove(index);
    }
    //returns trip at front, null if list is empty
    public Trip nextTrip(){
        if (trips.isEmpty()) {
            return null;
        }
        else{
            return trips.getFirst();
        }
    }

    //returns the collection
    public Collection<Trip> getTrips(){
        return trips;
    }

    //set id, throws IllegalArgumentException if empty string
    public void setId(String id){
        if (id == ""){
            throw new IllegalArgumentException("Can't set ID to empty string");
        }
        else{
            this.id = id;
        }
    }
    //returns id
    public String getId(){
        return id;
    }


}
