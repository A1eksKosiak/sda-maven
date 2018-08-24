package week8.tickets;

import static week8.tickets.PersonStatus.DISABLED;
import static week8.tickets.PersonStatus.STUDENT;
import static week8.tickets.PersonStatus.VIP;

public class DiscountCalculator {

    public int calculate(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("Given person is null");
        }
        if (person.getAge() < 0) {
            throw new IllegalArgumentException("Negative age is not allowed: " + person.getAge());
        }
        if (person.getAge() <= 6 || person.getStatus() == VIP) {
            return 100;
        }
        if (person.getAge() >= 70 || person.getStatus() == DISABLED) {
            return 90;
        }
        if ((person.getAge() > 6) && person.getAge() < 18) {
            return 75;
        }
        if (person.getStatus() == STUDENT &&
                person.getAge() >= 18 && person.getAge() <= 26) {
            return 50;
        }
        return 0;
    }
}
