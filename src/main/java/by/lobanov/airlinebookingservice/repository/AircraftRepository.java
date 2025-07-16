package by.lobanov.airlinebookingservice.repository;

import by.lobanov.airlinebookingservice.model.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft, String> {
    Optional<Aircraft> findByModel(String model);
}
