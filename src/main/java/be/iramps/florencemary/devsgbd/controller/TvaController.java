package be.iramps.florencemary.devsgbd.controller;

import be.iramps.florencemary.devsgbd.dto.TvaDto;
import be.iramps.florencemary.devsgbd.model.Tva;
import be.iramps.florencemary.devsgbd.repository.TvaRepository;
import be.iramps.florencemary.devsgbd.service.TvaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur REST du endpoint Tva (/api/tva)
 * CORS Access CrossOrigin localhost:4200
 */
@RestController
@RequestMapping(value = "/api/tva")
@CrossOrigin(origins = {"http://localhost:4200"})
public class TvaController {
    private final TvaService service;

    public TvaController(TvaService service) {
        this.service = service;
    }

    @Autowired
    TvaRepository repository;

    @GetMapping
    public List<Tva> read() {
        return service.readActive();
    }

    @GetMapping("/{id}")
    public Tva readOne(@PathVariable("id") Long id) {
        return service.readOne(id);
    }

    @PostMapping
    public Tva create(@RequestBody TvaDto tvaDto) {
        return service.create(tvaDto);
    }

    @PutMapping("/{id}")
    public Tva update(@PathVariable("id") Long id, @RequestBody TvaDto tvaDto) {
        return service.update(id, tvaDto);
    }

    @DeleteMapping("/{id}")
    public Tva delete(@PathVariable("id") Long id) {
        return service.delete(id);
    }
}
