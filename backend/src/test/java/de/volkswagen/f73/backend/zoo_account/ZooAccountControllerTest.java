package de.volkswagen.f73.backend.zoo_account;

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

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ZooAccountController.class)
class ZooAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ZooAccountService service;

    @Autowired
    private ObjectMapper mapper;

    private ZooAccount validZooAccount;
    private String json;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        validZooAccount = ZooAccount.builder()
                .date(LocalDate.now())
                .bankBalance(new BigDecimal("2"))
                .bookingType(Bookingtype.EXPENDS)
                .valueOfBooking(new BigDecimal("1"))
                .build();
        json = mapper.writeValueAsString(validZooAccount);
    }

    @Test
    void getAllZooAccountsTest_returnAllHistoryIsOK200() throws Exception {
        json = mapper.writeValueAsString(List.of(validZooAccount));
        when(service.getAllEntries()).thenReturn(List.of(validZooAccount));
        // Act
        mockMvc.perform(get("/zoo/account"))
                // Assert
                .andExpect(status().isOk())
                .andExpect(content().json(json));
    }

    @Test
    void getAllZooAccountsTest_returnNoContent204() throws Exception {
        // Arrange
        List<ZooAccount> tmpList = new ArrayList<>();
        when(service.getAllEntries()).thenReturn(tmpList);
        // Act
        mockMvc.perform(get("/zoo/account"))
                // Assert
                .andExpect(status().isNoContent());
    }

    @Test
    void postZooAccountsTest_returnZooHistoryListIsCreated201() throws Exception {
        // Arrange
        when(service.addZooAccount(any(ZooAccount.class))).thenReturn(Optional.ofNullable(validZooAccount));
        // Act
        mockMvc.perform(post("/zoo/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                // Assert
                .andExpect(status().isCreated())
                .andExpect(content().json(json));
    }

    @Test
    void postEmptyListOfZooAccount_returnBadRequest400() throws Exception {
        // Arrange
        when(service.addZooAccount(any(ZooAccount.class))).thenReturn(Optional.empty());
        // Act
        mockMvc.perform(post("/zoo/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                // Assert
                .andExpect(status().isBadRequest());
    }
    @Test
    void deleteByIdZooAccountObject_DatabaseIsEmpty_NoContent_returnIsNoContent204() throws Exception {
        // Arrange
        when(service.deleteById(anyLong())).thenReturn(true);
        // Act
        mockMvc.perform(delete("/zoo/account/1"))
                // Assert
                .andExpect(status().isNoContent());
    }
    @Test
    void deleteAllZooAccount_DeleteAll_ContentNoFound_returnIsNotFound404() throws Exception {
        // Arrange
        when(service.deleteById(anyLong())).thenReturn(false);
        // Act
        mockMvc.perform(delete("/zoo/history/2"))
                // Assert
                .andExpect(status().isNotFound());
    }
}