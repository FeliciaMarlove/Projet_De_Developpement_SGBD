package be.iramps.florencemary.devsgbd.controller;

import be.iramps.florencemary.devsgbd.repository.ClientRepository;
import be.iramps.florencemary.devsgbd.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/client", method = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@CrossOrigin(origins = {"http://localhost:4200"})
public class ClientController {
    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @Autowired
    ClientRepository repository;
}
