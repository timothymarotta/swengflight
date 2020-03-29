import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class User<Trip> {

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
        return null;
    }

    //returns the collection
    public Collection<Trip> getTrips(){
        return trips;
    }

    //set id, throws IllegalArgumentException if empty string
    public void setId(){

    }
    //returns id
    public String getId(){
        return id;
    }


}
