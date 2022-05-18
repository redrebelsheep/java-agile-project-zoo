package de.volkswagen.f73.backend.employee;

import de.volkswagen.f73.backend.animal.AnimalRepository;
import de.volkswagen.f73.backend.enclosure.EnclosureRepository;
import de.volkswagen.f73.backend.stall.StallRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashSet;

@AllArgsConstructor
@Getter
public class EmployeeMapper {
    public Employee convertDTOtoEmployee(EmployeeDTO employeeDTO, EnclosureRepository enclosureRepository, AnimalRepository animalRepository, StallRepository stallRepository ) {
        Employee employee = Employee.builder().name(employeeDTO.getName()).Salary(employeeDTO.getSalary()).job(employeeDTO.getJob()).build();
        setEnClosuresForEmployee(employeeDTO, enclosureRepository, employee);
        setResponsibilityAnimalsForEmployee(employeeDTO, animalRepository, employee);
        setStallForEmployee(employeeDTO, stallRepository, employee);
        setIdForEmployee(employeeDTO, employee);
        return employee;
    }

    private void setIdForEmployee(EmployeeDTO employeeDTO, Employee employee) {
        if (employeeDTO.getId() != null) {
            employee.setId(employeeDTO.getId());
        }
    }

    private void setStallForEmployee(EmployeeDTO employeeDTO, StallRepository stallRepository, Employee employee) {
        if (employeeDTO.getStall() != null) {
            employee.setStall(stallRepository.getById(employeeDTO.getStall()));
        }
    }

    private void setResponsibilityAnimalsForEmployee(EmployeeDTO employeeDTO, AnimalRepository animalRepository, Employee employee) {
        if (employeeDTO.getResponsibilityAnimals() != null && !employeeDTO.getResponsibilityAnimals().isEmpty()) {
            employee.setResponsibilityAnimals(new HashSet<>(animalRepository.findAllById(employeeDTO.getResponsibilityAnimals())));
        }
    }

    private void setEnClosuresForEmployee(EmployeeDTO employeeDTO, EnclosureRepository enclosureRepository, Employee employee) {
        if (employeeDTO.getEnclosures() != null && !employeeDTO.getEnclosures().isEmpty()) {
            employee.setEnclosures(new HashSet<>(enclosureRepository.findAllById(employeeDTO.getEnclosures())));
        }
    }

}
