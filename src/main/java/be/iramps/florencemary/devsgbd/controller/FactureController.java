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
    public List<Object> read() {
        return service.readActive();
    }

    @GetMapping("/{id}")
    public Object readOne(@PathVariable("id") Long id) {
        return service.readOne(id);
    }

    @PostMapping
    public FactureDtoGet create(@RequestBody FactureDtoPost factureDtoPost) {
        return service.create(factureDtoPost.getIdClient(), factureDtoPost.getIdPaiement());
    }

    @PutMapping("/{id}/add")
    public FactureArticlesLiaison update(@RequestBody FactureArticleDto factureArticleDto, @PathVariable("id") Long id) {
        return service.addArticle(factureArticleDto.getIdFacture(), factureArticleDto);
    }

    @GetMapping("/{id}/minus/{idArt}")
    public boolean minusOne(@PathVariable("id") Long id, @PathVariable("idArt") Long idArt) {
        return service.articleMinusOne(id, idArt);
    }

    @GetMapping("/{id}/del/{idArt}")
    public boolean deleteArticle(@PathVariable("id") Long id, @PathVariable("idArt") Long idArt) {
        return service.deleteArticle(id, idArt);
    }

    @GetMapping("/{id}/articles")
    public List<FactureArticlesLiaison> readArticles(@PathVariable("id") Long id) {
        return service.readArticlesOnFacture(id);
    }

    @GetMapping("/{id}/validate")
    public FactureDtoGet validate(@PathVariable("id") Long id) {
        return service.validateFacture(id);
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") Long id) {
        return service.delete(id);
    }
}
