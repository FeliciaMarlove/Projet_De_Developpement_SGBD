package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.ConnectionMessenger;
import be.iramps.florencemary.devsgbd.model.Utilisateur;
import be.iramps.florencemary.devsgbd.dto.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {
    List<Utilisateur> read();
    UtilisateurDto readOne(Long id);
    UtilisateurDto create(UtilisateurDto newItem);
    UtilisateurDto update(String login, UtilisateurDto update);
    UtilisateurDto delete(String login);
    List<UtilisateurDto> readActive();
    ConnectionMessenger connectUser(String login, String motDePasse);
}
