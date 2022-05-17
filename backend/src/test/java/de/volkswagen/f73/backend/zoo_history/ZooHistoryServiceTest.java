package de.volkswagen.f73.backend.zoo_history;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ZooHistoryServiceTest {

    private ZooHistoryService service;

    @Mock
    private ZooHistoryRepository repository;

    private ZooHistory validZooHistory;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        service = new ZooHistoryService(repository);
        validZooHistory = ZooHistory.builder()
                .date(LocalDate.now())
                .salesPerDay(new BigDecimal("2"))
                .visitorsPerDay(1112L)
                .staffCount(1)
                .build();
    }

    @Test
    void getFullHistory_returnsFullZooHistoryAsList() {
        // Arrange
        List<ZooHistory> fullZooHistory =  List.of(validZooHistory);
        when(repository.findAll()).thenReturn(fullZooHistory);
        // Act
        List<ZooHistory> responseList = service.getFullHistory();
        // Assert
        assertEquals(fullZooHistory,responseList);
    }

    @Test
    void getFullHistory_whenNotZooHistoryAdded_returnsEmptyList() {
        // Arrange
        List<ZooHistory> fullZooHistory =  List.of();
        when(repository.findAll()).thenReturn(fullZooHistory);
        // Act
        List<ZooHistory> responseList = service.getFullHistory();
        // Assert
        assertTrue(responseList.isEmpty());
    }

    @Test
    void addZooHistory_whenZooHistoryObjectIsValidAndNotExist_thenReturnsListOfZooHistory(){
        // Arrange
        validZooHistory.setId(1L);

        when(repository.save(any(ZooHistory.class))).thenReturn(validZooHistory);
        // Act
        Optional<ZooHistory> optionalZooHistoryObject  = service.addZooHistory(validZooHistory);

        // Assert
        assertTrue(optionalZooHistoryObject.isPresent());
        assertEquals(validZooHistory,optionalZooHistoryObject.get());
    }


//    @Test
//    void addZooHistory_whenEachZooHistoryObjectIsValidExist_thenReturnsEmptyList(){
//        // Arrange
//        List<ZooHistory> emptyZooHistoryList = List.of();
//        when(repository.save(any(ZooHistory.class))).thenReturn(null);
//        // Act
//        Optional<ZooHistory> optionalZooHistoryObject  = service.addZooHistory(validZooHistory);
//        // Assert
//        assertTrue(optionalZooHistoryObject.isEmpty());
//    }

    @Test
    void deleteAllHistory_whenDatabaseHasZooHistoryObjects_thenReturnsTrue() {
        // Arrange
        List<ZooHistory> fullZooHistory =  List.of(validZooHistory);
        when(repository.findAll()).thenReturn(fullZooHistory);
        // Act
        boolean result = service.deleteAllHistory();
        // Assert
        assertTrue(result);
    }

    @Test
    void deleteAllHistory_whenDatabaseIsEmpty_thenReturnsFalse() {
        // Arrange
        List<ZooHistory> fullZooHistory =  List.of();
        when(repository.findAll()).thenReturn(fullZooHistory);
        // Act
        boolean result = service.deleteAllHistory();
        // Assert
        assertFalse(result);
    }




}