package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.ArticleDtoGet;
import be.iramps.florencemary.devsgbd.dto.ArticleDtoPost;
import be.iramps.florencemary.devsgbd.dto.PaiementDtoPost;
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
    public ArticleDtoGet readOne(Long id) {
        for (Article article: repository.findAll()) {
            if (article.getIdArticle().equals(id)) {
                return mapEntityToDtoGet(repository.findById(id).get());
            }
        }
        return null;
    }

    @Override
    public ArticleDtoGet create(ArticleDtoPost newItem) {
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
            return mapEntityToDtoGet(newArticle);
        }
        return null;
    }

    @Override
    public Boolean update(Long id, ArticleDtoPost update) {
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
    public ArticleDtoGet delete(Long id) {
        if (exists(id)) {
            Article article = repository.findById(id).get();
            repository.findById(id).get().setActifArticle(false);
            repository.save(article);
            return readOne(id);
        }
        return null;
    }

    @Override
    public List<ArticleDtoGet> readActive() {
        List<ArticleDtoGet> actifs = new ArrayList<>();
        for (Article article : read()) {
            if (article.isActifArticle()) actifs.add(mapEntityToDtoGet(article));
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

    private Article equalsAny(ArticleDtoPost articleDtoPost) {
        for (Article articleCompared : read()) {
            if ((articleDtoPost.getCodeEAN().equals(articleCompared.getCodeEAN()))) {
                return repository.findById(articleCompared.getIdArticle()).get();
            }
        }
        return null;
    }

    private ArticleDtoPost mapEntityToDtoPost(Article article) {
        return new ArticleDtoPost(article.getNomArticle(), article.getDescArticle(), article.getStock(), article.getPrixUnitaire(), article.getCodeEAN(), article.getTva().getIdTva());
    }

    private ArticleDtoGet mapEntityToDtoGet(Article article) {
        return new ArticleDtoGet(article.getIdArticle(),article.getNomArticle(), article.getDescArticle(), article.getStock(), article.getPrixUnitaire(), article.getCodeEAN(), article.getTva().getIdTva());
    }

    private List<ArticleDtoGet> mapEntitiesToDtosGet(List<Article> articles) {
        List<ArticleDtoGet> dtos = new ArrayList<>();
        for (Article article: articles) {
            dtos.add(mapEntityToDtoGet(article));
        }
        return dtos;
    }

    private List<ArticleDtoPost> mapEntitiesToDtosPost(List<Article> articles) {
        List<ArticleDtoPost> dtos = new ArrayList<>();
        for (Article article: articles) {
            dtos.add(mapEntityToDtoPost(article));
        }
        return dtos;
    }
}
