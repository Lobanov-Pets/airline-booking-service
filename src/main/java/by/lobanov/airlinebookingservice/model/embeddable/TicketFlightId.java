package by.lobanov.airlinebookingservice.model.embeddable;

import jakarta.persistence.*;
import lombok.*;

import java.io.*;
import java.util.*;

@Getter
@Setter
@Embeddable
public class TicketFlightId implements Serializable {

    @Column(name = "ticket_no", length = 13)
    private String ticketNo;

    @Column(name = "flight_id")
    private Integer flightId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketFlightId that = (TicketFlightId) o;
        return Objects.equals(ticketNo, that.ticketNo) &&
                Objects.equals(flightId, that.flightId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketNo, flightId);
    }
}
