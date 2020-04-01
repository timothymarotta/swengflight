import java.util.Collection;

public class Trip {
    Collection<Ticket> tickets;

    public Trip(Collection<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Collection<Ticket> getTickets(){
        return tickets;
    }

    //need to figure out how we are going to give all the boarding info back when
    //getBoardingInfo() is called, since that includes boarding time and gate number,
    //I think that a string would make sense

    public Collection<Ticket> getTickets(){
        return tickets;
    }
}
