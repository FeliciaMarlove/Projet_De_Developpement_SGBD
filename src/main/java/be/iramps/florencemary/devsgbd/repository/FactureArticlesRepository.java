package be.iramps.florencemary.devsgbd.repository;

import be.iramps.florencemary.devsgbd.model.FactureArticlesLiaison;
import org.springframework.data.repository.CrudRepository;

public interface FactureArticlesRepository extends CrudRepository<FactureArticlesLiaison, Long> {
}
