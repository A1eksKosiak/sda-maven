package week8.tickets;

import java.time.LocalDateTime;

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
        if (!(o instanceof Ticket)) return false;

        Ticket ticket = (Ticket) o;

        if (price != ticket.price) return false;
        if (person != null ? !person.equals(ticket.person) : ticket.person != null) return false;
        return timestamp != null ? timestamp.equals(ticket.timestamp) : ticket.timestamp == null;
    }

    @Override
    public int hashCode() {
        int result = person != null ? person.hashCode() : 0;
        result = 31 * result + price;
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        return result;
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