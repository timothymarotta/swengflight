import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

enum FlightState {Landing, ViewTrips, GetFlightInfo, AddTrip, DeleteTrip, UpdateTrip}

public class Flight_UI {

    private static User user;
    private static FlightState currentUIState;
    private static Scanner in;
    private static boolean isRunning;

    public static void main(String[] args) throws Exception {

        isRunning = true;
        currentUIState = FlightState.Landing;
        setUpUser();

        while (isRunning) {
            if(currentUIState ==  FlightState.Landing){
                handleLanding();
            } else if(currentUIState == FlightState.ViewTrips) {
                handleViewTrips();
            } else if(currentUIState == FlightState.GetFlightInfo) {
                handleGetFlightInfo();
            } else if(currentUIState == FlightState.AddTrip) {
                handleAddTrip();
            } else if (currentUIState == FlightState.DeleteTrip){
                handleDeleteTrip();
            } else if (currentUIState == FlightState.UpdateTrip){
                handleUpdateTrip();
            }

        }
    }

    static void setUpUser() throws IOException {
        //find the most recent file in src/main/resources
        File directory = new File("src/main/resources");
        File[] files = directory.listFiles(File::isFile);
        long lastModifiedTime = Long.MIN_VALUE;
        File chosenFile = null;

        if (files != null) {
            for (File file : files) {
                if (file.lastModified() > lastModifiedTime) {
                    chosenFile = file;
                    lastModifiedTime = file.lastModified();
                }
            }
        }

        if (chosenFile == null) {
            //default for now
//            Flight_UI.user = new User();
            Flight_UI.user = testingUser();
        } else {
            Flight_UI.user = JsonUtil.fromJsonFile("src/main/resources/" + chosenFile.getName(), User.class);
        }
    }

    static void handleLanding() throws IOException {
        in = new Scanner(System.in);
        System.out.print("Enter 1 to view current trips, or 2 to add a new trip, or q to quit: ");
        String response = in.next();
        while (!(response.equals("1") || response.equals("2") || response.equals("q"))){
            System.out.print("Invalid entry, please enter 1, 2, or q: ");
            response = in.next();
        }
        if (response.equals("1")){
            currentUIState = FlightState.ViewTrips;
        } else if (response.equals("2")){
            currentUIState = FlightState.AddTrip;
        } else {
            isRunning = false;
        }

    }

    static void handleViewTrips(){
        System.out.println("Your current trips are:");
        Collection<Trip> trips = user.getTrips();
        Iterator<Trip> tripItr = trips.iterator();
        int i = 0;
        while (tripItr.hasNext()){
            Trip currentTrip = tripItr.next();
            i += 1;
            System.out.println(Integer.toString(i) + ": " + currentTrip.getName());
        }
        System.out.print("For information on a trip press 1, to update a trip press 2, to delete a trip press 3, and to return to the home page press q: ");
        String response = in.next();
        while (!(response.equals("1") || response.equals("2") || response.equals("3") || response.equals("q"))){
            System.out.print("Invalid entry, please enter 1, 2, 3, or q: ");
            response = in.next();
        }
        String targetAction;
        if (response.equals("q")){
            currentUIState = FlightState.Landing;
            return;
        }
        else{
            targetAction = response;
        }
        if (targetAction.equals("1")){
            int tripIndex = 0;
            while (tripIndex < 1 || tripIndex > trips.size()) {
                try {
                    System.out.print("Select a trip by entering the number next to the trips name: ");
                    String tripResponse = in.next();
                    tripIndex = Integer.parseInt(tripResponse);
                } catch (NumberFormatException e) {
                }
            }
            List<String> strings = user.checkFlights(tripIndex - 1);
            for (int j = 0; j < strings.size(); ++j){
                System.out.println(strings.get(j));
            }
            currentUIState = FlightState.Landing;
        }
        else if (targetAction.equals("2")){
            currentUIState = FlightState.UpdateTrip;
        }
        else if (targetAction.equals("3")){
            currentUIState = FlightState.DeleteTrip;
        }
    }
    static void handleGetFlightInfo(){

    }
    static void handleAddTrip() throws Exception {
        //Placeholder for now
        System.out.print("Enter the name of the trip you would like to create, or type q to return home: ");
        Scanner in = new Scanner(System.in);
        String tripOrQuit = in.next();

        if (!tripOrQuit.equals("q")) {
            System.out.print("Would you like to add tickets to your trip now? (y/n): ");
            String choice = in.next();
            while (!choice.equals("y") && !choice.equals("n")){
                System.out.print("Invalid option. Please type y if you would like to add tickets or n if you want to return home: ");
                choice = in.next();
            }
            Trip currentTrip = new Trip(new ArrayList<Ticket>(), tripOrQuit);
            user.addTrip(currentTrip);

            if (choice.equals("y")){
                handleAddTicketToTrip(currentTrip);
            }

        }
        // returns to main page
        currentUIState = FlightState.Landing;
    }
    static void handleAddTicketToTrip(Trip trip) throws Exception {
        String depAirport, arrAirport;
        System.out.println("Adding ticket to trip.");
        in.nextLine();
        System.out.print("Enter exact name for departure airport: ");
        depAirport = in.nextLine();
        System.out.print("Enter exact name for arrival airport: ");
        arrAirport = in.nextLine();
        System.out.println(depAirport);
        System.out.println(arrAirport);
        List<Flight> flightOptions = FlightAPI.getFlightByDepArr(depAirport, arrAirport);
        if (flightOptions.size() == 0){
            System.out.println("Either airport names were entered incorrectly or there are no available flights.");
            System.out.print("To try to add a ticket again enter y, to cancel adding a ticket enter anything else: ");
            String response = in.next();
            if (response.equals("y")){
                handleAddTicketToTrip(trip);
            }
            else{
                return;
            }
        }
        else{
            int i = 0;
            Iterator<Flight> flightItr = flightOptions.iterator();
            while (i < 10 && flightItr.hasNext()){
                i += 1;
                Flight currentFlight = flightItr.next();
                System.out.println(i + " : " + currentFlight.getDepartureCity() + " to " + currentFlight.getArrivalCity() + " at " + User.getTimeString(currentFlight.getDepartureTime()) + " on " + User.getDateString(currentFlight.getDepartureTime()));
            }
            int flightIndex = 0;
            while (flightIndex < 1 || flightIndex > flightOptions.size() || flightIndex > 10) {
                try {
                    System.out.print("Select a flight to add by entering the number next to the flight, or type q to cancel adding a flight: ");
                    String tripResponse = in.next();
                    if (tripResponse.equals("q")){
                        return;
                    }
                    flightIndex = Integer.parseInt(tripResponse);
                } catch (NumberFormatException e) {
                }
            }
            Flight flightToAdd = flightOptions.get(flightIndex - 1);
            trip.getTickets().add(new Ticket(null, null, null, flightToAdd, flightToAdd.getDepartureTime()));
            System.out.println("Ticket added successfully.");
        }



    }
    static void handleRemoveTicketFromTrip(Trip trip){
        int response = 0;
        LinkedList<Ticket> ticketList = (LinkedList<Ticket>) trip.getTickets();

        response = 0;
        while (response < 1 || response > ticketList.size()) {
            try {
                System.out.print("Select a trip by entering the number next to the trips name: ");
                String tripResponse = in.next();
                response = Integer.parseInt(tripResponse);
            } catch (NumberFormatException e) {
            }
        }

        trip.removeTicket(ticketList.get(response - 1));
        System.out.println("Ticket removed successfully.");
    }
    static void handleDeleteTrip(){
        LinkedList<Trip> trips = (LinkedList<Trip>) user.getTrips();
        int tripIndex = 0;
        while (tripIndex < 1 || tripIndex > trips.size()) {
            try {
                System.out.print("Select a trip to delete by entering the number next to the trips name, or type q to return to the landing page: ");
                String tripResponse = in.next();
                if (tripResponse.equals("q")){
                    currentUIState = FlightState.Landing;
                    return;
                }
                tripIndex = Integer.parseInt(tripResponse);
            } catch (NumberFormatException e) {
            }
        }
        Trip currentTrip = trips.get(tripIndex-1);
        System.out.println("You have chosen to delete " + currentTrip.getName());
        System.out.print("Enter y to confirm deletion or enter any other key to return to landing: ");
        String confirmResponse = in.next();
        if (confirmResponse.equals("y")){
            user.removeTrip(tripIndex -  1);
        }
        else{
            System.out.println("Deletion cancelled");
        }
        currentUIState = FlightState.Landing;
    }
    static void handleUpdateTrip() throws Exception {
        LinkedList<Trip> trips = (LinkedList<Trip>) user.getTrips();
        int tripIndex = 0;
        while (tripIndex < 1 || tripIndex > trips.size()) {
            try {
                System.out.print("Select a trip by entering the number next to the trips name: ");
                String tripResponse = in.next();
                tripIndex = Integer.parseInt(tripResponse);
            } catch (NumberFormatException e) {
            }
        }
        Trip currentTrip = trips.get(tripIndex - 1);
        Iterator<Ticket> ticketItr = currentTrip.getTickets().iterator();
        System.out.println("Current tickets in trip");
        int i = 0;
        while (ticketItr.hasNext()){
            i += 1;
            Ticket currentTicket = ticketItr.next();
            Flight currentFlight = currentTicket.getFlight();
            System.out.println(i + " : " + currentFlight.getDepartureCity() + " to " + currentFlight.getArrivalCity() + " at " + User.getTimeString(currentFlight.getDepartureTime()) + " on " + User.getDateString(currentFlight.getDepartureTime()));
        }
        System.out.print("Enter 1 to add a new ticket to the trip, 2 to remove a ticket from the trip, or q to return to home page: ");
        String response = in.next();
        while (!(response.equals("1") || response.equals("2") || response.equals("q"))){
            System.out.print("Invalid entry, please enter 1, 2, or q: ");
            response = in.next();
        }
        if (response.equals("q")){
            currentUIState = FlightState.Landing;
            return;
        }
        else if (response.equals("1")){
            handleAddTicketToTrip(currentTrip);
        }
        else{
            handleRemoveTicketFromTrip(currentTrip);
        }

    }
    //Create basic user for testing
    static User testingUser() throws IOException{
        User user = new User("Test");
        Collection<Ticket> tickets1 = new LinkedList<Ticket>();
        Flight houstonToMiami = new Flight("IAH", "A23", "MIA", "Houston", LocalDateTime.of(2020, 4, 12, 9, 25).atZone(ZoneId.of("America/Chicago")), LocalDateTime.of(2020, 4, 12, 9, 55).atZone(ZoneId.of("America/Chicago")), LocalDateTime.of(2020, 4, 12, 11, 45).atZone(ZoneId.of("America/Puerto_Rico")));
        tickets1.add(new Ticket("Houston to Mimi", "American", "1234", houstonToMiami, LocalDateTime.of(2020, 4, 12, 0, 0).atZone(ZoneId.of("America/Chicago"))));
        Flight miamiToHouston = new Flight("MIA", "B13", "IAH", "Miami",  LocalDateTime.of(2020, 4, 19, 8, 30).atZone(ZoneId.of("America/Puerto_Rico")), LocalDateTime.of(2020, 4, 19, 9, 0).atZone(ZoneId.of("America/Puerto_Rico")), LocalDateTime.of(2020, 4, 19, 12, 30).atZone(ZoneId.of("America/Puerto_Rico")));
        tickets1.add(new Ticket("Miami to Houston", "American", "2345", miamiToHouston, LocalDateTime.of(2020, 4, 19, 0, 0).atZone(ZoneId.of("America/Chicago"))));
        Trip trip1 = new Trip(tickets1, "Houston To Miami");
        Collection<Ticket> tickets2 = new LinkedList<Ticket>();
        Flight ithacaToCleveland = new Flight("ITH", "B42", "CLE", "Ithaca", LocalDateTime.of(2020, 7, 27, 14, 20).atZone(ZoneId.of("America/Chicago")), LocalDateTime.of(2020, 7, 27, 14, 55).atZone(ZoneId.of("America/Chicago")), LocalDateTime.of(2020, 7, 27, 17, 15).atZone(ZoneId.of("America/Chicago")));
        tickets2.add(new  Ticket("Ithaca To Cleveland","United","4058",  ithacaToCleveland, LocalDateTime.of(2020,  7, 27, 0, 0).atZone(ZoneId.of("America/Chicago"))));
        Trip trip2 = new Trip(tickets2, "Ithaca To Cleveland");
        user.addTrip(trip1);
        user.addTrip(trip2);
        //JsonUtil.toJsonFile("testingUser.json", user);
        return user;
    }
}