package be.iramps.florencemary.devsgbd.repository;

import be.iramps.florencemary.devsgbd.model.Article;
import org.springframework.data.repository.CrudRepository;

interface ArticleRepository extends CrudRepository<Article, Long> {
}
