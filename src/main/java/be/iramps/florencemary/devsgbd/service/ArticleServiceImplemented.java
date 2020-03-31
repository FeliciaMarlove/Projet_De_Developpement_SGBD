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

/**
 * Service contenant la couche business sur l'entite Article
 */
@Service
public class ArticleServiceImplemented implements ArticleService {
    private ArticleRepository repository;
    private TvaRepository repositoryTva;

    @Autowired
    public ArticleServiceImplemented(ArticleRepository repository, TvaRepository repositoryTva) {
        this.repository = repository;
        this.repositoryTva = repositoryTva;
    }

    /**
     * Retourne tous les articles en DB
     * @return List Article tous les articles en DB
     */
    @Override
    public List<Article> read() {
        return (List<Article>) repository.findAll();
    }

    /**
     * Retourne un article
     * @param id (Long) : id de l'article a retourner
     * @return ArticleDtoGet l'article || null si pas de correspondance
     */
    @Override
    public ArticleDtoGet readOne(Long id) {
        for (Article article: repository.findAll()) {
            if (article.getIdArticle().equals(id)) {
                return mapEntityToDtoGet(repository.findById(id).get());
            }
        }
        return null;
    }

    /**
     * Retourne les articles actifs
     * @return List ArticleDtoGet articles actifs
     */
    @Override
    public List<ArticleDtoGet> readActive() {
        List<ArticleDtoGet> actifs = new ArrayList<>();
        for (Article article : read()) {
            if (article.isActifArticle()) actifs.add(mapEntityToDtoGet(article));
        }
        return actifs;
    }

    /**
     * Cree un enregistrement d'un nouvel article en DB
     * @param newItem (ArticleDtoPost) : DTO POST de l'article a enregistrer
     * @return ArticleDtoGet l'article cree || null si l'article se trouve deja en DB
     */
    @Override
    public ArticleDtoGet create(ArticleDtoPost newItem) {
        if (equalsAny(newItem) == null) {
        Tva findTva = repositoryTva.findById(newItem.getIdTva()).get();
        Article newArticle = new Article(
                newItem.getNomArticle(),
                newItem.getDescArticle(),
                newItem.getStock(),
                newItem.getPrixUnitaire(),
                newItem.getCodeEAN(),
                findTva
        );
            repository.save(newArticle);
            return mapEntityToDtoGet(newArticle);
        }
        return null;
    }

    /**
     * Met a jour un article en DB
     * @param id (Long) : id de l'article a mettre a jour
     * @param update (ArticleDtoPost) : DTO POST de l'article modifie
     * @return Boolean true si la mise a jour a reussi, false si l'id n'a pas ete trouve
     */
    @Override
    public Boolean update(Long id, ArticleDtoPost update) {
        if ((exists(id))) {
            Article toUpdate = repository.findById(id).get();
            toUpdate.setNomArticle(update.getNomArticle());
            toUpdate.setDescArticle(update.getDescArticle());
            toUpdate.setStock(update.getStock());
            toUpdate.setCodeEAN(update.getCodeEAN());
            toUpdate.setPrixUnitaire(update.getPrixUnitaire());
            toUpdate.setTva(repositoryTva.findById(update.getIdTva()).get());
            repository.save(toUpdate);
            return true;
        }
        return false;
    }

    /**
     * Supprime logiquement un article en DB
     * @param id (Long) : id de l'article a supprimer
     * @return ArticleDtoGet l'article supprime || null si l'article n'a pas ete trouve en DB
     */
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

    //__________________PRIVATE METHODS_________________________________________________________________________________

    private boolean exists(Long id) {
        for (Article article : read()) {
            if ((article.isActifArticle()) && (article.getIdArticle().equals(id))) {
                return true;
            }
        }
        return false;
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
