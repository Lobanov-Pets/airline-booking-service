package by.lobanov.airlinebookingservice.model.entity;

import by.lobanov.airlinebookingservice.model.consant.*;
import by.lobanov.airlinebookingservice.model.embeddable.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.*;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ticket_flights")
public class TicketFlight {

    @EmbeddedId
    private TicketFlightId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("flightId")
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ticketNo")
    @JoinColumn(name = "ticket_no")
    private Ticket ticket;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "fare_conditions", length = 10)
    private FareCondition fareConditions;

    @DecimalMin("0.00")
    @Column(name = "amount", precision = 10, scale = 2)
    private BigDecimal amount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TicketFlight that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
