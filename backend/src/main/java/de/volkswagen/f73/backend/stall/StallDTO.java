package de.volkswagen.f73.backend.stall;


import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class StallDTO implements Serializable {

    private Long id;

    private Long seller;

    @NotNull
    private BigDecimal operatingCost;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StallType type;

}