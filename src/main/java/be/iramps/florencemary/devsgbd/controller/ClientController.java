package be.iramps.florencemary.devsgbd.controller;

import be.iramps.florencemary.devsgbd.dto.ClientDto;
import be.iramps.florencemary.devsgbd.model.Client;
import be.iramps.florencemary.devsgbd.repository.ClientRepository;
import be.iramps.florencemary.devsgbd.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/client")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ClientController {
    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @Autowired
    ClientRepository repository;

    @GetMapping
    public List<ClientDto> read() {
        return service.readActive();
    }

    @GetMapping("/{id}")
    public ClientDto readOne(@PathVariable("id") Long id) { return service.readOne(id); }

    @PostMapping
    public ClientDto create(@RequestBody ClientDto clientDto) { return service.create(clientDto); }

    @PutMapping("/{id}")
    public ClientDto update(@PathVariable("id") Long id, @RequestBody ClientDto clientDto) { return service.update(id, clientDto); }

    @DeleteMapping("/{id}")
    public ClientDto delete(@PathVariable("id") Long id) { return service.delete(id); }
}
