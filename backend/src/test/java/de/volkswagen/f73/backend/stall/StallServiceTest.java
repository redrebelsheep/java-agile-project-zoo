package de.volkswagen.f73.backend.stall;

import de.volkswagen.f73.backend.employee.EmployeeRepository;
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
class StallServiceTest {

    private StallService stallService;

    @Mock
    private StallRepository stallRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    private Stall validStall;
    private StallDTO validStallDTO;

    @BeforeEach
    void setUp() {
        stallService = new StallService(stallRepository, employeeRepository);
        validStall = Stall.builder().operatingCost(new BigDecimal("22.22")).type(StallType.DRINK).build();
        validStallDTO = StallDTO.builder().operatingCost(validStall.getOperatingCost()).type(validStall.getType()).build();
    }

    @Test
    void getAllStall_returnsAllStallAsList() {
        // Arrange
        List<Stall> expected = List.of(validStall);
        when(stallRepository.findAll()).thenReturn(expected);
        // Act
        List<Stall> actual = stallService.getAllStalls();
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void getAllStalls_whenNotStallsAdded_returnsEmptyList() {
        // Arrange
        List<Stall> expected = List.of();
        when(stallRepository.findAll()).thenReturn(expected);
        // Act
        List<Stall> actual = stallService.getAllStalls();
        // Assert
        assertTrue(actual.isEmpty());
    }

    @Test
    void getStallById_withID_returnsExitsStall() {
        // Arrange
        Stall expected = validStall;
        when(stallRepository.findById(any())).thenReturn(Optional.ofNullable(expected));
        // Act
        Optional<Stall> optionalActual = stallService.getStallById(1L);
        // Assert
        assertTrue(optionalActual.isPresent());
        assertEquals(expected, optionalActual.get());
    }

    @Test
    void getStallById_withID_returnsOptionalEmpty() {
        // Arrange
        when(stallRepository.findById(any())).thenReturn(Optional.empty());
        // Act
        Optional<Stall> optionalActual = stallService.getStallById(1L);
        // Assert
        assertTrue(optionalActual.isEmpty());
    }

    @Test
    void addStall_whenStallIsValidAndNotExist_thenReturnsOptionalOfStall(){
        // Arrange
        validStall.setId(1L);
        Stall expected = validStall;
        when(stallRepository.save(any(Stall.class))).thenReturn(validStall);
        // Act
        Optional<Stall> actual = stallService.addStall(validStallDTO);
        // Assert
        assertTrue(actual.isPresent());
        assertEquals(expected,actual.get());
    }

    @Test
    void deleteStall_whenStallIdIsValid_thenReturnsTrue() {
        // Arrange
        validStall.setId(1L);
        when(stallRepository.existsById(anyLong())).thenReturn(true);
        // Act
        boolean result = stallService.deleteStall(validStall.getId());
        // Assert
        assertTrue(result);
    }

    @Test
    void deleteStall_whenStallIdIsNotValid_thenReturnsFalse() {
        // Arrange
        validStall.setId(19L);
        when(stallRepository.existsById(anyLong())).thenReturn(false);
        // Act
        boolean result = stallService.deleteStall(validStall.getId());
        // Assert
        assertFalse(result);
    }

    @Test
    void updateStall_whenStallIsSave_returnOptionalOfStall() {
        // Arrange
        Stall expected = validStall;
        when(stallRepository.save(any(Stall.class))).thenReturn(expected);
        // Act
        Optional<Stall> actual = stallService.updateStall(validStallDTO);
        // Assert
        assertTrue(actual.isPresent());
        assertEquals(actual.get(),expected);
    }

    @Test
    void checkIfStallExists_whenStallAlreadyExists_returnTrue() {
        // Arrange
        validStall.setId(1L);
        when(stallRepository.existsById(anyLong())).thenReturn(true);
        // Act
        boolean result = stallService.isStallAlreadyExists(validStall.getId());
        // Assert
        assertTrue(result);
    }

    @Test
    void checkIfStallExists_whenStallNotExists_returnFalse() {
        // Arrange
        validStall.setId(1L);
        when(stallRepository.existsById(anyLong())).thenReturn(false);
        // Act
        boolean result = stallService.isStallAlreadyExists(validStall.getId());
        // Assert
        assertFalse(result);
    }

}