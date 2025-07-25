package by.lobanov.airlinebookingservice.repository;

import by.lobanov.airlinebookingservice.model.entity.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import org.springframework.boot.testcontainers.service.connection.*;
import org.testcontainers.containers.*;
import org.testcontainers.junit.jupiter.*;
import org.testcontainers.junit.jupiter.Container;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
class AirportRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:12-alpine")
            .withDatabaseName("todo_test")
            .withUsername("test")
            .withPassword("test")
            .withReuse(true);

    @Autowired
    private AirportRepository airportRepository;

    private Airport createTestAirport() {
        Airport airport = new Airport();
        airport.setAirportCode("DME");
        airport.setAirportName("Domodedovo");
        airport.setCity("Moscow");
        airport.setLongitude(37.906111);
        airport.setLatitude(55.408611);
        airport.setTimezone("Europe/Moscow");
        return airport;
    }

    @Test
    void shouldSaveAndFindAirport() {
        Airport airport = createTestAirport();
        airportRepository.save(airport);

        Airport foundAirport = airportRepository.findById("DME").orElse(null);

        assertThat(foundAirport).isNotNull();
        assertThat(foundAirport.getCity()).isEqualTo("Moscow");
    }

    @Test
    void shouldUpdateAirportCity() {
        Airport airport = createTestAirport();
        airportRepository.save(airport);

        Airport airportToUpdate = airportRepository.findById("DME").orElseThrow();
        airportToUpdate.setCity("Moskva");
        airportRepository.save(airportToUpdate);

        Airport updatedAirport = airportRepository.findById("DME").orElseThrow();
        assertThat(updatedAirport.getCity()).isEqualTo("Moskva");
    }

    @Test
    void shouldDeleteAirport() {
        Airport airport = createTestAirport();
        airportRepository.save(airport);

        airportRepository.delete(airport);

        assertThat(airportRepository.findById("DME")).isEmpty();
    }
}