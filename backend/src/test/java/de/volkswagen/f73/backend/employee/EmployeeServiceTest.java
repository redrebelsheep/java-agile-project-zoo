package de.volkswagen.f73.backend.employee;

import de.volkswagen.f73.backend.animal.AnimalRepository;
import de.volkswagen.f73.backend.enclosure.EnclosureRepository;
import de.volkswagen.f73.backend.stall.StallRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EnclosureRepository enclosureRepository;

    @Mock
    private AnimalRepository animalRepository;

    @Mock
    private StallRepository stallRepository;


    private Employee validEmployee;
    private EmployeeDTO validEmployeeDTO;

//    @BeforeEach
//    void setUp() {
//        employeeService = new EmployeeService(employeeRepository, enclosureRepository, animalRepository, stallRepository);
//        validEmployee = Employee.builder().name("hallo").job(Job.EMPLOYEE).Salary(new BigDecimal(2)).build();
//        validEmployeeDTO = EmployeeDTO.builder().name(validEmployee.getName()).job(validEmployee.getJob()).Salary(validEmployee.getSalary()).build();
//    }
//
//    @Test
//    void getAllEmployees_returnsAllEmployeeAsList() {
//        // Arrange
//        List<Employee> allEmployees = List.of(validEmployee);
//        when(employeeRepository.findAll()).thenReturn(allEmployees);
//        // Act
//        List<Employee> responseList = employeeService.getAllEmployees();
//        // Assert
//        assertEquals(allEmployees, responseList);
//    }

//    @Test
//    void getAllEmployees_whenNotEmployeeAdded_returnsEmptyList() {
//        // Arrange
//        List<Employee> emptyEmployees = List.of();
//        when(employeeRepository.findAll()).thenReturn(emptyEmployees);
//        // Act
//        List<Employee> responseList = employeeService.getAllEmployees();
//        // Assert
//        assertTrue(responseList.isEmpty());
//    }

//    @Test
//    void getEmployeeById_withID_returnsExitsEmployee() {
//        // Arrange
//        when(employeeRepository.findById(any())).thenReturn(Optional.ofNullable(validEmployee));
//        // Act
//        Optional<Employee> optionalEmployee = employeeService.getEmployeeById(1L);
//        // Assert
//        assertTrue(optionalEmployee.isPresent());
//        assertEquals(validEmployee, optionalEmployee.get());
//    }

//    @Test
//    void getEmployeeById_withID_returnsOptionalEmpty() {
//        // Arrange
//        when(employeeRepository.findById(any())).thenReturn(Optional.empty());
//        // Act
//        Optional<Employee> optionalEmployee = employeeService.getEmployeeById(1L);
//        // Assert
//        assertTrue(optionalEmployee.isEmpty());
//    }
//
//    @Test
//    void getWithJobEmployees_whenArgumentAStringOfJob_returnsAListOfEmployeesWithTheArgumentJob() {
//        List<Employee> expected = List.of(validEmployee);
//        // Arrange
//        when(employeeRepository.findByJob(any(Job.class))).thenReturn(expected);
//        // Act
//        List<Employee> actual = employeeService.getWithJobEmployees("doctor");
//        // Assert
//        assertEquals(expected, actual);
//    }

//    @Test
//    void getWithJobEmployees_whenArgumentAStringOfJobAndNoEmployeeWithJobExist_returnsEmptyList() {
//        List<Employee> expected = List.of();
//        // Arrange
//        when(employeeRepository.findByJob(any(Job.class))).thenReturn(expected);
//        // Act
//        List<Employee> actual = employeeService.getWithJobEmployees("employee");
//        // Assert
//        assertEquals(expected, actual);
//    }

    @Test
    void addEmployee_whenEmployeeIsValidAndNotExist_thenReturnsOptionalOfEnclosures(){
        // Arrange
        validEmployee.setId(1L);
        when(employeeRepository.save(any(Employee.class))).thenReturn(validEmployee);
        // Act
        Optional<Employee> result = employeeService.addEmployee(validEmployeeDTO);
        // Assert
        assertTrue(result.isPresent());
        assertEquals(validEmployee,result.get());
    }

    @Test
    void deleteEmployee_whenEmployeeIdIsValid_thenReturnsTrue() {
        // Arrange
        validEmployee.setId(1L);
        when(employeeRepository.existsById(anyLong())).thenReturn(true);
        // Act
        boolean result = employeeService.deleteEmployee(validEmployee.getId());
        // Assert
        assertTrue(result);
    }

    @Test
    void deleteEmployee_whenEmployeeIdIsNotValid_thenReturnsFalse() {
        // Arrange
        validEmployee.setId(19L);
        when(employeeRepository.existsById(anyLong())).thenReturn(false);
        // Act
        boolean result = employeeService.deleteEmployee(validEmployee.getId());
        // Assert
        assertFalse(result);
    }

    @Test
    void updateEmployee_whenEmployeeIsSave_returnOptionalOfEnclosure() {
        // Arrange
        when(employeeRepository.save(any(Employee.class))).thenReturn(validEmployee);
        // Act
        Optional<Employee> result = employeeService.updateEmployee(validEmployeeDTO);
        // Assert
        assertTrue(result.isPresent());
        assertEquals(result.get(),validEmployee);
    }

    @Test
    void isEmployeeAlreadyExists_whenEmployeeAlreadyExists_returnTrue() {
        // Arrange
        validEmployee.setId(1L);
        when(employeeRepository.existsById(anyLong())).thenReturn(true);
        // Act
        boolean result = employeeService.isEmployeeAlreadyExists(validEmployee.getId());
        // Assert
        assertTrue(result);
    }

    @Test
    void isEmployeeAlreadyExists_whenEmployeeNotExists_returnFalse() {
        // Arrange
        validEmployee.setId(1L);
        when(employeeRepository.existsById(anyLong())).thenReturn(false);
        // Act
        boolean result = employeeService.isEmployeeAlreadyExists(validEmployee.getId());
        // Assert
        assertFalse(result);
    }


}