package be.iramps.florencemary.devsgbd.controller;

import be.iramps.florencemary.devsgbd.dto.PaiementDto;
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
    public List<Paiement> read() {
        return service.readActive();
    }

    @GetMapping("/{id}")
    public Paiement readOne(@PathVariable("id") Long id) { return service.readOne(id); }

    @PostMapping
    public Paiement create(@RequestBody PaiementDto paiementDto) { return service.create(paiementDto); }

    @PutMapping("/{id}")
    public Paiement update(@PathVariable("id") Long id, @RequestBody PaiementDto paiementDto) { return service.update(id, paiementDto); }

    @DeleteMapping("/{id}")
    public Paiement delete(@PathVariable("id") Long id) { return service.delete(id); }
}
