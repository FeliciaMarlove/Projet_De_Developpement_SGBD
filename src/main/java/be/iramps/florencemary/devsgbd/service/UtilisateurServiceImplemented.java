package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.ConnectionMessenger;
import be.iramps.florencemary.devsgbd.model.Departement;
import be.iramps.florencemary.devsgbd.model.Utilisateur;
import be.iramps.florencemary.devsgbd.dto.UtilisateurDto;
import be.iramps.florencemary.devsgbd.repository.DepartementRepository;
import be.iramps.florencemary.devsgbd.repository.UtilisateurRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UtilisateurServiceImplemented implements UtilisateurService {
    private UtilisateurRepository repository;
    private DepartementRepository repositoryDepartement;

    @Autowired
    public UtilisateurServiceImplemented(UtilisateurRepository repository, DepartementRepository repositoryDepartement) {
        this.repository = repository;
        this.repositoryDepartement = repositoryDepartement;
    }

    @Override
    public List<Utilisateur> read() {
        return (List<Utilisateur>) repository.findAll();
    }

    @Override
    public UtilisateurDto readOne(Long id) {
        for (Utilisateur utilisateur : repository.findAll()) {
            if (utilisateur.getIdUtilisateur().equals(id)) {
                return mapEntityToDto(repository.findById(id).get());
            }
        }
        return null;
    }

    @Override
    public UtilisateurDto create(UtilisateurDto newItem) {
        Departement findDepartement = repositoryDepartement.findById(newItem.getIdDepartement()).get();
        Utilisateur newUtilisateur = new Utilisateur(
                newItem.getNomUtilisateur(),
                newItem.getPrenomUtilisateur(),
                newItem.getLogin(),
                newItem.getMotDePasse(),
                newItem.getPoste(),
                findDepartement
        );
        if (equalsAny(newItem) == null) {
            repository.save(newUtilisateur);
            return mapEntityToDto(newUtilisateur);
        }
        return null;
    }

    @Override
    public UtilisateurDto update(Long id, UtilisateurDto update) {
        if (exists(id)) {
            Utilisateur toUpdate = repository.findById(id).get();
            toUpdate.setPrenomUtilisateur(update.getPrenomUtilisateur());
            toUpdate.setNomUtilisateur(update.getNomUtilisateur());
            toUpdate.setLogin(update.getLogin());
            toUpdate.setMotDePasse(update.getMotDePasse());
            toUpdate.setPoste(update.getPoste());
            toUpdate.setDepartement(repositoryDepartement.findById(update.getIdDepartement()).get());
            repository.save(toUpdate);
            return mapEntityToDto(toUpdate);
        }
        return null;
    }

    @Override
    public UtilisateurDto delete(Long id) {
        Utilisateur utilisateur = repository.findById(id).get();
        if (exists(id)) {
            utilisateur.setActifUtilisateur(false);
            repository.save(utilisateur);
            return readOne(id);
        }
        return null;
    }

    @Override
    public List<UtilisateurDto> readActive() {
        List<Utilisateur> actifs = new ArrayList<>();
        for (Utilisateur utilisateur : read()) {
            if (utilisateur.isActifUtilisateur()) actifs.add(utilisateur);
        }
        return mapEntitiesToDtos(actifs);
    }

    /**
     * Fonction qui vérifie si l'Utilisateur est présent en DB sur base de l'Id
     *
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
     *
     * @param utilisateur
     * @return Utilisateur où "null" signifie qu'il n'y a aucune correspondance (aucun doublon)
     */
    private Utilisateur equalsAny(Utilisateur utilisateur) {
        for (Utilisateur utlisateurCompared : read()) {
            if (utilisateur.equals(utlisateurCompared))
                return repository.findById(utlisateurCompared.getIdUtilisateur()).get();
        }
        return null;
    }

    private UtilisateurDto equalsAny(UtilisateurDto utilisateurDto) {
        for (Utilisateur utlisateurCompared : read()) {
            if ((utilisateurDto.getNomUtilisateur().equals(utlisateurCompared.getNomUtilisateur())
                    && (utilisateurDto.getPrenomUtilisateur().equals(utlisateurCompared.getPrenomUtilisateur()))
                    && (utilisateurDto.getLogin().equals(utlisateurCompared.getLogin())))) {
                return mapEntityToDto(repository.findById(utlisateurCompared.getIdUtilisateur()).get());
            }
        }
        return null;
    }

    /**
     * Fonction de connexion à l'application qui vérifie le legin et le mot de passe d'un utilisateur et retourne un objet ConnectionMessenger
     *
     * @param login      string
     * @param motDePasse string
     * @return ConnectionMessenger (contient l'ID de l'utilisateur s'il existe, un message d'erreur, un booléen et un code d'erreur)
     */
    public ConnectionMessenger connectUser(String login, String motDePasse) {
        ConnectionMessenger connectionMessenger = new ConnectionMessenger(0L, "Echec de connexion, veuillez réessayer. Si le problème persiste, contactez l'administrateur", false, 0);
        for (Utilisateur user : readActiveForConnection()) {
            if (user.getLogin().equals(login)) {
                if (BCrypt.checkpw(motDePasse, user.getMotDePasse())) {
                    return new ConnectionMessenger(user.getIdUtilisateur(), "Connexion réussie", true, 1);
                } else {
                    return new ConnectionMessenger(user.getIdUtilisateur(), "Mot de passe incorrect", false, 2);
                }
            }
        }
        return new ConnectionMessenger(0L, "Le login " + login + " n'existe pas", false, 3);
    }

    private UtilisateurDto mapEntityToDto(Utilisateur utilisateur) {
        return new UtilisateurDto(utilisateur.getNomUtilisateur(), utilisateur.getPrenomUtilisateur(), utilisateur.getLogin(), utilisateur.getMotDePasse(), utilisateur.getPoste(), utilisateur.getDepartement().getIdDepartement());
    }

    private List<UtilisateurDto> mapEntitiesToDtos(List<Utilisateur> utilisateurs) {
        List<UtilisateurDto> dtos = new ArrayList<>();
        for (Utilisateur util : utilisateurs) {
            dtos.add(mapEntityToDto(util));
        }
        return dtos;
    }

    private List<Utilisateur> readActiveForConnection() {
        List<Utilisateur> actifs = new ArrayList<>();
        for (Utilisateur utilisateur : read()) {
            if (utilisateur.isActifUtilisateur()) actifs.add(utilisateur);
        }
        return actifs;
    }
}
