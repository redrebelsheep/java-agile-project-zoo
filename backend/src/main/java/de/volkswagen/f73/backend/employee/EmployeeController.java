package de.volkswagen.f73.backend.employee;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * The type Employee controller.
 */
@RestController
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService service;

    /**
     * Gets all employee.
     *
     * @return the all employee
     */
    @GetMapping("/zoo/employees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee() {
        List<EmployeeDTO> allEmployees = service.getAllEmployees();
        return allEmployees.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(allEmployees);
    }

    /**
     * Gets all with job employee.
     *
     * @param job the job
     * @return the all with job employee
     */
    @GetMapping("/zoo/employees/{job}")
    public ResponseEntity<List<EmployeeDTO>> getAllWithJobEmployee(@PathVariable String job) {
        List<EmployeeDTO> allEmployees = service.getWithJobEmployees(job);
        return allEmployees.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(allEmployees);
    }

    /**
     * Gets employee by id.
     *
     * @param id the id
     * @return the employee by id
     */
    @GetMapping("/zoo/employee/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        Optional<EmployeeDTO> oneEmployee = service.getEmployeeById(id);
        return oneEmployee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    /**
     * Add employee response entity.
     *
     * @param employeeDTO the employeeDTO
     * @return the response entity
     */
    @PostMapping(value = "/zoo/employee")
    public ResponseEntity<Employee> addEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
        Optional<Employee> optionalEmployee = service.addEmployee(employeeDTO);
        return optionalEmployee.map(value
                -> ResponseEntity.created(URI.create("/zoo/employee/"
                + optionalEmployee.get().getId())).body(value)).orElseGet(()
                -> ResponseEntity.badRequest().build());
    }

    /**
     * Delete employee response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/zoo/employee/{id}")
    public ResponseEntity<Long> deleteEmployee(@PathVariable Long id) {
        return service.deleteEmployee(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }


    /**
     * Put employee response entity.
     *
     * @param employeeDTO the employeeDTO
     * @return the response entity
     */
    @PutMapping("/zoo/employee")
    public ResponseEntity<Employee> putEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
        if (employeeDTO.getId() != null && service.isEmployeeAlreadyExists(employeeDTO.getId())) {
            Optional<Employee> optionalEmployee = service.updateEmployee(employeeDTO);
            return ResponseEntity.ok(optionalEmployee.get());
        }
        Optional<Employee> optionalEmployee = service.updateEmployee(employeeDTO);
        return optionalEmployee.map(value
                -> ResponseEntity.created(URI.create("/zoo/employee/" + value.getId())).body(value)).orElseGet(()
                -> ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

}
