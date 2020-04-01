import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
    //Should print flight information, when/where for departure, when/where for arrival
    public void checkFlights(){

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

    /**
     * exports data from User object to .txt file
     * @param filename name of .txt file
     */
    public void exportData(String filename) throws IOException {
        PrintStream printStream = new PrintStream(filename);
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

        //print user ID and time of export
        printStream.println("User ID: " + getId());
        printStream.println("Time of export: " + dateFormat.format(new Date()));
        printStream.println();

        //print flights associated with each trip
        for (int i = 0; i < trips.size(); i++){
            Trip nextTrip = trips.get(i);
            Collection<Ticket> tickets = nextTrip.getTickets();
            for(Ticket next: tickets){
                printStream.println("Passenger name: " + next.getName());
                printStream.println("Airline: " + next.getAirline());
                printStream.println("Ticket Number: " + next.getTicketNumber());
                printStream.println("Date: " + next.getDate());
                printStream.println("Flight: " + next.getFlight());
                Flight nextFlight = next.getFlight();
                printStream.println("\tAirport: " + nextFlight.getAirport());
                printStream.println("\tGate: " + nextFlight.getGate());
                printStream.println("\tArrival City: " + nextFlight.getArrivalCity());
                printStream.println("\tDeparture City: " + nextFlight.getDepartureCity());
                printStream.println("\tBoarding Time: " + nextFlight.getBoardingTime());
                printStream.println("\tDeparture Time: " + nextFlight.getDepartureTime());
                printStream.println("\tArrival Time: " + nextFlight.getArrivalTime());
                printStream.println();
            }
            printStream.println("---");
            printStream.println();
            printStream.flush();
        }
        //close file
        printStream.close();
    }


}
