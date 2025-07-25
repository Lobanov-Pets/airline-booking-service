package by.lobanov.airlinebookingservice.repository;

import by.lobanov.airlinebookingservice.model.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.math.*;
import java.util.*;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {
    List<Booking> findByTotalAmountGreaterThan(BigDecimal amount);
}