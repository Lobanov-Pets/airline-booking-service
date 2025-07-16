package by.lobanov.airlinebookingservice.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "airports")
public class Airport {

    @Id
    @Column(name = "airport_code", length = 3)
    private String airportCode;

    @Column(name = "airport_name")
    private String airportName;

    @Column(name = "city")
    private String city;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "timezone")
    private String timezone;

    @OneToMany(mappedBy = "departureAirport")
    private Set<Flight> departingFlights;

    @OneToMany(mappedBy = "arrivalAirport")
    private Set<Flight> arrivingFlights;

    public Airport(String airportCode, String airportName, String city, Double longitude, Double latitude, String timezone) {
        this.airportCode = airportCode;
        this.airportName = airportName;
        this.city = city;
        this.longitude = longitude;
        this.latitude = latitude;
        this.timezone = timezone;
    }
}