package de.volkswagen.f73.model.enums;

/**
 * The enum "ExpanseType"
 *
 * @Author Marcel Westphal, David Druecke, Marian Kowall
 */
public enum ExpanseType {
    ALL("Gesammt"),
    ENCLOSURES("Gehege"),
    ANIMALS("Tiere"),
    EMPLOYEES("Mitarbeiter");

    private final String name;

    ExpanseType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
