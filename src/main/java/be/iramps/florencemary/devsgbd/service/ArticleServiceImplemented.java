package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.ArticleDto;
import be.iramps.florencemary.devsgbd.model.Article;
import be.iramps.florencemary.devsgbd.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImplemented implements ArticleService {
    private ArticleRepository repository;

    @Autowired
    public ArticleServiceImplemented(ArticleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Article> read() {
        return (List<Article>) repository.findAll();
    }

    @Override
    public Article readOne(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public void create(Article newItem) {
        repository.save(newItem);
    }

    @Override
    public Article update(Long id, ArticleDto update) {
        return null;
    }

    @Override
    public Article delete(Long id) {
        return readOne(id);
    }
}
