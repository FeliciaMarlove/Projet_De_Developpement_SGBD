package be.iramps.florencemary.devsgbd.controller;

import be.iramps.florencemary.devsgbd.dto.ClientDtoGet;
import be.iramps.florencemary.devsgbd.dto.ClientDtoPost;
import be.iramps.florencemary.devsgbd.repository.ClientRepository;
import be.iramps.florencemary.devsgbd.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controleur REST du endpoint Client (/api/client)
 * CORS Access CrossOrigin localhost:4200
 */
@RestController
@RequestMapping(value = "/api/client")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ClientController {
    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @Autowired
    ClientRepository repository;

    /**
     * Retourne la liste des clients actifs suite a une requette http get
     * @return List ClientDtoGet clients actifs
     */
    @GetMapping
    public List<ClientDtoGet> read() {
        return service.readActive();
    }

    /**
     * Retourne un client suite a une requette http get
     * @param id (Long) : id du client a retourner
     * @return ClientDtoGet client correspondant a l'id
     */
    @GetMapping("/{id}")
    public ClientDtoGet readOne(@PathVariable("id") Long id) { return service.readOne(id); }

    /**
     * Cree un enregistrement de client en DB suite a une requete http post
     * @param clientDtoPost (ClientDtoPost) : DTO POST du client a enregistrer en base de donnees
     * @return ClientDtoPost client cree
     */
    @PostMapping
    public ClientDtoPost create(@RequestBody ClientDtoPost clientDtoPost) { return service.create(clientDtoPost); }

    /**
     * Met a jour un enregistrement en base de donnees suite a une requete http put
     * @param id (Long) : id du client a modifier
     * @param clientDtoPost (ClientDtoPost) : DTO POST du client a modifier
     * @return ClientDtoPost client modifie
     */
    @PutMapping("/{id}")
    public ClientDtoPost update(@PathVariable("id") Long id, @RequestBody ClientDtoPost clientDtoPost) { return service.update(id, clientDtoPost); }

    /**
     * Supprime logiquement un enregistrement Client en base de donnees suite a une requete http delete
     * @param id (Long) : id du client a supprimer logiquement
     * @return ClientDtoGet client supprime
     */
    @DeleteMapping("/{id}")
    public ClientDtoGet delete(@PathVariable("id") Long id) { return service.delete(id); }
}
