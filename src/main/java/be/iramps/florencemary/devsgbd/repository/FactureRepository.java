package be.iramps.florencemary.devsgbd.repository;

import be.iramps.florencemary.devsgbd.model.Facture;
import org.springframework.data.repository.CrudRepository;

interface FactureRepository extends CrudRepository<Facture, Long> {
}
