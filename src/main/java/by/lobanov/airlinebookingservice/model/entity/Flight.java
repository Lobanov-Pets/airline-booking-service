package by.lobanov.airlinebookingservice.model.entity;

import by.lobanov.airlinebookingservice.model.consant.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.*;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "flights", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"flight_no", "scheduled_departure"})
})
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Если ID генерируется базой
    @Column(name = "flight_id")
    private Integer flightId;

    @Column(name = "flight_no", length = 6)
    private String flightNo;

    @Column(name = "scheduled_departure")
    private OffsetDateTime scheduledDeparture;

    @Column(name = "scheduled_arrival")
    private OffsetDateTime scheduledArrival;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departure_airport", nullable = false)
    private Airport departureAirport;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arrival_airport", nullable = false)
    private Airport arrivalAirport;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", length = 20)
    private FlightStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aircraft_code", nullable = false)
    private Aircraft aircraft;

    @Column(name = "actual_departure")
    private OffsetDateTime actualDeparture;

    @Column(name = "actual_arrival")
    private OffsetDateTime actualArrival;

    @OneToMany(mappedBy = "flight")
    private Set<TicketFlight> ticketFlights;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight flight)) return false;
        return Objects.equals(flightId, flight.flightId) && Objects.equals(flightNo, flight.flightNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightId, flightNo);
    }
}
