package week8.tickets;

import java.time.Clock;

public class TimeFrameTicketMachine extends TicketMachine {

    public TimeFrameTicketMachine(DiscountCalculator discountCalculator, int price, Clock clock) {
        super(discountCalculator, price, clock);
    }


}
