package week8.tickets;

import java.time.Clock;

public class AdultTicketMachine extends TicketMachine {

    public AdultTicketMachine(DiscountCalculator discountCalculator, int price, Clock clock) {
        super(discountCalculator, price, clock);
    }

}
