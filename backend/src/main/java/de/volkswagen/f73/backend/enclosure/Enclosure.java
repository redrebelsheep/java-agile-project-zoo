package de.volkswagen.f73.backend.enclosure;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import de.volkswagen.f73.backend.animal.Animal;
import de.volkswagen.f73.backend.employee.Employee;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The type Enclosure.
 */
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class Enclosure implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private BigDecimal maintenanceCosts;

    @ManyToMany
    @JoinTable(
            name = "employeeEnclosure",
            joinColumns = @JoinColumn(name = "enclosure_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    @Builder.Default
    private Set<Employee> staff = new HashSet<>();

    @OneToMany(mappedBy = "enclosure", cascade = CascadeType.DETACH )
    @Builder.Default
    private Set<Animal> animals = new HashSet<>();

}
