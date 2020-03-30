import java.time.ZonedDateTime;

public class Flight {
    String flightNumber;
    String airport;
    String gate;
    String arrivalCity;
    String departureCity;
    ZonedDateTime boardingTime;
    ZonedDateTime departureTime;

    Flight(String airport, String gate, String arrivalCity,
           String departureCity, ZonedDateTime boardingTime, ZonedDateTime departureTime) {
        this.airport = airport;
        this.gate = gate;
        this.arrivalCity = arrivalCity;
        this.departureCity = departureCity;
        this.boardingTime = boardingTime;
        this.departureTime = departureTime;
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

    public void setFlightNumberNumber(String flightNumber) {
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
}
