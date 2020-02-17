package be.iramps.florencemary.devsgbd.controller;

import be.iramps.florencemary.devsgbd.repository.PaiementRepository;
import be.iramps.florencemary.devsgbd.service.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/paiement", method = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@CrossOrigin(origins = {"http://localhost:4200"})
public class PaiementController {
    private final PaiementService service;

    public PaiementController(PaiementService service) {
        this.service = service;
    }

    @Autowired
    PaiementRepository repository;
}
