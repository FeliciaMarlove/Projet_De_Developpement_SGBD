package be.iramps.florencemary.devsgbd.repository;

import be.iramps.florencemary.devsgbd.model.Departement;
import org.springframework.data.repository.CrudRepository;

/**
 * CRUD repository (DAO) sur l'entite Departement
 */
public interface DepartementRepository extends CrudRepository<Departement, Long> {
    /**
     * Trouve un departement par son nom
     * @param name (String) nom du departement à chercher
     * @return Departement le departement correspondant au login
     */
    Departement findDepartementByNomDepartement(String name);
}
