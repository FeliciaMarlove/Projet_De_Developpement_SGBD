package be.iramps.florencemary.devsgbd.controller;

import be.iramps.florencemary.devsgbd.model.Facture;
import be.iramps.florencemary.devsgbd.repository.FactureRepository;
import be.iramps.florencemary.devsgbd.service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/facture")
@CrossOrigin(origins = {"http://localhost:4200"})
public class FactureController {
    private final FactureService service;

    public FactureController(FactureService service) {
        this.service = service;
    }

    @Autowired
    FactureRepository repository;

    @GetMapping
    public List<Facture> read() {
        return service.readActive();
    }

    @GetMapping("/{id}")
    public Facture readOne(@PathVariable("id") Long id) {
        return service.readOne(id);
    }

    @PostMapping

    @PutMapping("/{id}")

    @DeleteMapping("/{id}")
    public Facture delete(@PathVariable("id") Long id) {
        return service.delete(id);
    }
}

//todo : terminer et tester contrôleur
