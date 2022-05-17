package de.volkswagen.f73.backend.employee;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import de.volkswagen.f73.backend.animal.Animal;
import de.volkswagen.f73.backend.enclosure.Enclosure;
import de.volkswagen.f73.backend.stall.Stall;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The type Employee.
 */
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private BigDecimal Salary;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Job job;

    @ManyToMany(mappedBy = "staff")
    @Builder.Default
    @Getter(AccessLevel.NONE)
    private Set<Enclosure> enclosures = new HashSet<>();

    @OneToMany(mappedBy = "vet", cascade = CascadeType.MERGE)
    @Builder.Default
    @Getter(AccessLevel.NONE)
    private Set<Animal> responsibilityAnimals = new HashSet<>();

    @OneToOne(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIdentityReference(alwaysAsId = true)
    private Stall stall;


    /**
     * Gets responsibility animals.
     *
     * @return the responsibility animals
     */
    public Set<Long> getResponsibilityAnimals() {
        return responsibilityAnimals.stream().map(Animal::getId).collect(Collectors.toSet());
    }

    /**
     * Gets enclosures.
     *
     * @return the enclosures
     */
    public Set<Long> getEnclosures() {
        return enclosures.stream().map(Enclosure::getId).collect(Collectors.toSet());
    }

}
