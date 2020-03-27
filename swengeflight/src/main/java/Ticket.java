import java.util.Date;

public class Ticket {
    String name;
    String airline;
    String ticketNumber;
    Flight flight;
    Date date;

    public Ticket(String name, String airline, String ticketNumber, Flight flight, Date date) {
        this.name = name;
        this.airline = airline;
        this.ticketNumber = ticketNumber;
        this.flight = flight;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getAirline() {
        return airline;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public Flight getFlight() {
        return flight;
    }

    public Date getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
