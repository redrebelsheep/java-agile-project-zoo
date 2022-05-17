package de.volkswagen.f73.backend.animal;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import de.volkswagen.f73.backend.employee.Employee;
import de.volkswagen.f73.backend.enclosure.Enclosure;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The type Animal.
 */
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
public class Animal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String species;
    @NotNull
    private String name;
    @NotNull
    private BigDecimal subsistenceCosts;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonIdentityReference(alwaysAsId = true)
    private Enclosure enclosure;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonIdentityReference(alwaysAsId = true)
    private Employee vet;
}
