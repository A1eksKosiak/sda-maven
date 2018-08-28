package week8.tickets;

import java.time.Clock;
import java.time.LocalDateTime;

public class AdultTicketMachine extends TicketMachine {

    public AdultTicketMachine(DiscountCalculator discountCalculator, int price, Clock clock) {
        super(discountCalculator, price, clock);
    }

    @Override
    public Ticket buy(Person person) throws NoPersonDataException {
        if (person == null) {
            throw new NoPersonDataException("Sorry, no person data", LocalDateTime.now(clock));
        }
        if (person.getAge() < 18) {
            throw new TooYoungException("Sorry, you're too young", LocalDateTime.now(clock));
        }
        return super.buy(person);
    }
}
