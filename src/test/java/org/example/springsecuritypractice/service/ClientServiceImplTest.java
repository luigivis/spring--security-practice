package org.example.springsecuritypractice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.springsecuritypractice.dto.request.ClientCreateDto;
import org.example.springsecuritypractice.entity.ClientEntity;
import org.example.springsecuritypractice.repository.ClientRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceImplTest {

    @InjectMocks
    private ClientServiceImpl clientService;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private ClientRepository clientRepository;

    @DisplayName("Create Client")
    @Test
    public void createClient() {
        var clientCreateDto = new ClientCreateDto("Luigi", "Vismara", "12345678", "luigi@gmail.com", "address");
        var clientEntity = new ClientEntity(1L, "Luigi", "Vismara", "Luigi Vismara", "12345678", "luigi@gmail.com", "address", true, LocalDate.now(), LocalDate.now());
        Mockito.when(objectMapper.convertValue(clientCreateDto, ClientEntity.class))
                .thenReturn(clientEntity);
        Mockito.when(clientRepository.save(any(ClientEntity.class))).thenReturn(clientEntity);

        var result = clientService.createClient(clientCreateDto);
        assertNotNull(result);
    }

    @DisplayName("List All Client")
    @Test
    public void listAllClient() {
        var clientEntity = new ClientEntity(1L, "Luigi", "Vismara", "Luigi Vismara", "12345678", "luigi@gmail.com", "address", true, LocalDate.now(), LocalDate.now());
        var pageRequest = PageRequest.of(0, 3);
        Mockito.when(clientRepository.findAll(pageRequest)).thenReturn(new PageImpl<>(Collections.singletonList(clientEntity)));

        var result = clientService.findAllClient(pageRequest);
        assertNotNull(result);
    }

    @DisplayName("Find By Id -> Exist")
    @Test
    public void findByIdExist() {
        var clientEntity = new ClientEntity(1L, "Luigi", "Vismara", "Luigi Vismara", "12345678", "luigi@gmail.com", "address", true, LocalDate.now(), LocalDate.now());

        Mockito.when(clientRepository.findById(1L)).thenReturn(Optional.of(clientEntity));
        var result = clientService.findById(1L);
        assertNotNull(result);
    }

    @DisplayName("Find By Id -> Null")
    @Test
    public void findByIdNull() {
        Mockito.when(clientRepository.findById(1L)).thenReturn(Optional.empty());
        var result = clientService.findById(1L);
        assertEquals(result.getStatusCode().value(), HttpStatus.NOT_FOUND.value());
    }

    @DisplayName("Delete By Id -> Exist")
    @Test
    public void deleteByIdExist() {
        var clientEntity = new ClientEntity(1L, "Luigi", "Vismara", "Luigi Vismara", "12345678", "luigi@gmail.com", "address", true, LocalDate.now(), LocalDate.now());
        Mockito.when(clientRepository.findById(1L)).thenReturn(Optional.of(clientEntity));
        Mockito.doNothing().when(clientRepository).deleteById(1L);
        var result = clientService.deleteById(1L);
        assertNotNull(result);
    }

    @DisplayName("Delete By Id -> Null")
    @Test
    public void deleteByIdNull() {
        Mockito.when(clientRepository.findById(1L)).thenReturn(Optional.empty());
        var result = clientService.deleteById(1L);
        assertEquals(result.getStatusCode().value(), HttpStatus.NOT_FOUND.value());
    }

    @DisplayName("Search -> Exist")
    @Test
    public void searchExist() {
        var clientEntity = new ClientEntity(1L, "Luigi", "Vismara", "Luigi Vismara", "12345678", "luigi@gmail.com", "address", true, LocalDate.now(), LocalDate.now());
        Mockito.when(clientRepository.searchClientEntities("something")).thenReturn(Collections.singletonList(clientEntity));
        var result = clientService.searchByAllRow("something");
        assertNotNull(result);
    }

    @DisplayName("Search -> Empty")
    @Test
    public void searchEmpty() {
       Mockito.when(clientRepository.searchClientEntities("something")).thenReturn(new ArrayList<ClientEntity>());
        var result = clientService.searchByAllRow("something");
        assertEquals(result.getStatusCode().value(), HttpStatus.NOT_FOUND.value());
    }

}