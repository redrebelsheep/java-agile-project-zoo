package de.volkswagen.f73.backend.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import de.volkswagen.f73.backend.animal.AnimalMapper;
import de.volkswagen.f73.backend.employee.EmployeeMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalBeansConfiguration {

    @Bean
    ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    @Bean
    AnimalMapper animalMapper(){
        return new AnimalMapper();
    }

    @Bean
    EmployeeMapper EmployeeMapper(){
        return new EmployeeMapper();
    }

}
