package be.iramps.florencemary.devsgbd.controller;

import be.iramps.florencemary.devsgbd.dto.AdresseDtoGet;
import be.iramps.florencemary.devsgbd.dto.AdresseDtoPost;
import be.iramps.florencemary.devsgbd.repository.AdresseRepository;
import be.iramps.florencemary.devsgbd.service.AdresseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controleur REST du endpoint Adresse (/api/adresse)
 * CORS Access CrossOrigin localhost:4200
 */
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

    /**
     * Retourne la liste des adresses actives suite a une requete http get
     * @return List AdresseDtoGet adresses actives
     */
    @GetMapping
    public List<AdresseDtoGet> read() {
        return service.readActive();
    }

    /**
     * Retourne une adresse suite a une requete http get
     * @param id (Long) : id de l'adresse a retourner
     * @return AdresseDtoGet adresse correspondant a l'id
     */
    @GetMapping("/{id}")
    public AdresseDtoGet readOne(@PathVariable("id") Long id) {
        return service.readOne(id);
    }

    /**
     * Crée un enregistrement d'adresse en DB pour un client suite a une requête http post
     * @param adresseDtoPost (AdresseDtoPost) : DTO POST de l'adresse a enregistrer en base de donnees
     * @param idClient (Long) : id du client pour qui l'adresse est creee
     * @return List AdresseDtoPost adresses du client
     */
    @PostMapping("/client/{idClient}")
    public List<AdresseDtoPost> create(@RequestBody AdresseDtoPost adresseDtoPost, @PathVariable("idClient") Long idClient) {
        return service.create(idClient, adresseDtoPost);
    }

    /**
     * Retourne la liste des adresses du client suite a une requete http get
     * @param idClient (Long) : id du client dont on recupere les adresses
     * @return List AdresseDtoGet adresses du client
     */
    @GetMapping("/client/{idClient}")
    public List<AdresseDtoGet> readFromClient(@PathVariable("idClient") Long idClient) {
        return service.readFromClient(idClient);
    }

    /**
     * Met a jour un enregistrement Adresse en base de donnees suite a une requete http put et retourne l'adresse creee
     * @param id (Long) : id de l'adresse a modifier
     * @param adresseDtoPost (AdresseDtoPost) : DTO POST de l'adresse a modifier
     * @return AdresseDtoPost adresse
     */
    @PutMapping("/{id}")
    public AdresseDtoPost update(@PathVariable("id") Long id, @RequestBody AdresseDtoPost adresseDtoPost) {
        return service.update(id, adresseDtoPost);
    }

    /**
     * Supprime logiquement un enregistrement Adresse en base de donnees suite q une requete http delete
     * @param id (Long) : id de l'adresse a supprimer logiquement
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }
}
