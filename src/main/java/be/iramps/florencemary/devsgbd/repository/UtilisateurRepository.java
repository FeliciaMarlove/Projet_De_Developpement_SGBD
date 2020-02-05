package be.iramps.florencemary.devsgbd.repository;

import be.iramps.florencemary.devsgbd.model.Utilisateur;
import org.springframework.data.repository.CrudRepository;

interface UtilisateurRepository extends CrudRepository<Utilisateur, Long> {
}
