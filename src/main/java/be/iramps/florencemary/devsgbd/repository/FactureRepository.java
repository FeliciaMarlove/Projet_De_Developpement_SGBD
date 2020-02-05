package be.iramps.florencemary.devsgbd.repository;

import be.iramps.florencemary.devsgbd.model.Facture;
import org.springframework.data.repository.CrudRepository;

public interface FactureRepository extends CrudRepository<Facture, Long> {
}
