import java.util.Collection;

public class Trip {
    Collection<Ticket> tickets;

    public Trip(Collection<Ticket> tickets) {
        this.tickets = tickets;
    }

    //need to figure out how we are going to give all the boarding info back when
    //getBoardingInfo() is called, since that includes boarding time and gate number,
    //I think that a string would make sense
}
