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
        if (equalsAny(newUtilisateur) == null) repository.save(newUtilisateur);
    }

    @Override
    public Utilisateur update(Long id, UtilisateurDto update) {
        Utilisateur toUpdate = repository.findById(id).get();
        if ((toUpdate != null) && exists(id)) {
            toUpdate.setPrenomUtilisateur(update.getPrenomUtilisateur());
            toUpdate.setNomUtilisateur(update.getNomUtilisateur());
            toUpdate.setLogin(update.getLogin());
            toUpdate.setMotDePasse(update.getMotDePasse());
            toUpdate.setPoste(update.getPoste());
            toUpdate.setDepartement(repositoryDepartement.findById(update.getIdDepartement()).get());
            if (equalsAny(toUpdate) == null) repository.save(toUpdate);
        }
        return toUpdate;
    }

    @Override
    public Utilisateur delete(Long id) {
        if (exists(id)) {
            repository.findById(id).get().setActifUtilisateur(false);
            return readOne(id);
        }
        return null;
    }

    /**
     * Fonction qui vérifie si l'Utilisateur est présent en DB sur base de l'Id
     * @param id
     * @return boolean où "true" signifie que l'Utilisateur correspondant à l'id est bien présent en DB
     */
    private boolean exists(Long id) {
        boolean exists = false;
        for (Utilisateur utilisateur : read()) {
            if ((utilisateur.isActifUtilisateur() == true) && (utilisateur.getIdUtilisateur() == id)) exists = true;
        }
        return exists;
    }

    /**
     * Fonction qui vérifie que l'Utilisateur n'est pas déjà présent dans la DB (basé sur la définition de equals)
     * @param utilisateur
     * @return Utilisateur où "null" signifie qu'il n'y a aucune correspondance (aucun doublon)
     */
    private Utilisateur equalsAny(Utilisateur utilisateur) {
        for (Utilisateur utlisateurCompared : read()) {
            if (utilisateur.equals(utlisateurCompared)) return repository.findById(utlisateurCompared.getIdUtilisateur()).get();
        }
        return null;
    }
}
