package by.lobanov.airlinebookingservice.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.*;
import java.time.*;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @Column(name = "book_ref", length = 6)
    private String bookRef;

    @Column(name = "book_date")
    private OffsetDateTime bookDate;

    @Column(name = "total_amount", precision = 10, scale = 2)
    private BigDecimal totalAmount;

    // Связь: одно бронирование может содержать много билетов
    @OneToMany(mappedBy = "booking")
    private Set<Ticket> tickets;

    // Геттеры и сеттеры
}