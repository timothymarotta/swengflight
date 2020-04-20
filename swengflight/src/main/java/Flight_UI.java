import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Scanner;

enum FlightState {Landing, ViewTrips, GetFlightInfo, AddTrip, AddFlightToTrip}

public class Flight_UI {

    private static User user;
    private static FlightState currentUIState;
    private static Scanner in;

    public static void main(String[] args)  {
        setUpUser();

        while (true) {
            if(currentUIState ==  FlightState.Landing){
                handleLanding();
            } else if(currentUIState == FlightState.ViewTrips) {
                handleViewTrips();
            } else if(currentUIState == FlightState.GetFlightInfo) {
                handleGetFlightInfo();
            } else if(currentUIState == FlightState.AddTrip) {
                handleAddTrip();
            } else if(currentUIState == FlightState.AddFlightToTrip) {
                handleAddFlightToTrip();
            }
        }
    }

    static void setUpUser(){

    }

    static void handleLanding(){

    }

    static void handleViewTrips(){

    }
    static void handleGetFlightInfo(){

    }
    static void handleAddTrip(){

    }
    static void handleAddFlightToTrip(){

    }
    //Create basic user for testing
    static void testingUser(){
        User user = new User("Test");
        Collection<Ticket> tickets1 = new LinkedList<Ticket>();
        Flight houstonToMiami = new Flight("IAH", "A23", "MIA", "Houston", LocalDateTime.of(2020, 4, 12, 9, 25).atZone(ZoneId.of("America/Chicago")), LocalDateTime.of(2020, 4, 12, 9, 55).atZone(ZoneId.of("America/Chicago")), LocalDateTime.of(2020, 4, 12, 11, 45).atZone(ZoneId.of("America/Puerto_Rico")));
        tickets1.add(new Ticket("Josh Hayden", "American", "1234", houstonToMiami, LocalDateTime.of(2020, 4, 12, 0, 0).atZone(ZoneId.of("America/Chicago"))));
        Flight miamiToHouston = new Flight("MIA", "B13", "IAH", "Miami",  LocalDateTime.of(2020, 4, 19, 8, 30).atZone(ZoneId.of("America/Puerto_Rico")), LocalDateTime.of(2020, 4, 19, 9, 0).atZone(ZoneId.of("America/Puerto_Rico")), LocalDateTime.of(2020, 4, 19, 12, 30).atZone(ZoneId.of("America/Puerto_Rico")));
        tickets1.add(new Ticket("Josh Hayden", "American", "2345", miamiToHouston, LocalDateTime.of(2020, 4, 19, 0, 0).atZone(ZoneId.of("America/Chicago"))));
        Trip trip1 = new Trip(tickets1);
    }
}
