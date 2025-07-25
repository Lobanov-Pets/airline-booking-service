package by.lobanov.airlinebookingservice.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "aircrafts")
public class Aircraft {

    @Id
    @Column(name = "aircraft_code", length = 3)
    private String aircraftCode;

    @Column(name = "model", columnDefinition = "TEXT")
    private String model;

    @Min(1)
    @Column(name = "range")
    private Integer range;

    @OneToMany(mappedBy = "aircraft")
    private Set<Seat> seats;

    @OneToMany(mappedBy = "aircraft")
    private Set<Flight> flights;

    public Aircraft(String aircraftCode, String model, Integer range) {
        this.aircraftCode = aircraftCode;
        this.model = model;
        this.range = range;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Aircraft aircraft)) return false;
        return Objects.equals(aircraftCode, aircraft.aircraftCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aircraftCode);
    }

    @Override
    public String toString() {
        return "Aircraft{" +
                "aircraftCode='" + aircraftCode + '\'' +
                ", model='" + model + '\'' +
                ", range=" + range +
                '}';
    }
}
