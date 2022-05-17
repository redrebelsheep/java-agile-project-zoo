package de.volkswagen.f73.backend.zoo_history;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ZooHistoryController.class)
class ZooHistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ZooHistoryService service;

    @Autowired
    private ObjectMapper mapper;

    private ZooHistory validZooHistory;
    private String json;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        validZooHistory = ZooHistory.builder()
                .date(LocalDate.now())
                .salesPerDay(new BigDecimal("2"))
                .visitorsPerDay(1112L)
                .staffCount(1)
                .build();
        json = mapper.writeValueAsString(validZooHistory);
    }

    @Test
    void getFullHistoryTest_returnAllHistory200() throws Exception {
        json = mapper.writeValueAsString(List.of(validZooHistory));
        when(service.getFullHistory()).thenReturn(List.of(validZooHistory));
        // Act
        mockMvc.perform(get("/zoo/history"))
                // Assert
                .andExpect(status().isOk())
                .andExpect(content().json(json));
    }

    @Test
    void getFullHistoryTest_returnNoContent204() throws Exception {
        // Arrange
        List<ZooHistory> tmpList = new ArrayList<>();
        when(service.getFullHistory()).thenReturn(tmpList);
        // Act
        mockMvc.perform(get("/zoo/history"))
                // Assert
                .andExpect(status().isNoContent());
    }

    @Test
    void postAHistorytTest_returnZooHistoryListIsCreated201() throws Exception {
        // Arrange
        when(service.addZooHistory(any(ZooHistory.class))).thenReturn(Optional.ofNullable(validZooHistory));
        // Act
        mockMvc.perform(post("/zoo/history")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                // Assert
                .andExpect(status().isCreated())
                .andExpect(content().json(json));
    }

    @Test
    void postEmptyZooHistory_returnBadRequest400() throws Exception {
        // Arrange
        when(service.addZooHistory(any(ZooHistory.class))).thenReturn(Optional.empty());
        // Act
        mockMvc.perform(post("/zoo/history")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                // Assert
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteAllZooHistory_DatabaseIsEmpty_NoContent_returnIsNoContent204() throws Exception {
        // Arrange
        when(service.deleteAllHistory()).thenReturn(true);
        // Act
        mockMvc.perform(delete("/zoo/history"))
                // Assert
                .andExpect(status().isNoContent());
    }
    @Test
    void deleteAllZooHistory_DeleteAll_ContentNoFound_returnIsNotFound404() throws Exception {
        // Arrange
        when(service.deleteAllHistory()).thenReturn(false);
        // Act
        mockMvc.perform(delete("/zoo/history"))
                // Assert
                .andExpect(status().isNotFound());
    }



}