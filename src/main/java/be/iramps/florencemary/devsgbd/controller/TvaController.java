package be.iramps.florencemary.devsgbd.controller;

import be.iramps.florencemary.devsgbd.repository.TvaRepository;
import be.iramps.florencemary.devsgbd.service.TvaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/tva", method = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@CrossOrigin(origins = {"http://localhost:4200"})
public class TvaController {
    private final TvaService service;

    public TvaController(TvaService service) {
        this.service = service;
    }

    @Autowired
    TvaRepository repository;
}
