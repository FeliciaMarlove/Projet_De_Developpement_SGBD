package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.ConnectionMessenger;
import be.iramps.florencemary.devsgbd.model.Utilisateur;
import be.iramps.florencemary.devsgbd.dto.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {
    List<Utilisateur> read();
    Utilisateur readOne(Long id);
    void create(UtilisateurDto newItem);
    Utilisateur update(Long id, UtilisateurDto update);
    Utilisateur delete(Long id);
    List<Utilisateur> readActive();
    ConnectionMessenger connectUser(String login, String motDePasse);
}
