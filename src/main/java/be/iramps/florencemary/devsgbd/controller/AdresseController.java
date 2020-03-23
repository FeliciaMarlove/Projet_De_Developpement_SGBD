package be.iramps.florencemary.devsgbd.controller;

import be.iramps.florencemary.devsgbd.dto.AdresseDtoGet;
import be.iramps.florencemary.devsgbd.dto.AdresseDtoPost;
import be.iramps.florencemary.devsgbd.repository.AdresseRepository;
import be.iramps.florencemary.devsgbd.service.AdresseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/adresse")
@CrossOrigin(origins = {"http://localhost:4200"})
public class AdresseController {
    private final AdresseService service;

    public AdresseController(AdresseService service) {
        this.service = service;
    }

    @Autowired
    AdresseRepository repository;

    @GetMapping
    public List<AdresseDtoGet> read() {
        return service.readActive();
    }

    @GetMapping("/{id}")
    public AdresseDtoGet readOne(@PathVariable("id") Long id) {
        return service.readOne(id);
    }

    @PostMapping("/client/{idClient}")
    public List<AdresseDtoPost> create(@RequestBody AdresseDtoPost adresseDtoPost, @PathVariable("idClient") Long idClient) {
        return service.create(idClient, adresseDtoPost);
    }

    @GetMapping("/client/{idClient}")
    public List<AdresseDtoGet> readFromClient(@PathVariable("idClient") Long idClient) {
        return service.readFromClient(idClient);
    }

    @PutMapping("/{id}")
    public AdresseDtoPost update(@PathVariable("id") Long id, @RequestBody AdresseDtoPost adresseDtoPost) {
        return service.update(id, adresseDtoPost);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }
}
