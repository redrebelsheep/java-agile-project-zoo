package de.volkswagen.f73.backend.enclosure;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Enclosure dto.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class EnclosureDTO implements Serializable {

    private Long id;
    @NotNull
    private String name;
    @NotNull
    private BigDecimal maintenanceCosts;

    private Set<Long> staff = new HashSet<>();

    private Set<Long> animals = new HashSet<>();

}