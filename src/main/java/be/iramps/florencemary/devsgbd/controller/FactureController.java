package be.iramps.florencemary.devsgbd.controller;

import be.iramps.florencemary.devsgbd.dto.FactureArticleDto;
import be.iramps.florencemary.devsgbd.dto.FactureDtoGet;
import be.iramps.florencemary.devsgbd.dto.FactureDtoPost;
import be.iramps.florencemary.devsgbd.model.FactureArticlesLiaison;
import be.iramps.florencemary.devsgbd.repository.FactureRepository;
import be.iramps.florencemary.devsgbd.service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controleur REST du endpoint Facture (/api/facture)
 * CORS Access CrossOrigin localhost:4200
 */
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

    /**
     * Retourne la liste des factures actives suite a une requette http get
     * @return List Object (FactureDtoGet et FactureDtoPost) actives
     */
    @GetMapping
    public List<Object> read() {
        return service.readActive();
    }

    /**
     * Retourne une facture suite a une requette http get
     * @param id (Long) : id de la facture a retourner
     * @return Object (FactureDtoPost ou FactureDtoGet) facture correspondant a l'id
     */
    @GetMapping("/{id}")
    public Object readOne(@PathVariable("id") Long id) {
        return service.readOne(id);
    }

    /**
     * Cree un enregistrement de facture en DB suite a une requete http post
     * @param factureDtoPost (FactureDtoPost) : DTO POST de la facture a enregistrer en base de donnees
     * @return FactureDtoGet facture creee
     */
    @PostMapping
    public FactureDtoGet create(@RequestBody FactureDtoPost factureDtoPost) {
        return service.create(factureDtoPost.getIdClient(), factureDtoPost.getIdPaiement());
    }

    /**
     * Met a jour un enregistrement FactureArticleLiaison en base de donnees suite a une requete http put
     * @param factureArticleDto (FactureArticleDto) : DTO de la FactureArticleLiaison a modifier
     * @param id (Long) : id de la facture ou se trouve la FactureArticleLiaison a modifier
     * @return FactureArticleDto FactureArticleLiaison modifiee
     */
    @PutMapping("/{id}/add")
    public FactureArticleDto update(@RequestBody FactureArticleDto factureArticleDto, @PathVariable("id") Long id) {
        return service.addArticle(factureArticleDto.getIdFacture(), factureArticleDto);
    }

    /**
     * Supprime une unite d'un article present sur la facture suite a une requette http get
     * @param id (Long) : id de la facture
     * @param idArt (Long) : id de l'article
     * @return boolean true en cas de reussite, false en cas d'echec
     */
    @GetMapping("/{id}/minus/{idArt}")
    public boolean minusOne(@PathVariable("id") Long id, @PathVariable("idArt") Long idArt) {
        return service.articleMinusOne(id, idArt);
    }

    /**
     * Supprime un article present sur la facture suite a une requete http get
     * @param id (Long) : id de la facture
     * @param idArt (Long) : id de l'article
     * @return boolean true en cas de reussite, false en cas d'echec
     */
    @GetMapping("/{id}/del/{idArt}")
    public boolean deleteArticle(@PathVariable("id") Long id, @PathVariable("idArt") Long idArt) {
        return service.deleteArticle(id, idArt);
    }

    /**
     * Retourne la liste des articles sur une facture suite a une requete http get
     * @param id (Long) : id de la facture
     * @return List FactureArticleDto articles sur la facture
     */
    @GetMapping("/{id}/articles")
    public List<FactureArticleDto> readArticles(@PathVariable("id") Long id) {
        return service.readArticlesOnFacture(id);
    }

    /**
     * Valide une facture en créant une facture finale suite a une requete http get
     * @param id (Long) : id de la facture
     * @return FactureDtoGet facture finale creee
     */
    @GetMapping("/{id}/validate")
    public FactureDtoGet validate(@PathVariable("id") Long id) {
        return service.validateFacture(id);
    }

    /**
     * Supprime une facture logiquement suite a une requette http delete
     * @param id (Long) : id de la facture a supprimer logiquement
     * @return Object (FactureDtoGet ou FactureDtoPost) facture supprimee
     */
    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") Long id) {
        return service.delete(id);
    }
}
