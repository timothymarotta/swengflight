import java.util.Date;

public class Flight {
    String airport;
    String gate;
    String arrivalCity;
    String departureCity;
    Date boardingTime;
    Date departureTime;

    Flight(String airport, String gate, String arrivalCity,
           String departureCity, Date boardingTime, Date departureTime) {
        this.airport = airport;
        this.gate = gate;
        this.arrivalCity = arrivalCity;
        this.departureCity = departureCity;
        this.boardingTime = boardingTime;
        this.departureTime = departureTime;
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

    public Date getBoardingTime() {
        return boardingTime;
    }

    public Date getDepartureTime() {
        return departureTime;
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

    public void setBoardingTime(Date boardingTime) {
        this.boardingTime = boardingTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }
}
