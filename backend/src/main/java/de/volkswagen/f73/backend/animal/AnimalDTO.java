package de.volkswagen.f73.backend.animal;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The type Animal dto.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class AnimalDTO implements Serializable {

    private Long id;

    @NotNull
    private String species;
    @NotNull
    private String name;
    @NotNull
    private BigDecimal subsistenceCosts;

    private Long enclosure;

    private Long vet;
}
