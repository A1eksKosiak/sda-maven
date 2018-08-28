package week8.tickets;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class TicketMachine {
    private List<Ticket> tickets;
    private Clock clock;
    private DiscountCalculator discountCalculator;
    private int price;

    public TicketMachine(DiscountCalculator discountCalculator, int price, Clock clock) {
        tickets = new ArrayList<>();
        this.discountCalculator = discountCalculator;
        this.price = price;
        this.clock = clock;
    }

    public List<Ticket> getTicketsSold() {
        return tickets;
    }

    public Ticket buy(Person person) throws NoPersonDataException {
        Ticket ticket;
        if (person == null) {
            throw new NoPersonDataException("Sorry, no person data", LocalDateTime.now(clock));
        }
        if (person.getAge() < 18) {
            throw new TooYoungException("Sorry, you're too young", LocalDateTime.now(clock));
        }
        if (discountCalculator == null) {
            ticket = new Ticket(person, price, LocalDateTime.now(clock));
            tickets.add(ticket);
            return ticket;
        }
        int discountPercentage = discountCalculator.calculate(person);
        double discount = price * (discountPercentage / 100d);
        double discountedPrice = price - discount;
        ticket = new Ticket(person, (int) Math.floor(discountedPrice),LocalDateTime.now(clock));
        tickets.add(ticket);
        return ticket;
    }

}
