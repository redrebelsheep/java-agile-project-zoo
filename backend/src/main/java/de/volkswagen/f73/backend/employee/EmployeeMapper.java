package de.volkswagen.f73.backend.employee;

import de.volkswagen.f73.backend.animal.Animal;
import de.volkswagen.f73.backend.animal.AnimalRepository;
import de.volkswagen.f73.backend.enclosure.Enclosure;
import de.volkswagen.f73.backend.enclosure.EnclosureRepository;
import de.volkswagen.f73.backend.stall.StallRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashSet;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class EmployeeMapper {
    public Employee convertDTOtoEmployee(EmployeeDTO employeeDTO, EnclosureRepository enclosureRepository, AnimalRepository animalRepository, StallRepository stallRepository) {
        Employee employee = Employee.builder()
                .name(employeeDTO.getName())
                .salary(employeeDTO.getSalary())
                .job(employeeDTO.getJob())
                .build();
        setEnClosuresForEmployee(employeeDTO, enclosureRepository, employee);
        setResponsibilityAnimalsForEmployee(employeeDTO, animalRepository, employee);
        setStallForEmployee(employeeDTO, stallRepository, employee);
        setIdForEmployee(employeeDTO, employee);
        return employee;
    }

    public EmployeeDTO convertEmployeeToDTO(Employee employee) {
        EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .Salary(employee.getSalary())
                .job(employee.getJob())
                .build();
        setStallForDTO(employee, employeeDTO);
        setAnimalsForDTO(employee, employeeDTO);
        setEnclosuresForDTO(employee, employeeDTO);
        return employeeDTO;
    }

    private void setStallForDTO(Employee employee, EmployeeDTO employeeDTO) {
        if (employee.getStall() != null) {
            employeeDTO.setStall(employee.getStall()
                    .getId());
        }
    }

    private void setAnimalsForDTO(Employee employee, EmployeeDTO employeeDTO) {
        if (employee.getResponsibilityAnimals() != null && !employee.getResponsibilityAnimals()
                .isEmpty()) {
            employeeDTO.setResponsibilityAnimals(employee.getResponsibilityAnimals()
                    .stream()
                    .map(Animal::getId)
                    .collect(Collectors.toSet()));
        }
    }

    private void setEnclosuresForDTO(Employee employee, EmployeeDTO employeeDTO) {
        if (employee.getEnclosures() != null && !employee.getEnclosures()
                .isEmpty()) {
            employeeDTO.setEnclosures(employee.getEnclosures()
                    .stream()
                    .map(Enclosure::getId)
                    .collect(Collectors.toSet()));
        }
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
            employee.setResponsibilityAnimals(
                    new HashSet<>(animalRepository.findAllById(employeeDTO.getResponsibilityAnimals())));
        }
    }

    private void setEnClosuresForEmployee(EmployeeDTO employeeDTO, EnclosureRepository enclosureRepository, Employee employee) {
        if (employeeDTO.getEnclosures() != null && !employeeDTO.getEnclosures().isEmpty()) {
            employee.setEnclosures(new HashSet<>(enclosureRepository.findAllById(employeeDTO.getEnclosures())));
        }
    }

}
