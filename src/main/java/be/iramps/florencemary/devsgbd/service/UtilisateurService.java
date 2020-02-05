package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.model.Utilisateur;
import be.iramps.florencemary.devsgbd.dto.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {
    List<Utilisateur> read();
    Utilisateur readOne(Long id);
    void create(Utilisateur newItem);
    Utilisateur update(Long id, UtilisateurDto update);
    Utilisateur delete(Long id);
}
