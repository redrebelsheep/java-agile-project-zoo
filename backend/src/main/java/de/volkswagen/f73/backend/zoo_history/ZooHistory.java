package de.volkswagen.f73.backend.zoo_history;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * The type Zoo history.
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ZooHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private LocalDate date;
    @NotNull
    private BigDecimal salesPerDay;
    @NotNull
    private long visitorsPerDay;
    @NotNull
    private int staffCount;

}
