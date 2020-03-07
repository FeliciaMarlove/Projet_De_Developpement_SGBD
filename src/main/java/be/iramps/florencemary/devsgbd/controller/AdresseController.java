package be.iramps.florencemary.devsgbd.controller;

import be.iramps.florencemary.devsgbd.dto.AdresseDto;
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
    public List<AdresseDto> read() {
        return service.readActive();
    }

    @GetMapping("/{id}")
    public AdresseDto readOne(@PathVariable("id") Long id) {
        return service.readOne(id);
    }

    @PostMapping("/client/{idClient}")
    public List<AdresseDto> create(@RequestBody AdresseDto adresseDto, @PathVariable("idClient") Long idClient) {
        return service.create(idClient, adresseDto);
    }

    @PutMapping("/{id}")
    public AdresseDto update(@PathVariable("id") Long id, @RequestBody AdresseDto adresseDto) {
        return service.update(id, adresseDto);
    }
}
