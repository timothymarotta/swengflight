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
}
