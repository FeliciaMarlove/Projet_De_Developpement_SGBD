package be.iramps.florencemary.devsgbd.controller;

import be.iramps.florencemary.devsgbd.dto.UtilisateurDto;
import be.iramps.florencemary.devsgbd.model.Utilisateur;
import be.iramps.florencemary.devsgbd.repository.UtilisateurRepository;
import be.iramps.florencemary.devsgbd.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/utilisateur")
@CrossOrigin(origins = {"http://localhost:4200"})
public class UtilisateurController {
    private final UtilisateurService service;

    public UtilisateurController(UtilisateurService service) {
        this.service = service;
    }

    @Autowired
    UtilisateurRepository repository;

    @GetMapping
    public List<UtilisateurDto> read() {
        return service.readActive();
    }

    @GetMapping("/{id}")
    public UtilisateurDto readOne(@PathVariable("id") Long id) { return service.readOne(id); }

    @PostMapping
    public UtilisateurDto create(@RequestBody UtilisateurDto utilisateurDto) { return service.create(utilisateurDto);}

    @PutMapping("/{login}")
    public UtilisateurDto update(@PathVariable("login") String login, @RequestBody UtilisateurDto utilisateurDto) { return service.update(login, utilisateurDto); }

    @DeleteMapping("/{login}")
    public UtilisateurDto delete(@PathVariable("login") String login) { return service.delete(login); }
}
