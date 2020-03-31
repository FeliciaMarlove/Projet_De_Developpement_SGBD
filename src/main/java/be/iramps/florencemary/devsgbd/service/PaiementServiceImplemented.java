package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.PaiementDtoGet;
import be.iramps.florencemary.devsgbd.dto.PaiementDtoPost;
import be.iramps.florencemary.devsgbd.model.Paiement;
import be.iramps.florencemary.devsgbd.repository.PaiementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service contenant la couche business sur l'entite Paiement
 */
@Service
public class PaiementServiceImplemented implements PaiementService {
    private PaiementRepository repository;

    @Autowired
    public PaiementServiceImplemented(PaiementRepository repository) {
        this.repository = repository;
    }

    /**
     * Retourne la liste des paiements
     * @return List Paiement tous les paiements en DB
     */
    @Override
    public List<Paiement> read() {
        return (List<Paiement>) repository.findAll();
    }

    /**
     * Retourne un paiement
     * @param id (Long) : id du paiement a retourner
     * @return le paiement trouve || null si le paiement n'est pas trouve
     */
    @Override
    public PaiementDtoGet readOne(Long id) {
        for (Paiement paiement: repository.findAll()) {
            if (paiement.getIdPaiement().equals(id)) {
                return mapEntityToDtoGet(repository.findById(id).get());
            }
        }
        return null;
    }

    /**
     * Retourne les paiements actifs
     * @return List PaiementDtoGet paiements actifs
     */
    @Override
    public List<PaiementDtoGet> readActive() {
        List<Paiement> actifs = new ArrayList<>();
        for (Paiement paiement : read()) {
            if (paiement.isActifPaiement()) actifs.add(paiement);
        }
        return mapEntitiesToDtosGet(actifs);
    }

    /**
     * Cree un enregistrement de paiement en DB
     * @param newItem (PaiementDtoPost) : le paiement a enregistrer
     * @return PaiementDtoGet le paiement enregistre || null si le paiement est deja existant en DB
     */
    @Override
    public PaiementDtoGet create(PaiementDtoPost newItem) {
        Paiement newPaiement = new Paiement(newItem.getNomPaiement(), newItem.getDescPaiement());
        if (equalsAny(newPaiement) == null) {
            repository.save(newPaiement);
            return mapEntityToDtoGet(newPaiement);
        }
        return null;
    }

    /**
     * Met a jour un paiement
     * @param id (Long) : id du paiement a mettre a jour
     * @param update (PaiementDtoPost) : paiement a mettre a jour
     * @return PaiementDtoGet paiement mis a jour || null si le paiement n'a pas ete trouve
     */
    @Override
    public PaiementDtoGet update(Long id, PaiementDtoPost update) {
        if (exists(id)) {
            Paiement toUpdate = repository.findById(id).get();
            toUpdate.setNomPaiement(update.getNomPaiement());
            toUpdate.setDescPaiement(update.getDescPaiement());
            repository.save(toUpdate);
            return mapEntityToDtoGet(toUpdate);
        }
        return null;
    }

    /**
     * Supprime logiquement un paiement
     * @param id (Long) : id du paiement a supprimer
     * @return PaiementDtoGet le paiement correspondant || null si pas de paiement trouve
     */
    @Override
    public PaiementDtoGet delete(Long id) {
        Paiement paiement = repository.findById(id).get();
        if (exists(id)) {
            paiement.setActifPaiement(false);
            repository.save(paiement);
            return readOne(id);
        }
        return null;
    }

    //__________________PRIVATE METHODS_________________________________________________________________________________

    private boolean exists(Long id) {
        for (Paiement paiement : read()) {
            if ((paiement.isActifPaiement()) && (paiement.getIdPaiement().equals(id))) {
                return true;
            }
        }
        return false;
    }

    private Paiement equalsAny(Paiement paiement) {
        for (Paiement paiementCompared : read()) {
            if (paiement.equals(paiementCompared)) return repository.findById(paiementCompared.getIdPaiement()).get();
        }
        return null;
    }

    private Paiement equalsAny(PaiementDtoPost paiementDtoPost) {
        for (Paiement paiementCompared : read()) {
            if (paiementCompared.getNomPaiement().equals(paiementDtoPost.getNomPaiement())) return repository.findById(paiementCompared.getIdPaiement()).get();
        }
        return null;
    }

    private PaiementDtoGet mapEntityToDtoGet(Paiement paiement) {
        return new PaiementDtoGet(paiement.getIdPaiement(), paiement.getNomPaiement(), paiement.getDescPaiement());
    }

    private PaiementDtoPost mapEntityToDtoPost(Paiement paiement) {
        return new PaiementDtoPost(paiement.getNomPaiement(), paiement.getDescPaiement());
    }

    private List<PaiementDtoGet> mapEntitiesToDtosGet(List<Paiement> paiements) {
        List<PaiementDtoGet> dtos = new ArrayList<>();
        for (Paiement paiement: paiements) {
            dtos.add(mapEntityToDtoGet(paiement));
        }
        return dtos;
    }

    private List<PaiementDtoPost> mapEntitiesToDtosPost(List<Paiement> paiements) {
        List<PaiementDtoPost> dtos = new ArrayList<>();
        for (Paiement paiement: paiements) {
            dtos.add(mapEntityToDtoPost(paiement));
        }
        return dtos;
    }
}
