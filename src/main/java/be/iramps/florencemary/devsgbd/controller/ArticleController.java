package be.iramps.florencemary.devsgbd.controller;

import be.iramps.florencemary.devsgbd.repository.ArticleRepository;
import be.iramps.florencemary.devsgbd.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
