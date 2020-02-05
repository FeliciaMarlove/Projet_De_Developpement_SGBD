package be.iramps.florencemary.devsgbd.repository;

import be.iramps.florencemary.devsgbd.model.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
}
