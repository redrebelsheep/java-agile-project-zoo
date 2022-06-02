package de.volkswagen.f73.backend.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import de.volkswagen.f73.backend.animal.AnimalMapper;
import de.volkswagen.f73.backend.employee.EmployeeMapper;
import de.volkswagen.f73.backend.enclosure.Enclosure;
import de.volkswagen.f73.backend.enclosure.EnclosureMapper;
import de.volkswagen.f73.backend.stall.StallMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class GlobalBeansConfiguration {

    @Bean
    ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
          corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost:3001"));
//        corsConfiguration.setAllowedOrigins(Collections.singletonList("http://127.0.0.1:5500"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                                                          "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
                                                          "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
                                                          "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

    @Bean
    AnimalMapper animalMapper(){
        return new AnimalMapper();
    }

    @Bean
    EmployeeMapper EmployeeMapper(){
        return new EmployeeMapper();
    }

    @Bean
    EnclosureMapper EnclosureMapper(){
        return new EnclosureMapper();
    }

    @Bean
    StallMapper StallMapper(){
        return new StallMapper();
    }


}
