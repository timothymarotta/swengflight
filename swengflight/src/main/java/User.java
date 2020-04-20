import java.io.*;
import java.util.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class User implements Serializable {

    LinkedList<Trip> trips;
    String id;//can't be empty string

    public User(){
        //default constructor
    }

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
    public List<String> checkFlights(){
        Trip currentTrip = this.nextTrip();
        Iterator<Ticket> itr = currentTrip.getTickets().iterator();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyy");
        List<String> toReturn = new LinkedList<String>();
        while (itr.hasNext()){
            Ticket currentTicket = itr.next();
            toReturn.add(getUpdateString(currentTicket));
        }

        return toReturn;


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
  
    public static String getDateString(ZonedDateTime date){
        return Integer.toString(date.getMonthValue()) + '/' + Integer.toString(date.getDayOfMonth()) + '/' + Integer.toString(date.getYear());
    }

    public static String getTimeString(ZonedDateTime date){
        String ampm = "";
        int hour = date.getHour();
        String minuteStr = Integer.toString(date.getMinute());
        if (hour > 12){
            hour -= 12;
            ampm = "p.m.";
        }
        else if (hour == 0){
            hour = 12;
            ampm = "a.m.";
        }
        else if (hour == 12){
            ampm = "p.m.";
        }
        else{
            ampm = "a.m.";
        }
        if (minuteStr.length() == 1){
            minuteStr = "0" + minuteStr;
        }
        return Integer.toString(hour) + ":" + minuteStr + " " + ampm;
    }

    public static String getUpdateString(Ticket ticket){
        Flight currentFlight = ticket.getFlight();
        String boardDateString = getDateString(currentFlight.boardingTime);
        String departDateString = getDateString(currentFlight.departureTime);
        String boardTimeString = getTimeString(currentFlight.boardingTime);
        String departTimeString = getTimeString(currentFlight.departureTime);
        String arrivalDateString = getDateString(currentFlight.arrivalTime);
        String arrivalTimeString = getTimeString(currentFlight.arrivalTime);
        String message = "Your flight from " + currentFlight.getAirport() + " to " + currentFlight.getArrivalCity() + " boards at gate " + currentFlight.getGate() + " on " + boardDateString + " at " + boardTimeString + ", departs on " + departDateString + " at " + departTimeString + ", and arrives in " + currentFlight.getArrivalCity() + " on " + arrivalDateString + " at " + arrivalTimeString + " " + currentFlight.getArrivalCity() + " time.";
        return message;
    }

//    JSON FUNCTIONS
    private void setTrips(){

    }

    @Override
    public boolean equals(Object obj) {
        return (this == obj);
    }
}
