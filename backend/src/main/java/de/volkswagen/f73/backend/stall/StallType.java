package de.volkswagen.f73.backend.stall;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The enum Stall type.
 */
public enum StallType {

    /**
     * Food stall type.
     */
    @JsonProperty("Nahrung")
    FOOD("Nahrung"),

    /**
     * Drink stall type.
     */
    @JsonProperty("Getraenke")
    DRINK("Getraenke"),

    /**
     * Souvenir stall type.
     */
    @JsonProperty("Souvenir")
    SOUVENIR("Souvenir");

    private final String name;

    StallType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
