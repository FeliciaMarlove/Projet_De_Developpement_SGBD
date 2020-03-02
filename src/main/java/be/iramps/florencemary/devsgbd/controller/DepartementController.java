package be.iramps.florencemary.devsgbd.controller;

import be.iramps.florencemary.devsgbd.dto.DepartementDto;
import be.iramps.florencemary.devsgbd.model.Departement;
import be.iramps.florencemary.devsgbd.repository.DepartementRepository;
import be.iramps.florencemary.devsgbd.service.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/departement")
@CrossOrigin(origins = {"http://localhost:4200"})
public class DepartementController {
    private final DepartementService service;

    public DepartementController(DepartementService service) {
        this.service = service;
    }

    @Autowired
    DepartementRepository repository;

    @GetMapping
    public List<Departement> read() {
        return service.readActive();
    }

    @GetMapping("/{id}")
    public Departement readOne(@PathVariable("id") Long id) { return service.readOne(id); }

    @PostMapping
    public Departement create(@RequestBody DepartementDto departementDto) { return service.create(departementDto); }

    @PutMapping("/{id}")
    public Departement update(@PathVariable("id") Long id, @RequestBody DepartementDto departementDto) { return service.update(id, departementDto); }

    @DeleteMapping("/{id}")
    public Departement delete(@PathVariable("id") Long id) { return service.delete(id); }
}
