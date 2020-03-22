package be.iramps.florencemary.devsgbd.repository;

import be.iramps.florencemary.devsgbd.model.Departement;
import org.springframework.data.repository.CrudRepository;

public interface DepartementRepository extends CrudRepository<Departement, Long> {
    Departement findDepartementByNomDepartement(String name);
}
