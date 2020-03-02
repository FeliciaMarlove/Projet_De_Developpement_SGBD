package be.iramps.florencemary.devsgbd.controller;

import be.iramps.florencemary.devsgbd.dto.ArticleDto;
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
    public List<Article> read() { return service.readActive(); }

    @GetMapping("/{id}")
    public Article readOne(@PathVariable("id") Long id) { return service.readOne(id); }

    @PostMapping
    public Article create(@RequestBody ArticleDto articleDto) { return service.create(articleDto);}

    @PutMapping("/{id}")
    public Article update(@PathVariable("id") Long id, @RequestBody ArticleDto articleDto) {
        return service.update(id, articleDto);
    }

    @DeleteMapping("/{id}")
    public Article delete(@PathVariable("id") Long id) {
        return service.delete(id);
    }
}
