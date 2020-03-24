package be.iramps.florencemary.devsgbd.controller;

import be.iramps.florencemary.devsgbd.dto.ArticleDtoGet;
import be.iramps.florencemary.devsgbd.dto.ArticleDtoPost;
import be.iramps.florencemary.devsgbd.model.Article;
import be.iramps.florencemary.devsgbd.repository.ArticleRepository;
import be.iramps.florencemary.devsgbd.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<ArticleDtoGet> read() { return service.readActive(); }

    @GetMapping("/{id}")
    public ArticleDtoGet readOne(@PathVariable("id") Long id) { return service.readOne(id); }

    @PostMapping
    public ArticleDtoGet create(@RequestBody ArticleDtoPost articleDtoPost) { return service.create(articleDtoPost);}

    @PutMapping("/{id}")
    public Boolean update(@PathVariable("id") Long id, @RequestBody ArticleDtoPost articleDtoPost) {
        return service.update(id, articleDtoPost);
    }

    @DeleteMapping("/{id}")
    public ArticleDtoGet delete(@PathVariable("id") Long id) {
        return service.delete(id);
    }
}
