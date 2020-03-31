package be.iramps.florencemary.devsgbd.repository;

import be.iramps.florencemary.devsgbd.model.Utilisateur;
import org.springframework.data.repository.CrudRepository;

/**
 * CRUD repository (DAO) sur l'entite Utilisateur
 */
public interface UtilisateurRepository extends CrudRepository<Utilisateur, Long> {
    /**
     * Trouve un utilisateur par son login
     * @param login (String) login de l'utilisateur à chercher
     * @return Utilisateur l'utilisateur correspondant au login
     */
    Utilisateur findUtilisateurByLogin(String login);
}
