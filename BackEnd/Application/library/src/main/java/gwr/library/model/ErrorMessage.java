package gwr.library.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Data;


/**
 * To string.
 *
 * @return the java.lang. string
 */
@Data
public class ErrorMessage implements Serializable {
    

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new error message.
     *
     * @param status the status
     * @param localDateTime the date
     * @param message the message
     * @param description the description
     */
    public ErrorMessage(HttpStatus status, LocalDateTime localDateTime, String message, String description) {
        super();
        this.status = status;
        this.date = localDateTime;
        this.message = message;
        this.description = description;
    }

    /** The status. */
    private HttpStatus status;

    /** The date. */
    private LocalDateTime date;

    /** The message. */
    private String message;

    /** The detail. */
    private String description;
}
