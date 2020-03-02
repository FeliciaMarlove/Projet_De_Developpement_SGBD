package be.iramps.florencemary.devsgbd.controller;

import be.iramps.florencemary.devsgbd.dto.ConnectionDto;
import be.iramps.florencemary.devsgbd.dto.ConnectionMessenger;
import be.iramps.florencemary.devsgbd.repository.UtilisateurRepository;
import be.iramps.florencemary.devsgbd.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/login")
@CrossOrigin(origins = {"http://localhost:4200"})
public class LoginController {
    private final UtilisateurService service;

    public LoginController(UtilisateurService service) { this.service = service; }

    @Autowired
    UtilisateurRepository repository;

    @PostMapping
    public ConnectionMessenger login(@RequestBody ConnectionDto connectionDto) {
        return service.connectUser(connectionDto.getLogin(), connectionDto.getMotDePasse());
    }
}
