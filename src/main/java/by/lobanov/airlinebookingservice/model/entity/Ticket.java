package by.lobanov.airlinebookingservice.model.entity;

import io.hypersistence.utils.hibernate.type.json.*;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @Column(name = "ticket_no", length = 13)
    private String ticketNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_ref", nullable = false)
    private Booking booking;

    @Column(name = "passenger_id", length = 20)
    private String passengerId;

    @Column(name = "passenger_name", columnDefinition = "TEXT")
    private String passengerName;

    @Type(JsonBinaryType.class)
    @Column(name = "contact_data", columnDefinition = "jsonb")
    private Map<String, String> contactData;
}