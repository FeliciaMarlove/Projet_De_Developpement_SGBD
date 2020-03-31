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

/**
 * Service contenant la couche business sur l'entite Utilisateur
 */
@Service
public class UtilisateurServiceImplemented implements UtilisateurService {
    private UtilisateurRepository repository;
    private DepartementRepository repositoryDepartement;

    @Autowired
    public UtilisateurServiceImplemented(UtilisateurRepository repository, DepartementRepository repositoryDepartement) {
        this.repository = repository;
        this.repositoryDepartement = repositoryDepartement;
    }

    /**
     * Retourne les utilisateurs
     * @return List Utilisateur tous les utilisateurs en DB
     */
    @Override
    public List<Utilisateur> read() {
        return (List<Utilisateur>) repository.findAll();
    }

    /**
     * Retourne un utilisateur
     * @param id (Long) : id de l'utilisateur a retourner
     * @return UtilisateurDto l'utilisateur trouve || null si l'id n'est pas trouve
     */
    @Override
    public UtilisateurDto readOne(Long id) {
        for (Utilisateur utilisateur : repository.findAll()) {
            if (utilisateur.getIdUtilisateur().equals(id)) {
                return mapEntityToDto(repository.findById(id).get());
            }
        }
        return null;
    }

    /**
     * Retourne les utilisateurs actifs
     * @return List UtilisateurDto utilisateurs actifs
     */
    @Override
    public List<UtilisateurDto> readActive() {
        List<Utilisateur> actifs = new ArrayList<>();
        for (Utilisateur utilisateur : read()) {
            if (utilisateur.isActifUtilisateur()) actifs.add(utilisateur);
        }
        return mapEntitiesToDtos(actifs);
    }

    /**
     * Cree un utilisateur
     * @param newItem (UtilisateurDto) : utilisateur a creer
     * @return UtilisateurDto l'utilisateur cree
     */
    @Override
    public UtilisateurDto create(UtilisateurDto newItem) {
        if (equalsAny(newItem) == null) {
            Departement findDepartement = repositoryDepartement.findById(repositoryDepartement.findDepartementByNomDepartement(newItem.getNomDepartement()).getIdDepartement()).get();
            Utilisateur newUtilisateur = new Utilisateur(
                    newItem.getNomUtilisateur(),
                    newItem.getPrenomUtilisateur(),
                    newItem.getLogin(),
                    newItem.getMotDePasse(),
                    newItem.getPoste(),
                    findDepartement
            );
            repository.save(newUtilisateur);
            return mapEntityToDto(newUtilisateur);
        }
        return null;
    }

    /**
     * Met a jour un utilisateur
     * @param login (String) : login de l'utilisateur a mettre a jour
     * @param update (UtilisateurDto) : utilisateur a modifier
     * @return UtilisateurDto utilisateur mis a jour || null si l'utilisateur n'est pas trouve en DB
     */
    @Override
    public UtilisateurDto update(String login, UtilisateurDto update) {
        Departement dpt = repositoryDepartement.findDepartementByNomDepartement(update.getNomDepartement());
        Utilisateur util = repository.findUtilisateurByLogin(login);
        Long id = util.getIdUtilisateur();
        if (exists(id)) {
            util.setPrenomUtilisateur(update.getPrenomUtilisateur());
            util.setNomUtilisateur(update.getNomUtilisateur());
            util.setLogin(update.getLogin());
            util.setPoste(update.getPoste());
            util.setDepartement(dpt);
            repository.save(util);
            return mapEntityToDto(util);
        }
        return null;
    }

    /**
     * Supprime logiquement un utilisateur
     * @param login (String) : login de l'utilisateur a supprimer
     * @return UtilisateurDto l'utilisateur supprime || null si l'utilisateur n'est pas trouve
     */
    @Override
    public UtilisateurDto delete(String login) {
        Utilisateur utilisateur = repository.findUtilisateurByLogin(login);
        Long id = utilisateur.getIdUtilisateur();
        if (exists(id)) {
            utilisateur.setActifUtilisateur(false);
            repository.save(utilisateur);
            return readOne(id);
        }
        return null;
    }

    /**
     * Connexion a l'application. Teste si l'utilisateur existe en DB et si le mot de passe correspond a celui de l'utilisateur sur base de son login.
     * @param login (String) : login de l'utilisateur
     * @param motDePasse (String) : mot de passe
     * @return ConnectionMessenger en fonction des conditions vérifiées
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

    //__________________PRIVATE METHODS_________________________________________________________________________________

    private boolean exists(Long id) {
        for (Utilisateur utilisateur : read()) {
            if ((utilisateur.isActifUtilisateur()) && (utilisateur.getIdUtilisateur().equals(id))) {
                return true;
            }
        }
        return false;
    }

    private Utilisateur equalsAny(Utilisateur utilisateur) {
        for (Utilisateur utlisateurCompared : read()) {
            if (utilisateur.equals(utlisateurCompared))
                return repository.findById(utlisateurCompared.getIdUtilisateur()).get();
        }
        return null;
    }

    private UtilisateurDto equalsAny(UtilisateurDto utilisateurDto) {
        for (Utilisateur utlisateurCompared : read()) {
            if (((utilisateurDto.getNomUtilisateur().equals(utlisateurCompared.getNomUtilisateur())
                    && (utilisateurDto.getPrenomUtilisateur().equals(utlisateurCompared.getPrenomUtilisateur())))
                    || (utilisateurDto.getLogin().equals(utlisateurCompared.getLogin())))) {
                return mapEntityToDto(repository.findById(utlisateurCompared.getIdUtilisateur()).get());
            }
        }
        return null;
    }



    private UtilisateurDto mapEntityToDto(Utilisateur utilisateur) {
        return new UtilisateurDto(utilisateur.getNomUtilisateur(), utilisateur.getPrenomUtilisateur(), utilisateur.getLogin(), utilisateur.getMotDePasse(), utilisateur.getPoste(), utilisateur.getDepartement().getNomDepartement());
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
