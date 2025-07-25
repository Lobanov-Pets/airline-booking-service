package by.lobanov.airlinebookingservice.model.consant;

import lombok.*;

@Getter
public enum FareCondition {

    ECONOMY ("Economy"),
    COMFORT ("Comfort"),
    BUSINESS("Business");

    private final String label;

    FareCondition(String label) {
        this.label = label;
    }
}
