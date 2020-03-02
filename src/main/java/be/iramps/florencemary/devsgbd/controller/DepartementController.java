package be.iramps.florencemary.devsgbd.controller;

import be.iramps.florencemary.devsgbd.repository.DepartementRepository;
import be.iramps.florencemary.devsgbd.service.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/departement")
@CrossOrigin(origins = {"http://localhost:4200"})
public class DepartementController {
    private final DepartementService service;

    public DepartementController(DepartementService service) {
        this.service = service;
    }

    @Autowired
    DepartementRepository repository;
}
