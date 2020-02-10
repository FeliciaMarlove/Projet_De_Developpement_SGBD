package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.ArticleDto;
import be.iramps.florencemary.devsgbd.model.Article;
import be.iramps.florencemary.devsgbd.model.Tva;
import be.iramps.florencemary.devsgbd.repository.ArticleRepository;
import be.iramps.florencemary.devsgbd.repository.TvaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public void create(ArticleDto newItem) {
        Tva findTva = repositoryTva.findById(newItem.getIdTva()).get();
        Article newArticle = new Article(
                newItem.getNomArticle(),
                newItem.getDescArticle(),
                newItem.getStock(),
                newItem.getPrixUnitaire(),
                newItem.getCodeEAN(),
                findTva
        );
        if (equalsAny(newArticle) == null) repository.save(newArticle);
    }

    @Override
    public Article update(Long id, ArticleDto update) {
        Article toUpdate = repository.findById(id).get();
        if ((toUpdate != null) && (exists(id))) {
            toUpdate.setNomArticle(update.getNomArticle());
            toUpdate.setDescArticle(update.getDescArticle());
            toUpdate.setStock(update.getStock());
            toUpdate.setCodeEAN(update.getCodeEAN());
            toUpdate.setPrixUnitaire(update.getPrixUnitaire());
            toUpdate.setTva(repositoryTva.findById(update.getIdTva()).get());
            if (equalsAny(toUpdate) == null) repository.save(toUpdate);
        }
        return toUpdate;
    }

    @Override
    public Article delete(Long id) {
        if (exists(id)) {
            repository.findById(id).get().setActifArticle(false);
            return readOne(id);
        }
        return null;
    }

    @Override
    public List<Article> readActive() {
        List<Article> actifs = new ArrayList<>(read());
        for (Article article : actifs) {
            if (article.isActifArticle()) actifs.remove(article);
        }
        return actifs;
    }

    private boolean exists(Long id) {
        boolean exists = false;
        for (Article article : read()) {
            if ((article.isActifArticle() == true) && (article.getIdArticle() == id)) exists = true;
        }
        return exists;
    }

    private Article equalsAny(Article article) {
        for (Article articleCompared : read()) {
            if (article.equals(articleCompared)) return repository.findById(articleCompared.getIdArticle()).get();
        }
        return null;
    }
}
