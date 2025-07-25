package by.lobanov.airlinebookingservice.model.entity;

import by.lobanov.airlinebookingservice.model.embeddable.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "boarding_passes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"flight_id", "boarding_no"}),
        @UniqueConstraint(columnNames = {"flight_id", "seat_no"})
})
public class BoardingPass {

    @EmbeddedId
    private TicketFlightId id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name="ticket_no", referencedColumnName="ticket_no")
    @JoinColumn(name="flight_id", referencedColumnName="flight_id")
    private TicketFlight ticketFlight;

    @Column(name = "boarding_no")
    private Integer boardingNo;

    @Column(name = "seat_no", length = 4)
    private String seatNo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BoardingPass that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}