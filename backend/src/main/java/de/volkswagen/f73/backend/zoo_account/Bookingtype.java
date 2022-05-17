package de.volkswagen.f73.backend.zoo_account;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The enum Bookingtype.
 */
public enum Bookingtype {

    /**
     * Income bookingtype.
     */
    @JsonProperty("Einkommen")
    INCOME("Einkommen"),

    /**
     * Expends bookingtype.
     */
    @JsonProperty("Ausgabe")
    EXPENDS("Ausgabe"),

    /**
     * Donation bookingtype.
     */
    @JsonProperty("Spende")
    DONATION("Spende");

    private final String name;

    Bookingtype(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
