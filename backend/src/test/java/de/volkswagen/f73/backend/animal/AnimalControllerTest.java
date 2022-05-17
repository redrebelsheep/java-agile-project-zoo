package de.volkswagen.f73.backend.animal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(AnimalController.class)
class AnimalControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AnimalService animalService;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private AnimalRepository repository;
    private Animal validAnimal;
    private String jsonObject;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        validAnimal = Animal.builder().name("Paul der Sonnenbär").species("Drache").subsistenceCosts(new BigDecimal("22")).build();
        jsonObject = mapper.writeValueAsString(validAnimal);
    }

    @Test
    void getAllAnimalsTest_returnAllAnimal200() throws Exception {
        // Arrange
        jsonObject = jsonObject = mapper.writeValueAsString(List.of(validAnimal));
        when(animalService.getAllAnimals()).thenReturn(List.of(validAnimal));
        // Act
        mockMvc.perform(get("/zoo/animals"))
                // Assert
                .andExpect(status().isOk()).andExpect(content().json(jsonObject));
    }

    @Test
    void getAllAnimalsTest_returnNoContent204() throws Exception {
        // Arrange
        List<Animal> emptyList = new ArrayList<>();
        when(animalService.getAllAnimals()).thenReturn(emptyList);
        // Act
        mockMvc.perform(get("/zoo/animals"))
                // Assert
                .andExpect(status().isNoContent());
    }

    @Test
    void getAnimalByIdTest_returnAllAnimal200() throws Exception {
        //Arrange
        Animal inputAnimal = validAnimal;
        String expected = mapper.writeValueAsString(inputAnimal);
        when(animalService.getAnimalById(anyLong())).thenReturn(Optional.ofNullable(inputAnimal));
        // Act
        mockMvc.perform(get("/zoo/animal/1"))
                // Assert
                .andExpect(status().isOk()).andExpect(content().json(expected));
    }

    @Test
    void getAnimalByIdTest_returnNoContent204() throws Exception {
        // Arrange
        when(animalService.getAnimalById(anyLong())).thenReturn(Optional.empty());
        // Act
        mockMvc.perform(get("/zoo/animal/1"))
                // Assert
                .andExpect(status().isNoContent());
    }

    @Test
    void postAnimalTest_returnAnimalIsCreated201() throws Exception {
        // Arrange
        when(animalService.addAnimal(any(AnimalDTO.class))).thenReturn(Optional.ofNullable(validAnimal));
        // Act
        mockMvc.perform(post("/zoo/animal").contentType(MediaType.APPLICATION_JSON).content(jsonObject))
                // Assert
                .andExpect(status().isCreated()).andExpect(content().json(jsonObject));
    }

    @Test
    void postAnimalTest_returnBadRequest400() throws Exception {
        // Arrange
        jsonObject = mapper.writeValueAsString(null);
        when(animalService.addAnimal(null)).thenThrow(HttpMessageNotReadableException.class);
        // Act
        mockMvc.perform(post("/zoo/animal").contentType(MediaType.APPLICATION_JSON).content(jsonObject))
                // Assert
                .andExpect(status().isBadRequest()).andExpect(result -> assertTrue(result.getResolvedException() instanceof HttpMessageNotReadableException));
    }

    //TODO this test doest work with a exception
    @Test
    void postAnimalTest_invalidAnimal_missingParameter_returnBadRequest400() throws Exception {
        // Arrange
        Animal inValidAnimal = Animal.builder().build();
        String invalidJson = mapper.writeValueAsString(inValidAnimal);
        System.out.println(invalidJson);

        // Act
        mockMvc.perform(post("/zoo/animal").contentType(MediaType.APPLICATION_JSON).content(invalidJson))
                // Assert
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteAnimalTest_returnNoContent204() throws Exception {
        // Arrange
        when(animalService.deleteAnimal(anyLong())).thenReturn(true);
        // Act
        mockMvc.perform(delete("/zoo/animal/1"))
                // Assert
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteAnimalTest_ContentNoFound_returnIsNotFound404() throws Exception {
        // Arrange
        when(animalService.deleteAnimal(anyLong())).thenReturn(false);
        // Act
        mockMvc.perform(delete("/zoo/animal/1"))
                // Assert
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteAnimalTest_withNoParameter_returnNoContent204() throws Exception {
        // Act
        mockMvc.perform(delete("/zoo/animal"))
                // Assert
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    void putUpdateAnimalTest_IfExits_returnAnimalIsOk200() throws Exception {
        // Arrange
        validAnimal.setId(1L);
        jsonObject = mapper.writeValueAsString(validAnimal);
        when(animalService.isAnimalAlreadyExists(anyLong())).thenReturn(true);
        when(animalService.updateAnimal(any(AnimalDTO.class))).thenReturn(Optional.ofNullable(validAnimal));
        // Act
        mockMvc.perform(put("/zoo/animal").contentType(MediaType.APPLICATION_JSON).content(jsonObject))
                // Assert
                .andExpect(status().isOk()).andExpect(content().json(jsonObject));
    }

    @Test
    void putUpdateAnimalTest_IfNotExists_returnAnimalIsCreated201() throws Exception {
        // Arrange
        jsonObject = mapper.writeValueAsString(validAnimal);
        when(animalService.updateAnimal(any(AnimalDTO.class))).thenReturn(Optional.ofNullable(validAnimal));
        when(animalService.isAnimalAlreadyExists(anyLong())).thenReturn(false);
        // Act
        mockMvc.perform(put("/zoo/animal").contentType(MediaType.APPLICATION_JSON).content(jsonObject))
                // Assert
                .andExpect(status().isCreated()).andExpect(content().json(jsonObject));
    }
}