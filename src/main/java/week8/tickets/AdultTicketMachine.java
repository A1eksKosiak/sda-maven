package week8.tickets;

import java.time.Clock;
import java.time.LocalDateTime;

public class AdultTicketMachine extends TicketMachine {

    private Clock clock;
    private DiscountCalculator discountCalculator;
    private int price;

    public AdultTicketMachine(DiscountCalculator discountCalculator, int price, Clock clock) {
        this.discountCalculator = discountCalculator;
        this.price = price;
        this.clock = clock;
    }

    public Ticket buy(Person person) throws NoPersonDataException {
        if (person == null) {
            throw new NoPersonDataException("Sorry, no person data", LocalDateTime.now(clock));
        }
        if (discountCalculator == null) {
            return new Ticket(person, price);
        }
        int discountPercentage = discountCalculator.calculate(person);
        double discount = price * (discountPercentage / 100d);
        double discountedPrice = price - discount;
        return new Ticket(person, (int) Math.floor(discountedPrice));
    }
}
