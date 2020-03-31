package be.iramps.florencemary.devsgbd.controller;

import be.iramps.florencemary.devsgbd.dto.DepartementDto;
import be.iramps.florencemary.devsgbd.model.Departement;
import be.iramps.florencemary.devsgbd.repository.DepartementRepository;
import be.iramps.florencemary.devsgbd.service.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur REST du endpoint Departement (/api/departement)
 * CORS Access CrossOrigin localhost:4200
 */
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

    /**
     * Retourne la liste des departements actifs suite a une requette http get
     * @return List DepartementDto departements actifs
     */
    @GetMapping
    public List<DepartementDto> read() {
        return service.readActive();
    }

    /**
     * Retourne un departement suite a une requette http get
     * @param name (String) : nom du departement a retourner
     * @return : DepartementDto departement correspondant au nom
     */
    @GetMapping("/{name}")
    public DepartementDto readOne(@PathVariable("name") String name) { return service.readOne(name); }

    /**
     * Cree un enregistrement de departement en DB suite a une requete http post
     * @param departementDto (DepartementDto) : DTO du departement a enregistrer en base de donnees
     * @return DepartementDto departement cree
     */
    @PostMapping
    public DepartementDto create(@RequestBody DepartementDto departementDto) { return service.create(departementDto); }

    /**
     * Met a jour un enregistrement en base de donnees suite a une requette http put
     * @param id (Long) : id du departement a modifier
     * @param departementDto (DepartementDto) : DTO du departement a modifier
     * @return DepartementDto departement modifie
     */
    @PutMapping("/{id}")
    public DepartementDto update(@PathVariable("id") Long id, @RequestBody DepartementDto departementDto) { return service.update(id, departementDto); }

    /**
     * Supprime logiquement un enregistrement Departement en base de donnees suite a une requette http delete
     * @param name (String) : nom du departement a supprimer
     * @return DepartementDto : departement supprime
     */
    @DeleteMapping("/{name}")
    public DepartementDto delete(@PathVariable("name") String name) { return service.delete(name); }
}
