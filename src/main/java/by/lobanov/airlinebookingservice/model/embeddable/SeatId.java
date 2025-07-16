package by.lobanov.airlinebookingservice.model.embeddable;

import jakarta.persistence.*;
import lombok.*;

import java.io.*;
import java.util.*;

@Getter
@Setter
@Embeddable
public class SeatId implements Serializable {

    @Column(name = "aircraft_code", length = 3)
    private String aircraftCode;

    @Column(name = "seat_no", length = 4)
    private String seatNo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeatId seatId = (SeatId) o;
        return Objects.equals(aircraftCode, seatId.aircraftCode) &&
                Objects.equals(seatNo, seatId.seatNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aircraftCode, seatNo);
    }
}
