package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.ArticleDto;
import be.iramps.florencemary.devsgbd.model.Article;

import java.util.List;

public interface ArticleService {
    List<Article> read();
    Article readOne(Long id);
    void create(Article newItem);
    Article update(Long id, ArticleDto update);
    Article delete(Long id);
}
