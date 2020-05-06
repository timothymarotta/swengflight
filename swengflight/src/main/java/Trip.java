import java.util.Collection;

public class Trip {
    Collection<Ticket> tickets;
    String name;

    public Trip(){}
    public Trip(Collection<Ticket> tickets) {
        this.tickets = tickets;
        name = "";
    }

    public Trip(Collection<Ticket> tickets, String name){
        this.tickets = tickets;
        this.name = name;
    }

    public void setTickets(Collection<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Collection<Ticket> getTickets(){
        return tickets;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void removeTicket(Ticket ticket) {
        if (tickets.contains(ticket)) {
            tickets.remove(ticket);
        } else throw new IllegalArgumentException("Ticket not in trip");
    }

    //need to figure out how we are going to give all the boarding info back when
    //getBoardingInfo() is called, since that includes boarding time and gate number,
    //I think that a string would make sense

}
