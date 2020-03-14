package be.iramps.florencemary.devsgbd.repository;

import be.iramps.florencemary.devsgbd.model.FactureArticlesLiaison;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FactureArticlesRepository extends CrudRepository<FactureArticlesLiaison, Long> {
//    @Query(value = "SELECT max(id_facture_articles) +1 FROM facture_articles",
//            nativeQuery = true)
//    int getLastIdFactureArticlesPlusOne();
//
//    @Query(value = "ALTER SEQUENCE facture_articles_generator RESTART WITH ?1",
//            nativeQuery = true)
//    void jumpToNextId(int currentId);



}
