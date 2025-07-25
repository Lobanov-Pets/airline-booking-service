package by.lobanov.airlinebookingservice.repository;

import by.lobanov.airlinebookingservice.model.entity.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import org.springframework.boot.testcontainers.service.connection.*;
import org.testcontainers.containers.*;
import org.testcontainers.junit.jupiter.*;
import org.testcontainers.junit.jupiter.Container;

import java.time.*;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
class FlightRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:12-alpine")
            .withDatabaseName("todo_test")
            .withUsername("test")
            .withPassword("test")
            .withReuse(true);

    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private TestEntityManager entityManager;

    private Aircraft aircraft;
    private Airport airportDME;
    private Airport airportLED;

    @BeforeEach
    void setUp() {
        aircraft = new Aircraft("733", "Boeing 737-300", 4200);
        entityManager.persist(aircraft);

        airportDME = new Airport("DME", "Domodedovo", "Moscow", 37.9, 55.4, "Europe/Moscow");
        entityManager.persist(airportDME);

        airportLED = new Airport("LED", "Pulkovo", "St. Petersburg", 30.2, 59.8, "Europe/Moscow");
        entityManager.persist(airportLED);
    }

    private Flight createTestFlight() {
        Flight flight = new Flight();
        flight.setFlightNo("PG0404");
        flight.setAircraft(aircraft);
        flight.setDepartureAirport(airportDME);
        flight.setArrivalAirport(airportLED);
        flight.setScheduledDeparture(OffsetDateTime.now().plusDays(1));
        flight.setScheduledArrival(OffsetDateTime.now().plusDays(1).plusHours(2));
        return flight;
    }

    @Test
    void shouldSaveAndFindFlight() {
        Flight flight = createTestFlight();
        Flight savedFlight = flightRepository.save(flight);

        Flight found = flightRepository.findById(savedFlight.getFlightId()).orElse(null);

        assertThat(found).isNotNull();
        assertThat(found.getFlightNo()).isEqualTo("PG0404");
        assertThat(found.getArrivalAirport().getCity()).isEqualTo("St. Petersburg");
    }
}
