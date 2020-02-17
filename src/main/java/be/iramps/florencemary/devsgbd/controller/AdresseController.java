package be.iramps.florencemary.devsgbd.controller;

import be.iramps.florencemary.devsgbd.repository.AdresseRepository;
import be.iramps.florencemary.devsgbd.service.AdresseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/adresse", method = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@CrossOrigin(origins = {"http://localhost:4200"})
public class AdresseController {
    private final AdresseService service;

    public AdresseController(AdresseService service) {
        this.service = service;
    }

    @Autowired
    AdresseRepository repository;
}
