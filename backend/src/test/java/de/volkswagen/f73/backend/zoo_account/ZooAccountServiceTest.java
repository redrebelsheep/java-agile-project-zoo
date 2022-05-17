package de.volkswagen.f73.backend.zoo_account;

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
class ZooAccountServiceTest {

    private ZooAccountService service;

    @Mock
    private ZooAccountRepository repository;

    private ZooAccount validZooAccount;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        service = new ZooAccountService(repository);
        validZooAccount = ZooAccount.builder()
                .date(LocalDate.now())
                .bankBalance(new BigDecimal("2"))
                .bookingType(Bookingtype.EXPENDS)
                .valueOfBooking(new BigDecimal("1"))
                .build();
    }

    @Test
    void getAllEntries_returnsAllAsList() {
        // Arrange
        List<ZooAccount> fullZooAccount =  List.of(validZooAccount);
        when(repository.findAll()).thenReturn(fullZooAccount);
        // Act
        List<ZooAccount> responseList = service.getAllEntries();
        // Assert
        assertEquals(fullZooAccount,responseList);
    }

    @Test
    void getAllEntries_whenNotZooHistoryAdded_returnsEmptyList() {
        // Arrange
        List<ZooAccount> fullZooHistory =  List.of();
        when(repository.findAll()).thenReturn(fullZooHistory);
        // Act
        List<ZooAccount> responseList = service.getAllEntries();
        // Assert
        assertTrue(responseList.isEmpty());
    }

    @Test
    void addZooAccountList_whenEachZooAccountsObjectIsValidAndNotExist_thenReturnsListOfZooAccount(){
        // Arrange
        validZooAccount.setId(1L);
        when(repository.save(any(ZooAccount.class))).thenReturn(validZooAccount);
        // Act
        Optional<ZooAccount> optionalResponse  = service.addZooAccount(validZooAccount);
        // Assert
        assertEquals(validZooAccount,optionalResponse.get());
    }

//    @Test
//    void addZooAccountList_whenEachZooHistoryObjectIsValidExist_thenReturnsEmpytList(){
//        when(repository.save(any(ZooAccount.class))).thenReturn(null);
//        // Act
//        Optional<ZooAccount> optionalResponse  = service.addZooAccount(validZooAccount);
//        // Assert
//        assertTrue(optionalResponse.isEmpty());
//    }

    @Test
    void deleteZooAccount_whenZooAccountIdIsValid_thenReturnsTrue() {
        // Arrange
        validZooAccount.setId(1L);
        when(repository.existsById(anyLong())).thenReturn(true);
        // Act
        boolean result = service.deleteById(validZooAccount.getId());
        // Assert
        assertTrue(result);
    }

    @Test
    void deleteZooAccount_whenEnclosureIdIsNotValid_thenReturnsFalse() {
        // Arrange
        validZooAccount.setId(19L);
        when(repository.existsById(anyLong())).thenReturn(false);
        // Act
        boolean result = service.deleteById(validZooAccount.getId());
        // Assert
        assertFalse(result);
    }


    @Test
    void checkIfZooAccountExists_whenZooAccountAlreadyExists_returnTrue() {
        // Arrange
        validZooAccount.setId(1L);
        when(repository.existsById(anyLong())).thenReturn(true);
        // Act
        boolean result = service.checkIfZooAccountExists(validZooAccount.getId());
        // Assert
        assertTrue(result);
    }

    @Test
    void checkIfZooAccountExists_whenZOoNotExists_returnFalse() {
        // Arrange
        validZooAccount.setId(1L);
        when(repository.existsById(anyLong())).thenReturn(false);
        // Act
        boolean result = service.checkIfZooAccountExists(validZooAccount.getId());
        // Assert
        assertFalse(result);
    }





}