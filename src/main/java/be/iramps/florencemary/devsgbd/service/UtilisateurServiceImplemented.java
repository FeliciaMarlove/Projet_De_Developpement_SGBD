package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.model.Utilisateur;
import be.iramps.florencemary.devsgbd.dto.UtilisateurDto;
import be.iramps.florencemary.devsgbd.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurServiceImplemented implements UtilisateurService {
    private UtilisateurRepository repository;

    @Autowired
    public UtilisateurServiceImplemented(UtilisateurRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Utilisateur> read() {
        return (List<Utilisateur>) repository.findAll();
    }

    @Override
    public Utilisateur readOne(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public void create(Utilisateur newItem) {
        repository.save(newItem);
    }

    @Override
    public Utilisateur update(Long id, UtilisateurDto update) {
        return null;
    }

    @Override
    public Utilisateur delete(Long id) {
        return readOne(id);
    }
}
