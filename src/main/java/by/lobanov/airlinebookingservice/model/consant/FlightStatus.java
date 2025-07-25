package by.lobanov.airlinebookingservice.model.consant;

import lombok.*;

@Getter
public enum FlightStatus {

    SCHEDULED ("Scheduled"),
    ON_TIME ("On Time"),
    DELAYED ("Delayed"),
    DEPARTED ("Departed"),
    ARRIVED ("Arrived"),
    CANCELLED ("Cancelled");

    private final String label;

    FlightStatus(String label) {
        this.label = label;
    }
}
