package de.volkswagen.f73.backend.enclosure;

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
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EnclosureController.class)
//@AutoConfigureMockMvc
class EnclosureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnclosureService enclosureService;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private EnclosureRepository repository;

    private Enclosure validEnclosure;
    private String jsonObject;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        validEnclosure = Enclosure.builder().name("Africa").maintenanceCosts(new BigDecimal("22.22")).build();
        jsonObject = mapper.writeValueAsString(validEnclosure);
    }

    @Test
    void getEnclosureTest_returnAllEnclosures200() throws Exception {
        // Arrange
        jsonObject = jsonObject = mapper.writeValueAsString(List.of(validEnclosure));
        when(enclosureService.getAllEnclosures()).thenReturn(List.of(validEnclosure));
        // Act
        mockMvc.perform(get("/zoo/enclosures"))
                // Assert
                .andExpect(status().isOk())
                .andExpect(content().json(jsonObject));
    }

    @Test
    void getEnclosureTest_returnNoContent204() throws Exception {
        // Arrange
        List<Enclosure> tmpList = new ArrayList<>();
        when(enclosureService.getAllEnclosures()).thenReturn(tmpList);
        // Act
        mockMvc.perform(get("/zoo/enclosures"))
                // Assert
                .andExpect(status().isNoContent());
    }

    @Test
    void getEnclosureByIdTest_returnAllEnclosures200() throws Exception {
        //Arrange
        Enclosure inputEnclosure = validEnclosure;
        String expected = mapper.writeValueAsString(inputEnclosure);
        when(enclosureService.getEnclosureById(anyLong())).thenReturn(Optional.ofNullable(inputEnclosure));
        // Act
        mockMvc.perform(get("/zoo/enclosure/1"))
                // Assert
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    void getEnclosureByIdTest_returnNoContent204() throws Exception {
        // Arrange
        when(enclosureService.getEnclosureById(anyLong())).thenReturn(Optional.empty());
        // Act
        mockMvc.perform(get("/zoo/enclosure/1"))
                // Assert
                .andExpect(status().isNoContent());
    }

    @Test
    void postAddEnclosureTest_returnEnclosureIsCreated201() throws Exception {
        // Arrange
        when(enclosureService.addEnclosure(any(EnclosureDTO.class))).thenReturn(Optional.ofNullable(validEnclosure));
        // Act
        mockMvc.perform(post("/zoo/enclosure")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject))
        // Assert
                .andExpect(status().isCreated())
                .andExpect(content().json(jsonObject));
    }

    @Test
    void postAddEnclosureTest_returnBadRequest400() throws Exception {
        // Arrange
        jsonObject = mapper.writeValueAsString(null);
        when(enclosureService.addEnclosure(null)).thenThrow(HttpMessageNotReadableException.class);
        // Act
        mockMvc.perform(post("/zoo/enclosure")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject))
                // Assert
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof HttpMessageNotReadableException));
    }


    //TODO this test doest work with a exception
    @Test
    void postAddEnclosureTest_invalidEnclosure_missingParameter_returnBadRequest400() throws Exception {
        // Arrange
        Enclosure inValidEnclosure = Enclosure.builder().build();
        String invalidJson = mapper.writeValueAsString(inValidEnclosure);
        // Act
        mockMvc.perform(post("/zoo/enclosure")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                // Assert
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteEnclosureTest_returnNoContent204() throws Exception {
        // Arrange
        when(enclosureService.deleteEnclosure(anyLong())).thenReturn(true);
        // Act
        mockMvc.perform(delete("/zoo/enclosure/1"))
                // Assert
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteEnclosureTest_ContentNoFound_returnIsNotFound404() throws Exception {
        // Arrange
        when(enclosureService.deleteEnclosure(anyLong())).thenReturn(false);
        // Act
        mockMvc.perform(delete("/zoo/enclosure/1"))
                // Assert
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteEnclosureTest_withNoParameter_returnNoContent204() throws Exception {
        // Act
        mockMvc.perform(delete("/zoo/enclosure/"))
                // Assert
                .andExpect(status().isMethodNotAllowed());
    }

   @Test
    void putUpdateEnclosureTest_IfExits_returnEnclosureIsOk200() throws Exception {
        // Arrange
        validEnclosure.setId(1L);
       jsonObject = mapper.writeValueAsString(validEnclosure);
        when(enclosureService.checkIfEnclosureExists(anyLong())).thenReturn(true);
        when(enclosureService.updateEnclosure(any(EnclosureDTO.class))).thenReturn(Optional.ofNullable(validEnclosure));
        // Act
        mockMvc.perform(put("/zoo/enclosure")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject))
                // Assert
                .andExpect(status().isOk())
                .andExpect(content().json(jsonObject));
    }

   @Test
    void putUpdateEnclosureTest_IfNotExists_returnEnclosureIsCreated201() throws Exception {
        // Arrange
        jsonObject = mapper.writeValueAsString(validEnclosure);
        when(enclosureService.updateEnclosure(any(EnclosureDTO.class))).thenReturn(Optional.ofNullable(validEnclosure));
        when(enclosureService.checkIfEnclosureExists(anyLong())).thenReturn(false);
        // Act
        mockMvc.perform(put("/zoo/enclosure")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject))
                // Assert
                .andExpect(status().isCreated())
                .andExpect(content().json(jsonObject));
    }

}