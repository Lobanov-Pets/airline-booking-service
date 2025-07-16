package by.lobanov.airlinebookingservice.repository;

import by.lobanov.airlinebookingservice.model.entity.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.jdbc.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import org.springframework.boot.testcontainers.service.connection.*;
import org.testcontainers.containers.*;
import org.testcontainers.junit.jupiter.*;
import org.testcontainers.junit.jupiter.Container;

import java.math.*;
import java.time.*;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TicketRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:12-alpine")
            .withDatabaseName("todo_test")
            .withUsername("test")
            .withPassword("test")
            .withReuse(true);

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private TestEntityManager entityManager;

    private Booking createAndPersistBooking(String bookRef) {
        Booking booking = new Booking();
        booking.setBookRef(bookRef);
        booking.setBookDate(OffsetDateTime.now());
        booking.setTotalAmount(BigDecimal.ZERO);
        return entityManager.persist(booking);
    }

    private Ticket createTestTicket(String ticketNo, Booking booking) {
        Ticket ticket = new Ticket();
        ticket.setTicketNo(ticketNo);
        ticket.setBooking(booking);
        ticket.setPassengerId("PASS123");
        ticket.setPassengerName("John Doe");
        return ticket;
    }

    @Test
    void shouldSaveAndFindTicket() {
        Booking booking = createAndPersistBooking("B001");
        Ticket ticket = createTestTicket("T001", booking);
        ticketRepository.save(ticket);

        Ticket found = ticketRepository.findById("T001").orElse(null);

        assertThat(found).isNotNull();
        assertThat(found.getPassengerName()).isEqualTo("John Doe");
        assertThat(found.getBooking().getBookRef()).isEqualTo("B001");
    }

    @Test
    void shouldFindTicketsByBookingRef() {
        Booking booking1 = createAndPersistBooking("B001");
        Booking booking2 = createAndPersistBooking("B002");
        ticketRepository.save(createTestTicket("T001", booking1));
        ticketRepository.save(createTestTicket("T002", booking1));
        ticketRepository.save(createTestTicket("T003", booking2));

        List<Ticket> tickets = ticketRepository.findByBookingBookRef("B001");

        assertThat(tickets).hasSize(2);
    }

    @Test
    void shouldUpdatePassengerName() {
        Booking booking = createAndPersistBooking("B001");
        Ticket ticket = createTestTicket("T001", booking);
        ticketRepository.save(ticket);

        Ticket toUpdate = ticketRepository.findById("T001").orElseThrow();
        toUpdate.setPassengerName("Jane Doe");
        ticketRepository.save(toUpdate);

        Ticket updated = ticketRepository.findById("T001").orElseThrow();
        assertThat(updated.getPassengerName()).isEqualTo("Jane Doe");
    }
}
