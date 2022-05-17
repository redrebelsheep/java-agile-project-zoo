package de.volkswagen.f73.model.enums;

/**
 * The enum "IncomeType"
 *
 * @Author Marcel Westphal, David Druecke, Marian Kowall
 */
public enum IncomeType {
    ALL("Gesammt"),
    ENTRANCE_FEE("Eintrittsgeld"),
    DONATES("Spenden"),
    SALES("Verkäufe");

    private final String name;

    IncomeType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
