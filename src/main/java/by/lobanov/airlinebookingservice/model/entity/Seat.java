package by.lobanov.airlinebookingservice.model.entity;

import by.lobanov.airlinebookingservice.model.constant.*;
import by.lobanov.airlinebookingservice.model.embeddable.*;
import jakarta.persistence.*;
import lombok.*;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "fare_conditions", length = 10)
    private FareCondition fareConditions;
}