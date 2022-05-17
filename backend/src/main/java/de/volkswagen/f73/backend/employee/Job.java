package de.volkswagen.f73.backend.employee;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The enum Job.
 */
public enum Job {

    /**
     * Employee job.
     */
    @JsonProperty("Mitarbeiter")
    EMPLOYEE("Mitarbeiter"),

    /**
     * Doctor job.
     */
    @JsonProperty("Arzt")
    DOCTOR("Arzt"),

    /**
     * Seller job.
     */
    @JsonProperty("Verkaeufer")
    SELLER("Verkaeufer"),

    /**
     * Keeper job.
     */
    @JsonProperty("Tierpfleger")
    KEEPER("Tierpfleger"),

    /**
     * None job.
     */
    @JsonProperty("")
    NONE("");

    private final String name;

    Job(String name) {
        this.name = name;
    }

    /**
     * Gets the right enum.
     *
     * @param job the job
     * @return the the right enum
     */
    public static Job getTheRightEnum(String job) {
        switch (job.toLowerCase()) {
            case "arzt":
                return Job.DOCTOR;
            case "verkaeufer":
                return Job.SELLER;
            case "tierpfleger":
                return Job.KEEPER;
            default:
                return Job.EMPLOYEE;
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
