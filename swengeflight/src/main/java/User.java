import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class User<Trip> {

    Collection<Trip> trips;
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

    public void removeTrip(){

    }

    public Trip nextTrip(){
        return null;
    }

    public Collection<Trip> getTrips(){
        return null;
    }

    public void setId(){

    }

    public String getId(){
        return id;
    }


}
