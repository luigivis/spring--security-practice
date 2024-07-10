package org.example.springsecuritypractice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.springsecuritypractice.dto.request.ClientCreateDto;
import org.example.springsecuritypractice.dto.request.ClientEditDto;
import org.example.springsecuritypractice.entity.ClientEntity;
import org.example.springsecuritypractice.repository.ClientRepository;
import org.example.springsecuritypractice.utils.HttpResponse;
import org.example.springsecuritypractice.utils.PageableToolsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.example.springsecuritypractice.utils.PageableToolsImpl.pageableRepository;

@Slf4j
@Service
public class ClientServiceImpl {

    private final ObjectMapper objectMapper;

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, ObjectMapper objectMapper) {
        this.clientRepository = clientRepository;
        this.objectMapper = objectMapper;
    }

    public HttpResponse<ClientEntity> createClient(ClientCreateDto clientCreateDto) {
        var clientEntity = objectMapper.convertValue(clientCreateDto, ClientEntity.class);
        var result = clientRepository.save(clientEntity);
        return new HttpResponse<ClientEntity>(HttpStatus.CREATED, result);
    }

    public HttpResponse<PageableToolsImpl.PaginationDto> findAllClient(PageRequest pageRequest) {
        return pageableRepository(clientRepository.findAll(pageRequest));
    }

    public HttpResponse<ClientEntity> findById(Long id) {
        var clientEntity = clientRepository.findById(id);
        if (clientEntity.isEmpty()) return new HttpResponse<>(HttpStatus.NOT_FOUND);
        return new HttpResponse<>(clientEntity.get(), HttpStatus.OK);
    }

    public HttpResponse<Void> deleteById(Long id) {
        var clientEntity = clientRepository.findById(id);
        if (clientEntity.isEmpty()) return new HttpResponse<>(HttpStatus.NOT_FOUND);
        clientRepository.deleteById(id);
        return new HttpResponse<>(HttpStatus.OK);
    }

    public HttpResponse<ClientEntity> updateById(Long id, ClientEditDto clientEditDto) {
        if (clientRepository.findById(id).isEmpty()) return new HttpResponse<>(HttpStatus.NOT_FOUND);

        var clientEntityFromRequest = objectMapper.convertValue(clientEditDto, ClientEntity.class);
        clientEntityFromRequest.setId(id);

        var result = clientRepository.save(clientEntityFromRequest);
        return new HttpResponse<>(HttpStatus.OK, result);
    }

    public HttpResponse<List<ClientEntity>> searchByAllRow(String keyword) {
        var result = clientRepository.searchClientEntities(keyword);
        if (result.isEmpty()) return new HttpResponse<>(HttpStatus.NOT_FOUND);
        return new HttpResponse<>(HttpStatus.OK, result);
    }
}
