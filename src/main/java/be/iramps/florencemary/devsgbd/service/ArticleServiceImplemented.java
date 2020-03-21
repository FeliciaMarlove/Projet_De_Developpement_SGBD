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
    public ArticleServiceImplemented(ArticleRepository repository, TvaRepository repositoryTva) {
        this.repository = repository;
        this.repositoryTva = repositoryTva;
    }

    @Override
    public List<Article> read() {
        return (List<Article>) repository.findAll();
    }

    @Override
    public Article readOne(Long id) {
        for (Article article: repository.findAll()) {
            if (article.getIdArticle().equals(id)) {
                return repository.findById(id).get();
            }
        }
        return null;
    }

    @Override
    public Article create(ArticleDto newItem) {
        Tva findTva = repositoryTva.findById(newItem.getIdTva()).get();
        Article newArticle = new Article(
                newItem.getNomArticle(),
                newItem.getDescArticle(),
                newItem.getStock(),
                newItem.getPrixUnitaire(),
                newItem.getCodeEAN(),
                findTva
        );
        if (equalsAny(newItem) == null) {
            repository.save(newArticle);
            return newArticle;
        }
        return null;
    }

    @Override
    public Boolean update(Long id, ArticleDto update) {
        if ((exists(id))) {
            Article toUpdate = repository.findById(id).get();
            System.out.println(toUpdate);
            System.out.println(update);
            toUpdate.setNomArticle(update.getNomArticle());
            toUpdate.setDescArticle(update.getDescArticle());
            toUpdate.setStock(update.getStock());
            toUpdate.setCodeEAN(update.getCodeEAN());
            toUpdate.setPrixUnitaire(update.getPrixUnitaire());
            toUpdate.setTva(repositoryTva.findById(update.getIdTva()).get());
            System.out.println(toUpdate);
            System.out.println(update);
            repository.save(toUpdate);
            return true;
        }
        return false;
    }

    @Override
    public Article delete(Long id) {
        if (exists(id)) {
            Article article = repository.findById(id).get();
            repository.findById(id).get().setActifArticle(false);
            repository.save(article);
            return readOne(id);
        }
        return null;
    }

    @Override
    public List<Article> readActive() {
        List<Article> actifs = new ArrayList<>();
        for (Article article : read()) {
            if (article.isActifArticle()) actifs.add(article);
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

    private Article equalsAny(ArticleDto articleDto) {
        for (Article articleCompared : read()) {
            if ((articleDto.getCodeEAN().equals(articleCompared.getCodeEAN()))) {
                return repository.findById(articleCompared.getIdArticle()).get();
            }
        }
        return null;
    }
}
