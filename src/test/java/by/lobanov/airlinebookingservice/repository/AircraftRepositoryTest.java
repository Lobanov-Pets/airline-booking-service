package by.lobanov.airlinebookingservice.repository;

import by.lobanov.airlinebookingservice.model.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.*;
import org.testcontainers.containers.*;
import org.testcontainers.junit.jupiter.*;
import org.testcontainers.junit.jupiter.Container;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
class AircraftRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:12-alpine")
            .withDatabaseName("todo_test")
            .withUsername("test")
            .withPassword("test")
            .withReuse(true);

    @Autowired
    private AircraftRepository aircraftRepository;

    private Aircraft createTestAircraft() {
        Aircraft aircraft = new Aircraft();
        aircraft.setAircraftCode("SU9");
        aircraft.setModel("Sukhoi Superjet 100");
        aircraft.setRange(3000);
        return aircraft;
    }

    @Test
    void shouldSaveAndFindAircraft() {
        Aircraft aircraft = createTestAircraft();
        aircraftRepository.save(aircraft);

        Aircraft foundAircraft = aircraftRepository.findById("SU9").orElse(null);

        assertThat(foundAircraft).isNotNull();
        assertThat(foundAircraft.getModel()).isEqualTo("Sukhoi Superjet 100");
    }

    @Test
    void shouldUpdateAircraft() {
        Aircraft aircraft = createTestAircraft();
        aircraftRepository.save(aircraft);

        Aircraft aircraftToUpdate = aircraftRepository.findById("SU9").orElseThrow();
        aircraftToUpdate.setRange(3200);
        aircraftRepository.save(aircraftToUpdate);

        Aircraft updatedAircraft = aircraftRepository.findById("SU9").orElseThrow();
        assertThat(updatedAircraft.getRange()).isEqualTo(3200);
    }

    @Test
    void shouldDeleteAircraft() {
        Aircraft aircraft = createTestAircraft();
        aircraftRepository.save(aircraft);

        aircraftRepository.deleteById("SU9");

        assertThat(aircraftRepository.findById("SU9")).isEmpty();
    }
}
