import java.util.Collection;

public class User<Trip> {

    Collection<Trip> trips;
    String id;//can't be empty string

    public User(String id){

    }
    //Should print flight information, when/where for departure, when/where for arrival
    public void checkFlights(){

    }
    //Adds a trip to the collection
    public void addTrip(Trip newTrip){

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
        return "";
    }


}
