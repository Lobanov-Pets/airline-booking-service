package by.lobanov.airlinebookingservice.model.entity;

import by.lobanov.airlinebookingservice.model.consant.*;
import by.lobanov.airlinebookingservice.model.embeddable.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "seats")
public class Seat {

    @EmbeddedId
    private SeatId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("aircraftCode")
    @JoinColumn(name = "aircraft_code")
    private Aircraft aircraft;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "fare_conditions", length = 10)
    private FareCondition fareConditions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Seat seat)) return false;
        return Objects.equals(id, seat.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}