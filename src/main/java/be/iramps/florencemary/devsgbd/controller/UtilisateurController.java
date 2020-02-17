package be.iramps.florencemary.devsgbd.controller;

import be.iramps.florencemary.devsgbd.repository.UtilisateurRepository;
import be.iramps.florencemary.devsgbd.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/utilisateur", method = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@CrossOrigin(origins = {"http://localhost:4200"})
public class UtilisateurController {
    private final UtilisateurService service;

    public UtilisateurController(UtilisateurService service) {
        this.service = service;
    }

    @Autowired
    UtilisateurRepository repository;
}
