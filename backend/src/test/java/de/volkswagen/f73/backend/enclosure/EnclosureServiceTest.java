package de.volkswagen.f73.backend.enclosure;

import de.volkswagen.f73.backend.animal.AnimalRepository;
import de.volkswagen.f73.backend.employee.EmployeeDTO;
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
class EnclosureServiceTest {

    private EnclosureService enclosureService;

    @Mock
    private EnclosureRepository enclosureRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private AnimalRepository animalRepository;

    private Enclosure validEnclosure;
    private EnclosureDTO validEnclosureDTO;

    @BeforeEach
    void setUp() {
        enclosureService = new EnclosureService(enclosureRepository, employeeRepository, animalRepository);
        validEnclosure = Enclosure.builder().name("Africa").maintenanceCosts(new BigDecimal("22.22")).build();
        validEnclosureDTO = EnclosureDTO.builder().name(validEnclosure.getName()).maintenanceCosts(validEnclosure.getMaintenanceCosts()).build();
    }

    @Test
    void getAllEnclosures_returnsAllEnclosuresAsList() {
        // Arrange
        List<Enclosure> allEnclosures =  List.of(validEnclosure);
        when(enclosureRepository.findAll()).thenReturn(allEnclosures);
        // Act
        List<Enclosure> responseList = enclosureService.getAllEnclosures();
        // Assert
        assertEquals(allEnclosures,responseList);
    }

    @Test
    void getAllEnclosures_whenNotEnclosuresAdded_returnsEmptyList() {
        // Arrange
        List<Enclosure> allEnclosures =  List.of();
        when(enclosureRepository.findAll()).thenReturn(allEnclosures);
        // Act
        List<Enclosure> responseList = enclosureService.getAllEnclosures();
        // Assert
       assertTrue(responseList.isEmpty());
    }

    @Test
    void getEnclosureById_withID_returnsExitsEmployee() {
        // Arrange
        Enclosure expected = validEnclosure;
        when(enclosureRepository.findById(any())).thenReturn(Optional.ofNullable(expected));
        // Act
        Optional<Enclosure> optionalActual = enclosureService.getEnclosureById(1L);
        // Assert
        assertTrue(optionalActual.isPresent());
        assertEquals(expected,optionalActual.get());
    }

    @Test
    void getEnclosureById_withID_returnsOptionalEmpty() {
        // Arrange
        when(enclosureRepository.findById(any())).thenReturn(Optional.empty());
        // Act
        Optional<Enclosure> optionalActual = enclosureService.getEnclosureById(1L);
        // Assert
        assertTrue(optionalActual.isEmpty());
    }

    @Test
    void addEnclosure_whenEnclosureIsValidAndNotExist_thenReturnsOptionalOfEnclosures(){
        // Arrange
        validEnclosure.setId(1L);
        when(enclosureRepository.save(any(Enclosure.class))).thenReturn(validEnclosure);
        // Act
        Optional<Enclosure> result = enclosureService.addEnclosure(validEnclosureDTO);
        // Assert
        assertTrue(result.isPresent());
        assertEquals(validEnclosure,result.get());
    }

    @Test
    void deleteEnclosure_whenEnclosureIdIsValid_thenReturnsTrue() {
        // Arrange
        validEnclosure.setId(1L);
        when(enclosureRepository.existsById(anyLong())).thenReturn(true);
        // Act
        boolean result = enclosureService.deleteEnclosure(validEnclosure.getId());
        // Assert
        assertTrue(result);
    }

    @Test
    void deleteEnclosure_whenEnclosureIdIsNotValid_thenReturnsFalse() {
        // Arrange
        validEnclosure.setId(19L);
        when(enclosureRepository.existsById(anyLong())).thenReturn(false);
        // Act
        boolean result = enclosureService.deleteEnclosure(validEnclosure.getId());
        // Assert
        assertFalse(result);
    }

    @Test
    void updateEnclosure_whenEnclosureIsSave_returnOptionalOfEnclosure() {
        // Arrange
        when(enclosureRepository.save(any(Enclosure.class))).thenReturn(validEnclosure);
        // Act
        Optional<Enclosure> result = enclosureService.updateEnclosure(validEnclosureDTO);
        // Assert
        assertTrue(result.isPresent());
        assertEquals(result.get(),validEnclosure);
    }

    @Test
    void checkIfEnclosureExists_whenEnclosureAlreadyExists_returnTrue() {
        // Arrange
        validEnclosure.setId(1L);
        when(enclosureRepository.existsById(anyLong())).thenReturn(true);
        // Act
        boolean result = enclosureService.checkIfEnclosureExists(validEnclosure.getId());
        // Assert
        assertTrue(result);
    }

    @Test
    void checkIfEnclosureExists_whenEnclosureNotExists_returnFalse() {
        // Arrange
        validEnclosure.setId(1L);
        when(enclosureRepository.existsById(anyLong())).thenReturn(false);
        // Act
        boolean result = enclosureService.checkIfEnclosureExists(validEnclosure.getId());
        // Assert
        assertFalse(result);
    }

}