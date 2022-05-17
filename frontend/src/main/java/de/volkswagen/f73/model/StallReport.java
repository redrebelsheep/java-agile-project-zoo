package de.volkswagen.f73.model;

import io.swagger.client.model.Stall;

import java.math.BigDecimal;

/**
 * The class "StallReport"
 *
 * @Author Marcel Westphal, David Druecke, Marian Kowall
 */
public class StallReport {

    private final Stall stall;
    private BigDecimal income;

    public StallReport(Stall stall, BigDecimal income) {
        this.stall = stall;
        this.income = income;
    }

    public Stall getStall() {
        return stall;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }
}
