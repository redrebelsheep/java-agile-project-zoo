package de.volkswagen.f73.backend.employee;


import de.volkswagen.f73.backend.animal.AnimalRepository;
import de.volkswagen.f73.backend.enclosure.EnclosureRepository;
import de.volkswagen.f73.backend.stall.StallRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/**
 * The type Employee service.
 */
@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private EnclosureRepository enclosureRepository;
    private AnimalRepository animalRepository;
    private StallRepository stallRepository;
    private EmployeeMapper employeeMapper;

    /**
     * Gets all employees.
     *
     * @return the all employees
     */
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    /**
     * Gets with job employees.
     *
     * @param job the job
     * @return the with job employees
     */
    public List<Employee> getWithJobEmployees(String job) {
        return employeeRepository.findByJob(Job.getTheRightEnum(job));
    }

    /**
     * Gets employee by id.
     *
     * @param id the id
     * @return the employee by id
     */
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    /**
     * Add employee optional.
     *
     * @param employeeDTO the employeeDTO
     * @return the optional
     */
    public Optional<Employee> addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.convertDTOtoEmployee(employeeDTO,
                enclosureRepository, animalRepository,  stallRepository );
        return Optional.of(employeeRepository.save(employee));
    }

    /**
     * Delete employee boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean deleteEmployee(Long id) {
        if (isEmployeeAlreadyExists(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Update employee optional.
     *
     * @param employeeDTO the employeeDTO
     * @return the optional
     */
    public Optional<Employee> updateEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.convertDTOtoEmployee(employeeDTO,
                enclosureRepository, animalRepository,  stallRepository );
        return Optional.of(employeeRepository.save(employee));
    }

    /**
     * Is employee already exists boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean isEmployeeAlreadyExists(Long id) {
        return employeeRepository.existsById(id);
    }


}
