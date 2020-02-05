package be.iramps.florencemary.devsgbd.repository;

import be.iramps.florencemary.devsgbd.model.Paiement;
import org.springframework.data.repository.CrudRepository;

interface PaiementRepository extends CrudRepository<Paiement, Long> {
}
