package be.iramps.florencemary.devsgbd.repository;

import be.iramps.florencemary.devsgbd.model.Departement;
import org.springframework.data.repository.CrudRepository;

interface DepartementRepository extends CrudRepository<Departement, Long> {
}
