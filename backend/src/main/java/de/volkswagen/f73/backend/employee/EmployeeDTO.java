package de.volkswagen.f73.backend.employee;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Employee dto.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class EmployeeDTO implements Serializable {


    private Long id;
    @NotNull
    private String name;
    @NotNull
    private BigDecimal Salary;
    @NotNull
    private Job job;

    private Set<Long> enclosures = new HashSet<>();

    private Set<Long> responsibilityAnimals = new HashSet<>();

    private Long stall;

}
