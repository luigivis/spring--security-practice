package org.example.springsecuritypractice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.example.springsecuritypractice.dto.request.ClientCreateDto;
import org.example.springsecuritypractice.entity.ClientEntity;
import org.example.springsecuritypractice.service.ClientServiceImpl;
import org.example.springsecuritypractice.utils.HttpResponse;
import org.example.springsecuritypractice.utils.PageableToolsImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

    private final ClientServiceImpl clientService;

    public ClientController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @PostMapping("")
    public HttpResponse<ClientEntity> create(@RequestBody @Valid ClientCreateDto clientCreateDto) throws JsonProcessingException {
        return this.clientService.createClient(clientCreateDto);
    }

    @GetMapping
    public HttpResponse<PageableToolsImpl.PaginationDto> getAll(@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "3") int size) {
        return this.clientService.findAllClient(PageRequest.of(page, size));
    }

}
