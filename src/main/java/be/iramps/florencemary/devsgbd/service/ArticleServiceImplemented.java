package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.ArticleDto;
import be.iramps.florencemary.devsgbd.model.Article;
import be.iramps.florencemary.devsgbd.repository.ArticleRepository;
import be.iramps.florencemary.devsgbd.repository.TvaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImplemented implements ArticleService {
    private ArticleRepository repository;
    private TvaRepository repositoryTva;

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
        Article toUpdate = repository.findById(id).get();
        if (toUpdate != null) {
            toUpdate.setNomArticle(update.getNomArticle());
            toUpdate.setDescArticle(update.getDescArticle());
            toUpdate.setStock(update.getStock());
            toUpdate.setCodeEAN(update.getCodeEAN());
            toUpdate.setPrixUnitaire(update.getPrixUnitaire());
            toUpdate.setTva(repositoryTva.findById(update.getIdTva()).get());
            repository.save(toUpdate);
        }
        return toUpdate;
    }

    @Override
    public Article delete(Long id) {
        return readOne(id);
    }
}
