package de.volkswagen.f73.backend.stall;

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
@WebMvcTest(StallController.class)
class StallControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StallService stallService;

    @Autowired
    private ObjectMapper mapper;

    private Stall validStall;
    private String jsonObject;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        validStall = Stall.builder().operatingCost(new BigDecimal("22.22")).type(StallType.DRINK).build();
        jsonObject = mapper.writeValueAsString(validStall);
    }
//
//    @Test
//    void getStallTest_returnAllStalls200() throws Exception {
//        // Arrange
//        jsonObject = jsonObject = mapper.writeValueAsString(List.of(validStall));
//        when(stallService.getAllStalls()).thenReturn(List.of(validStall));
//        // Act
//        mockMvc.perform(get("/zoo/stalls"))
//                // Assert
//                .andExpect(status().isOk())
//                .andExpect(content().json(jsonObject));
//    }

//    @Test
//    void getStallTest_returnNoContent204() throws Exception {
//        // Arrange
//        List<Stall> tmpList = new ArrayList<>();
//        when(stallService.getAllStalls()).thenReturn(tmpList);
//        // Act
//        mockMvc.perform(get("/zoo/stalls"))
//                // Assert
//                .andExpect(status().isNoContent());
//    }
//
//    @Test
//    void getStallByIdTest_returnStall200() throws Exception {
//        //Arrange
//        Stall inputStall = validStall;
//        String expected = mapper.writeValueAsString(inputStall);
//        when(stallService.getStallById(anyLong())).thenReturn(Optional.ofNullable(inputStall));
//        // Act
//        mockMvc.perform(get("/zoo/stall/1"))
//                // Assert
//                .andExpect(status().isOk())
//                .andExpect(content().json(expected));
//    }

    @Test
    void getStallByIdTest_returnNoContent204() throws Exception {
        // Arrange
        when(stallService.getStallById(anyLong())).thenReturn(Optional.empty());
        // Act
        mockMvc.perform(get("/zoo/stall/1"))
                // Assert
                .andExpect(status().isNoContent());
    }

    @Test
    void postAddStallTest_returnStallIsCreated201() throws Exception {
        // Arrange
        when(stallService.addStall(any(StallDTO.class))).thenReturn(Optional.ofNullable(validStall));
        // Act
        mockMvc.perform(post("/zoo/stall")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject))
                // Assert
                .andExpect(status().isCreated())
                .andExpect(content().json(jsonObject));
    }

    @Test
    void postAddStallTest_returnBadRequest400() throws Exception {
        // Arrange
        jsonObject = mapper.writeValueAsString(null);
        when(stallService.addStall(null)).thenThrow(HttpMessageNotReadableException.class);
        // Act
        mockMvc.perform(post("/zoo/stall")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject))
                // Assert
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof HttpMessageNotReadableException));
    }

    //TODO this test doest work with a exception
    @Test
    void postAddStallTest_invalidStall_missingParameter_returnBadRequest400() throws Exception {
        // Arrange
        Stall inValidStall = Stall.builder().build();
        String invalidJson = mapper.writeValueAsString(inValidStall);
        // Act
        mockMvc.perform(post("/zoo/stall")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                // Assert
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteStallTest_returnNoContent204() throws Exception {
        // Arrange
        when(stallService.deleteStall(anyLong())).thenReturn(true);
        // Act
        mockMvc.perform(delete("/zoo/stall/1"))
                // Assert
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteStallTest_ContentNoFound_returnIsNotFound404() throws Exception {
        // Arrange
        when(stallService.deleteStall(anyLong())).thenReturn(false);
        // Act
        mockMvc.perform(delete("/zoo/stall/1"))
                // Assert
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteStallTest_withNoParameter_returnNoContent204() throws Exception {
        // Act
        mockMvc.perform(delete("/zoo/stall"))
                // Assert
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    void putUpdateStallTest_IfExits_returnStallIsOk200() throws Exception {
        // Arrange
        validStall.setId(1L);
        jsonObject = mapper.writeValueAsString(validStall);
        when(stallService.isStallAlreadyExists(anyLong())).thenReturn(true);
        when(stallService.updateStall(any(StallDTO.class))).thenReturn(Optional.ofNullable(validStall));
        // Act
        mockMvc.perform(put("/zoo/stall")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject))
                // Assert
                .andExpect(status().isOk())
                .andExpect(content().json(jsonObject));
    }

    @Test
    void putUpdateStallTest_IfNotExists_returnStallIsCreated201() throws Exception {
        // Arrange
        jsonObject = mapper.writeValueAsString(validStall);
        when(stallService.updateStall(any(StallDTO.class))).thenReturn(Optional.ofNullable(validStall));
        when(stallService.isStallAlreadyExists(anyLong())).thenReturn(false);
        // Act
        mockMvc.perform(put("/zoo/stall")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject))
                // Assert
                .andExpect(status().isCreated())
                .andExpect(content().json(jsonObject));
    }

    @Test
    void putUpdateStallTest_IfStallExists_returnStatusConflict() throws Exception {
        // Arrange
        validStall.setId(1L);
        jsonObject = mapper.writeValueAsString(validStall);
        when(stallService.isStallAlreadyExists(anyLong())).thenReturn(false);
        when(stallService.updateStall(any(StallDTO.class))).thenReturn(Optional.empty());
        // Act
        mockMvc.perform(put("/zoo/stall")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject))
                // Assert
                .andExpect(status().isConflict());
    }


}