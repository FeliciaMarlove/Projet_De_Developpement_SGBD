package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.ArticleDtoGet;
import be.iramps.florencemary.devsgbd.dto.ArticleDtoPost;
import be.iramps.florencemary.devsgbd.model.Article;

import java.util.List;

/**
 * Interface non documentee : Ref Classe Implementee
 */
public interface ArticleService {
    List<Article> read();
    ArticleDtoGet readOne(Long id);
    ArticleDtoGet create(ArticleDtoPost newItem);
    Boolean update(Long id, ArticleDtoPost update);
    ArticleDtoGet delete(Long id);
    List<ArticleDtoGet> readActive();
}
