package de.volkswagen.f73.backend.animal;

import de.volkswagen.f73.backend.employee.EmployeeRepository;
import de.volkswagen.f73.backend.enclosure.EnclosureRepository;
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
class AnimalServiceTest {

    private AnimalService service;

    @Mock
    private AnimalRepository animalRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EnclosureRepository enclosureRepository;

    @Mock
    private AnimalMapper animalMapper;

    private Animal validAnimal;
    private AnimalDTO validAnimalDTO;

    @BeforeEach
    void setUp() {
       service = new AnimalService(animalRepository, employeeRepository, enclosureRepository, animalMapper);
        validAnimal = Animal.builder().name("Marta").species("Einhorn").subsistenceCosts(new BigDecimal("22.22")).build();
        validAnimalDTO = AnimalDTO.builder().name(validAnimal.getName()).species(validAnimal.getSpecies()).subsistenceCosts(validAnimal.getSubsistenceCosts()).build();
    }

//    @Test
//    void getAllAnimals_returnsAllAnimalsAsList() {
//        // Arrange
//        List<Animal> expected =  List.of(validAnimal);
//        when(animalRepository.findAll()).thenReturn(expected);
//        // Act
//        List<Animal> actual = service.getAllAnimals();
//        // Assert
//        assertEquals(expected,actual);
//    }

//    @Test
//    void getAllAnimals_whenNoAnimalExist_returnsEmptyList() {
//        // Arrange
//        List<Animal> expected =  List.of();
//        when(animalRepository.findAll()).thenReturn(expected);
//        // Act
//        List<Animal> actual = service.getAllAnimals();
//        // Assert
//        assertTrue(actual.isEmpty());
//    }


//    @Test
//    void getAnimalById_withID_returnsOptionalEmpty() {
//        // Arrange
//        when(animalRepository.findById(any())).thenReturn(Optional.empty());
//        // Act
//        Optional<Animal> optionalActual = service.getAnimalById(1L);
//        // Assert
//        assertTrue(optionalActual.isEmpty());
//    }

    @Test
    void addAnimal_whenAnimalIsValidAndNotExist_thenReturnsOptionalOfAnimal(){
        // Arrange
        validAnimal.setId(1L);
        Animal expected = validAnimal;
        when(animalRepository.save(any(Animal.class))).thenReturn(expected);
        // Act
        Optional<Animal> actual = service.addAnimal(validAnimalDTO);
        // Assert
        assertTrue(actual.isPresent());
        assertEquals(expected,actual.get());
    }

    @Test
    void deleteAnimal_whenAnimalIdIsValid_thenReturnsTrue() {
        // Arrange
        validAnimal.setId(1L);
        Animal expected = validAnimal;
        when(animalRepository.existsById(anyLong())).thenReturn(true);
        // Act
        boolean result = service.deleteAnimal(expected.getId());
        // Assert
        assertTrue(result);
    }

    @Test
    void deleteAnimal_whenAnimalIdIsNotValid_thenReturnsFalse() {
        // Arrange
        validAnimal.setId(19L);
        Animal expected = validAnimal;
        when(animalRepository.existsById(anyLong())).thenReturn(false);
        // Act
        boolean result = service.deleteAnimal(expected.getId());
        // Assert
        assertFalse(result);
    }

    @Test
    void updateAnimal_whenAnimalIsSave_returnOptionalOfAnimal() {
        // Arrange
        Animal expected = validAnimal;
        when(animalRepository.save(any(Animal.class))).thenReturn(expected);
        // Act
        Optional<Animal> actual = service.updateAnimal(validAnimalDTO);
        // Assert
        assertTrue(actual.isPresent());
        assertEquals(actual.get(),expected);
    }

    @Test
    void isAnimalAlreadyExists_whenAnimalAlreadyExists_returnTrue() {
        // Arrange
        validAnimal.setId(1L);
        Animal expected = validAnimal;
        when(animalRepository.existsById(anyLong())).thenReturn(true);
        // Act
        boolean actual = service.isAnimalAlreadyExists(expected.getId());
        // Assert
        assertTrue(actual);
    }

    @Test
    void isAnimalAlreadyExists_whenAnimalNotExists_returnFalse() {
        // Arrange
        validAnimal.setId(1L);
        Animal expected = validAnimal;
        when(animalRepository.existsById(anyLong())).thenReturn(false);
        // Act
        boolean actual = service.isAnimalAlreadyExists(expected.getId());
        // Assert
        assertFalse(actual);
    }

//    @Test
//    void convertDTOtoAnimal_thenReturnValidAnimal() {
//        // Arrange
//        Animal expected = validAnimal;
//        // Act
//        Animal actual = service.convertDTOtoAnimal(validAnimalDTO);
//        // Assert
//        assertNotNull(actual);
//        assertEquals(expected.getName(),actual.getName());
//        assertEquals(expected.toString(),actual.toString());
//    }

}