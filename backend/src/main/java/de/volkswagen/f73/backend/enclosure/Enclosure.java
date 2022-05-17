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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
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
    @Getter(AccessLevel.NONE)
    private Set<Employee> staff = new HashSet<>();

    @OneToMany(mappedBy = "enclosure", cascade = CascadeType.DETACH )
    @Builder.Default
    @Getter(AccessLevel.NONE)
    private Set<Animal> animals = new HashSet<>();

    /**
     * Gets staff.
     *
     * @return the staff
     */
    public Set<Long> getStaff() {
        return staff.stream().map(Employee::getId).collect(Collectors.toSet());
    }

    /**
     * Gets animals.
     *
     * @return the animals
     */
    public Set<Long> getAnimals() {
        return animals.stream().map(Animal::getId).collect(Collectors.toSet());
    }
}
