package be.iramps.florencemary.devsgbd.controller;

import be.iramps.florencemary.devsgbd.dto.ConnectionDto;
import be.iramps.florencemary.devsgbd.dto.ConnectionMessenger;
import be.iramps.florencemary.devsgbd.repository.UtilisateurRepository;
import be.iramps.florencemary.devsgbd.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur REST du endpoint Login (/api/login)
 * CORS Access CrossOrigin localhost:4200
 */
@RestController
@RequestMapping(value = "/api/login")
@CrossOrigin(origins = {"http://localhost:4200"})
public class LoginController {
    private final UtilisateurService service;

    public LoginController(UtilisateurService service) { this.service = service; }

    @Autowired
    UtilisateurRepository repository;

    /**
     * Verifie les informations de connexion
     * @param connectionDto (ConnectionDto) : un DTO contenant le login et le mot de passe
     * @return ConnectionMessenger un objet retournant des informations quant a la reussite ou l'echec de la connexion
     */
    @PostMapping
    public ConnectionMessenger login(@RequestBody ConnectionDto connectionDto) {
        return service.connectUser(connectionDto.getLogin(), connectionDto.getMotDePasse());
    }
}
