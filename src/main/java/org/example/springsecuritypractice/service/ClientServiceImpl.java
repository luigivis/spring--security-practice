package org.example.springsecuritypractice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.springsecuritypractice.dto.request.ClientCreateDto;
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

    public ClientServiceImpl(ClientRepository clientRepository, ObjectMapper objectMapper) {
        this.clientRepository = clientRepository;
        this.objectMapper = objectMapper;
    }

    public HttpResponse<ClientEntity> createClient(ClientCreateDto clientCreateDto){
        var clientEntity = objectMapper.convertValue(clientCreateDto, ClientEntity.class);
        var result = clientRepository.save(clientEntity);
        return new HttpResponse<ClientEntity>(HttpStatus.CREATED, result);
    }

    public HttpResponse<PageableToolsImpl.PaginationDto> findAllClient(PageRequest pageRequest){
            return pageableRepository(this.clientRepository.findAll(pageRequest));
    }
}
