package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.model.Departement;
import be.iramps.florencemary.devsgbd.model.Utilisateur;
import be.iramps.florencemary.devsgbd.dto.UtilisateurDto;
import be.iramps.florencemary.devsgbd.repository.DepartementRepository;
import be.iramps.florencemary.devsgbd.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurServiceImplemented implements UtilisateurService {
    private UtilisateurRepository repository;
    private DepartementRepository repositoryDepartement;

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
    public void create(UtilisateurDto newItem) {
        Departement findDepartement = repositoryDepartement.findById(newItem.getIdDepartement()).get();
        Utilisateur newUtilisateur = new Utilisateur(
                newItem.getNomUtilisateur(),
                newItem.getPrenomUtilisateur(),
                newItem.getLogin(),
                newItem.getMotDePasse(),
                newItem.getPoste(),
                findDepartement
        );
        repository.save(newUtilisateur);
    }

    @Override
    public Utilisateur update(Long id, UtilisateurDto update) {
        Utilisateur toUpdate = repository.findById(id).get();
        if (toUpdate != null) {
            toUpdate.setPrenomUtilisateur(update.getPrenomUtilisateur());
            toUpdate.setNomUtilisateur(update.getNomUtilisateur());
            toUpdate.setLogin(update.getLogin());
            toUpdate.setMotDePasse(update.getMotDePasse());
            toUpdate.setPoste(update.getPoste());
            toUpdate.setDepartement(repositoryDepartement.findById(update.getIdDepartement()).get());
            repository.save(toUpdate);
        }
        return toUpdate;
    }

    @Override
    public Utilisateur delete(Long id) {
        return readOne(id);
    }
}
