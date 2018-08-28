package week8.tickets;

import java.time.LocalDateTime;
import java.util.Objects;

public class Ticket {
    private Person person;
    private int price;
    private LocalDateTime timestamp;

    public Ticket(Person person, int price) {
        this(person, price, LocalDateTime.now());
    }

    public Ticket(Person person, int price, LocalDateTime timestamp) {
        this.person = person;
        this.price = price;
        this.timestamp = timestamp;
    }

    public Person getPerson() {

        return person;
    }

    public int getPrice() {
        return price;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return price == ticket.price &&
                Objects.equals(person, ticket.person) &&
                Objects.equals(timestamp, ticket.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person, price, timestamp);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "person=" + person +

                ", price=" + price +
                ", timestamp=" + timestamp +
                '}';
    }
}