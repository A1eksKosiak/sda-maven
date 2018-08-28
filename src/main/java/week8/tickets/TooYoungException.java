package week8.tickets;

import java.time.LocalDateTime;

public class TooYoungException extends RuntimeException {

    LocalDateTime timestamp;

    public TooYoungException(String message, LocalDateTime timestamp) {
        super(message);
        this.timestamp = timestamp;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

}
