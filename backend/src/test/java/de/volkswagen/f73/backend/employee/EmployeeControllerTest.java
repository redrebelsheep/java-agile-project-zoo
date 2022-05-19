package de.volkswagen.f73.backend.employee;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.volkswagen.f73.backend.enclosure.Enclosure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper mapper;

    private Employee validEmployee;
    private String jsonObject;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        validEmployee = Employee.builder()
                .name("hallo")
                .job(Job.EMPLOYEE)
                .Salary(new BigDecimal(2)).build();
        jsonObject = mapper.writeValueAsString(validEmployee);
    }

//    @Test
//    void getAllEmployeeTest_returnAllEnclosures200() throws Exception {
//        // Arrange
//        jsonObject = jsonObject = mapper.writeValueAsString(List.of(validEmployee));
//        when(employeeService.getAllEmployees()).thenReturn(List.of(validEmployee));
//        // Act
//        mockMvc.perform(get("/zoo/employees"))
//                // Assert
//                .andExpect(status().isOk())
//                .andExpect(content().json(jsonObject));
//    }

//    @Test
//    void getAllEmployeeTest_returnNoContent204() throws Exception {
//        // Arrange
//        List<Employee> tmpList = new ArrayList<>();
//        when(employeeService.getAllEmployees()).thenReturn(tmpList);
//        // Act
//        mockMvc.perform(get("/zoo/employees"))
//                // Assert
//                .andExpect(status().isNoContent());
//    }

//    @Test
//    void getEmployeeByIdTest_returnAllEnclosures200() throws Exception {
//        //Arrange
//        when(employeeService.getEmployeeById(anyLong())).thenReturn(Optional.ofNullable(validEmployee));
//        // Act
//        mockMvc.perform(get("/zoo/employee/1"))
//                // Assert
//                .andExpect(status().isOk())
//                .andExpect(content().json(jsonObject));
//    }

    @Test
    void getEmployeeByIdTest_returnNoContent204() throws Exception {
        // Arrange
        when(employeeService.getEmployeeById(anyLong())).thenReturn(Optional.empty());
        // Act
        mockMvc.perform(get("/zoo/employee/1"))
                // Assert
                .andExpect(status().isNoContent());
    }

//    @Test
//    void getEmployeesByJobTest_whenUrlIncludeJob_returnAllEmployeesByJobAsList() throws Exception {
//        //Arrange
//        validEmployee.setJob(Job.DOCTOR);
//        List<Employee> result = List.of(validEmployee);
//        jsonObject = mapper.writeValueAsString(result);
//        when(employeeService.getWithJobEmployees("doctor")).thenReturn(result);
//        // Act
//        mockMvc.perform(get("/zoo/employees/doctor"))
//                // Assert
//                .andExpect(status().isOk())
//                .andExpect(content().json(jsonObject));
//    }

//    @Test
//    void getEmployeesByJobTest_whenUrlIncludeJobAndNotEmployeeWithThisJobExist_returnAllEmployeesByJobAsList() throws Exception {
//        //Arrange
//        List<Employee> result = new ArrayList<>();
//        jsonObject = mapper.writeValueAsString(result);
//        when(employeeService.getWithJobEmployees("doctor")).thenReturn(result);
//        // Act
//        mockMvc.perform(get("/zoo/employees/doctor"))
//                // Assert
//                .andExpect(status().isNoContent());
//    }


    @Test
    void postAddEmployeeTest_returnEnclosureIsCreated201() throws Exception {
        // Arrange
        when(employeeService.addEmployee(any(EmployeeDTO.class))).thenReturn(Optional.ofNullable(validEmployee));
        // Act
        mockMvc.perform(post("/zoo/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject))
                // Assert
                .andExpect(status().isCreated())
                .andExpect(content().json(jsonObject));
    }

    @Test
    void postAddEmployeeTest_returnBadRequest400() throws Exception {
        // Arrange
        jsonObject = mapper.writeValueAsString(null);
        when(employeeService.addEmployee(null)).thenThrow(HttpMessageNotReadableException.class);
        // Act
        mockMvc.perform(post("/zoo/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject))
                // Assert
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof HttpMessageNotReadableException));
    }

    //TODO this test doest work with a exception
    @Test
    void postAddEmployeeTest_invalidEmployee_missingParameter_returnBadRequest400() throws Exception {
        // Arrange
        Enclosure inValidEmployee = Enclosure.builder().build();
        String invalidJson = mapper.writeValueAsString(inValidEmployee);
        // Act
        mockMvc.perform(post("/zoo/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                // Assert
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteEmployeeTest_returnNoContent204() throws Exception {
        // Arrange
        when(employeeService.deleteEmployee(anyLong())).thenReturn(true);
        // Act
        mockMvc.perform(delete("/zoo/employee/1"))
                // Assert
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteEmployeeTest_ContentNoFound_returnIsNotFound404() throws Exception {
        // Arrange
        when(employeeService.deleteEmployee(anyLong())).thenReturn(false);
        // Act
        mockMvc.perform(delete("/zoo/employee/1"))
                // Assert
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteEmployeeTest_withNoParmeter_returnNoContent204() throws Exception {
        // Act
        mockMvc.perform(delete("/zoo/employee/"))
                // Assert
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    void putUpdateEmployeeTest_IfExits_returnEnclosureIsOk200() throws Exception {
        // Arrange
        validEmployee.setId(1L);
        jsonObject = mapper.writeValueAsString(validEmployee);
        when(employeeService.isEmployeeAlreadyExists(anyLong())).thenReturn(true);
        when(employeeService.updateEmployee(any(EmployeeDTO.class))).thenReturn(Optional.ofNullable(validEmployee));
        // Act
        mockMvc.perform(put("/zoo/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject))
                // Assert
                .andExpect(status().isOk())
                .andExpect(content().json(jsonObject));
    }
//
    @Test
    void putUpdateEmployeeTest_IfNotExists_returnEnclosureIsCreated201() throws Exception {
        // Arrange
        jsonObject = mapper.writeValueAsString(validEmployee);
        when(employeeService.updateEmployee(any(EmployeeDTO.class))).thenReturn(Optional.ofNullable(validEmployee));
        when(employeeService.isEmployeeAlreadyExists(anyLong())).thenReturn(false);
        // Act
        mockMvc.perform(put("/zoo/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject))
                // Assert
                .andExpect(status().isCreated())
                .andExpect(content().json(jsonObject));
    }


    //TODO: maybe later write test for empty object
//    @Test
//    void putUpdateEmployeeTest_IfEmployeeExists_returnStatusConflict() throws Exception {
//        // Arrange
//        validEmployee.setId(1L);
//        json = mapper.writeValueAsString(validEmployee);
//        when(service.isEmployeeAlreadyExists(anyLong())).thenReturn(false);
//        when(service.updateEmployee(any(Employee.class))).thenReturn(Optional.empty());
//        // Act
//        mockMvc.perform(put("/zoo/employee")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json))
//                // Assert
//                .andExpect(status().isConflict());
//    }






}