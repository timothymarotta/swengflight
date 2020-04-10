import java.time.ZonedDateTime;

public class Flight {
    String flightNumber;
    String airport;
    String gate;
    String arrivalCity;
    String departureCity;
    ZonedDateTime boardingTime;
    ZonedDateTime departureTime;
    ZonedDateTime arrivalTime;

    Flight(String airport, String gate, String arrivalCity,
           String departureCity, ZonedDateTime boardingTime, ZonedDateTime departureTime, ZonedDateTime arrivalTime) {
        this.airport = airport;
        this.gate = gate;
        this.arrivalCity = arrivalCity;
        this.departureCity = departureCity;
        this.boardingTime = boardingTime;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getAirport() {
        return airport;
    }

    public String getGate() {
        return gate;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public ZonedDateTime getBoardingTime() {
        return boardingTime;
    }

    public ZonedDateTime getDepartureTime() {
        return departureTime;
    }

    public ZonedDateTime getArrivalTime(){
        return arrivalTime;
    }
  
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public void setBoardingTime(ZonedDateTime boardingTime) {
        this.boardingTime = boardingTime;
    }

    public void setDepartureTime(ZonedDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(ZonedDateTime arrivalTime){
        this.arrivalTime = arrivalTime;
    }
}
