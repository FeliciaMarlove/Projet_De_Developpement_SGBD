package be.iramps.florencemary.devsgbd.controller;

import be.iramps.florencemary.devsgbd.dto.ArticleDtoGet;
import be.iramps.florencemary.devsgbd.dto.ArticleDtoPost;
import be.iramps.florencemary.devsgbd.model.Article;
import be.iramps.florencemary.devsgbd.repository.ArticleRepository;
import be.iramps.florencemary.devsgbd.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controleur REST du endpoint Article (/api/article)
 * CORS Access CrossOrigin localhost:4200
 */
@RestController
@RequestMapping(value = "/api/article")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ArticleController {
    private final ArticleService service;

    public ArticleController(ArticleService service) {
        this.service = service;
    }

    @Autowired
    ArticleRepository repository;

    /**
     * Retourne la liste des articles actifs suite a une requete http get
     * @return List ArticleDtoGet articles actifs
     */
    @GetMapping
    public List<ArticleDtoGet> read() { return service.readActive(); }

    /**
     * Retourne un article suite a une requete http get
     * @param id (Long) : id de l'article a retourner
     * @return ArticleDtoGet article correspondant a l'id
     */
    @GetMapping("/{id}")
    public ArticleDtoGet readOne(@PathVariable("id") Long id) { return service.readOne(id); }

    /**
     * Cree un enregistrement d'article en DB suite a une requete http post
     * @param articleDtoPost (ArticleDtoPost) : DTO POST de l'article a enregistrer en base de donnees
     * @return ArticleDtoGet article cree
     */
    @PostMapping
    public ArticleDtoGet create(@RequestBody ArticleDtoPost articleDtoPost) { return service.create(articleDtoPost);}

    /**
     * Met a jour un enregistrement en base de donnees suite a une requete http put
     * @param id (Long) : id de l'article a modifier
     * @param articleDtoPost (ArticleDtPost) : DTO POST de l'article a modifier
     * @return Boolean true en cas de reussite, false en cas d'echec
     */
    @PutMapping("/{id}")
    public Boolean update(@PathVariable("id") Long id, @RequestBody ArticleDtoPost articleDtoPost) {
        return service.update(id, articleDtoPost);
    }

    /**
     * Supprime logiquement un enregistrement Article en base de donnees suite a une requete http delete
     * @param id (Long) : id de l'article a supprimer logiquement
     * @return ArticleDtoGet article supprime
     */
    @DeleteMapping("/{id}")
    public ArticleDtoGet delete(@PathVariable("id") Long id) {
        return service.delete(id);
    }
}
