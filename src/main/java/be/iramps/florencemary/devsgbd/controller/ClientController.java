package be.iramps.florencemary.devsgbd.controller;

import be.iramps.florencemary.devsgbd.dto.ClientDtoGet;
import be.iramps.florencemary.devsgbd.dto.ClientDtoPost;
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
    public List<ClientDtoGet> read() {
        return service.readActive();
    }

    @GetMapping("/{id}")
    public ClientDtoGet readOne(@PathVariable("id") Long id) { return service.readOne(id); }

    @PostMapping
    public ClientDtoPost create(@RequestBody ClientDtoPost clientDtoPost) { return service.create(clientDtoPost); }

    @PutMapping("/{id}")
    public ClientDtoPost update(@PathVariable("id") Long id, @RequestBody ClientDtoPost clientDtoPost) { return service.update(id, clientDtoPost); }

    @DeleteMapping("/{id}")
    public ClientDtoGet delete(@PathVariable("id") Long id) { return service.delete(id); }
}
