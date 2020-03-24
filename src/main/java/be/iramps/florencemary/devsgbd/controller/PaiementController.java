package be.iramps.florencemary.devsgbd.controller;

import be.iramps.florencemary.devsgbd.dto.PaiementDtoGet;
import be.iramps.florencemary.devsgbd.dto.PaiementDtoPost;
import be.iramps.florencemary.devsgbd.model.Paiement;
import be.iramps.florencemary.devsgbd.repository.PaiementRepository;
import be.iramps.florencemary.devsgbd.service.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/paiement")
@CrossOrigin(origins = {"http://localhost:4200"})
public class PaiementController {
    private final PaiementService service;

    public PaiementController(PaiementService service) {
        this.service = service;
    }

    @Autowired
    PaiementRepository repository;

    @GetMapping
    public List<PaiementDtoGet> read() {
        return service.readActive();
    }

    @GetMapping("/{id}")
    public PaiementDtoGet readOne(@PathVariable("id") Long id) { return service.readOne(id); }

    @PostMapping
    public PaiementDtoGet create(@RequestBody PaiementDtoPost paiementDtoPost) { return service.create(paiementDtoPost); }

    @PutMapping("/{id}")
    public PaiementDtoGet update(@PathVariable("id") Long id, @RequestBody PaiementDtoPost paiementDtoPost) { return service.update(id, paiementDtoPost); }

    @DeleteMapping("/{id}")
    public PaiementDtoGet delete(@PathVariable("id") Long id) { return service.delete(id); }
}
