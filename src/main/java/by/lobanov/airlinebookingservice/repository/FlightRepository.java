package by.lobanov.airlinebookingservice.repository;

import by.lobanov.airlinebookingservice.model.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.util.*;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    List<Flight> findByScheduledDepartureBetween(OffsetDateTime start, OffsetDateTime end);
}