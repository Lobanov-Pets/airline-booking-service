package by.lobanov.airlinebookingservice.repository;

import by.lobanov.airlinebookingservice.model.embeddable.*;
import by.lobanov.airlinebookingservice.model.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface SeatRepository extends JpaRepository<Seat, SeatId> {
}
