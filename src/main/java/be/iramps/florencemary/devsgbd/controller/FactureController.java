package be.iramps.florencemary.devsgbd.controller;

import be.iramps.florencemary.devsgbd.dto.FactureArticleDto;
import be.iramps.florencemary.devsgbd.dto.FactureDto;
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
    public List<FactureDto> read() {
        return service.readActive();
    }

    @GetMapping("/{id}")
    public FactureDto readOne(@PathVariable("id") Long id) {
        return service.readOne(id);
    }

    @PostMapping
    public FactureDto create(@RequestBody FactureDto factureDto) {
        return service.create(factureDto.getIdClient(), factureDto.getIdPaiement());
    }

    @PutMapping("/{id}/add")
    public boolean update(@RequestBody FactureArticleDto factureArticleDto, @PathVariable("id") Long id) {
        return service.addArticle(factureArticleDto.getIdFacture(), factureArticleDto);
    }

    @PutMapping("/{id}")
    public Facture validate(@PathVariable("id") Long id) {
        return service.validateFacture(id);
    }

    @DeleteMapping("/{id}")
    public FactureDto delete(@PathVariable("id") Long id) {
        return service.delete(id);
    }
}
