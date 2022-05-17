package de.volkswagen.f73.backend.employee;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class JobTest {

    @ParameterizedTest
    @MethodSource("stringToJob")
    void getTheRightEnum_whenStringIsDoctor_returnsJobDoctorEnum(String inputJobString,Job expected ) {
        // Arrange
        // Act
        Job actual = Job.getTheRightEnum(inputJobString);
        // Assert
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> stringToJob() {
        return Stream.of(
                arguments("mitarbeiter", Job.EMPLOYEE),
                arguments("arzt", Job.DOCTOR),
                arguments("verkaeufer", Job.SELLER),
                arguments("tierpfleger", Job.KEEPER)
        );
    }

}