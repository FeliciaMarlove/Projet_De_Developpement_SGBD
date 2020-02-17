package be.iramps.florencemary.devsgbd.controller;

import be.iramps.florencemary.devsgbd.repository.FactureRepository;
import be.iramps.florencemary.devsgbd.service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/facture", method = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@CrossOrigin(origins = {"http://localhost:4200"})
public class FactureController {
    private final FactureService service;

    public FactureController(FactureService service) {
        this.service = service;
    }

    @Autowired
    FactureRepository repository;
}
