package by.lobanov.airlinebookingservice.repository;

import by.lobanov.airlinebookingservice.model.entity.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
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
class BookingRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:12-alpine")
            .withDatabaseName("todo_test")
            .withUsername("test")
            .withPassword("test")
            .withReuse(true);

    @Autowired
    private BookingRepository bookingRepository;

    private Booking createTestBooking(String bookRef, BigDecimal amount) {
        Booking booking = new Booking();
        booking.setBookRef(bookRef);
        booking.setBookDate(OffsetDateTime.now());
        booking.setTotalAmount(amount);
        return booking;
    }

    @Test
    void shouldSaveAndFindBooking() {
        Booking booking = createTestBooking("REF001", new BigDecimal("100.00"));
        bookingRepository.save(booking);

        Booking found = bookingRepository.findById("REF001").orElse(null);

        assertThat(found).isNotNull();
        assertThat(found.getTotalAmount()).isEqualByComparingTo("100.00");
    }

    @Test
    void shouldFindAllBookings() {
        bookingRepository.save(createTestBooking("REF001", new BigDecimal("100.00")));
        bookingRepository.save(createTestBooking("REF002", new BigDecimal("200.00")));

        List<Booking> bookings = bookingRepository.findAll();

        assertThat(bookings).hasSize(2);
    }

    @Test
    void shouldDeleteBooking() {
        Booking booking = createTestBooking("REF001", new BigDecimal("100.00"));
        bookingRepository.save(booking);

        bookingRepository.deleteById("REF001");

        assertThat(bookingRepository.findById("REF001")).isEmpty();
    }
}