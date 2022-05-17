package de.volkswagen.f73.backend.zoo_account;

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
 * The type Zoo account.
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ZooAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private LocalDate date;
    @NotNull
    private Bookingtype bookingType;

    private String usageOfBooking;

    @NotNull
    private BigDecimal valueOfBooking;

    @NotNull
    private BigDecimal bankBalance;
}
