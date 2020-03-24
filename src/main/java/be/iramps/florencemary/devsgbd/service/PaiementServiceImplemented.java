package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.PaiementDtoGet;
import be.iramps.florencemary.devsgbd.dto.PaiementDtoPost;
import be.iramps.florencemary.devsgbd.model.Paiement;
import be.iramps.florencemary.devsgbd.repository.PaiementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaiementServiceImplemented implements PaiementService {
    private PaiementRepository repository;

    @Autowired
    public PaiementServiceImplemented(PaiementRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Paiement> read() {
        return (List<Paiement>) repository.findAll();
    }

    @Override
    public PaiementDtoGet readOne(Long id) {
        for (Paiement paiement: repository.findAll()) {
            if (paiement.getIdPaiement().equals(id)) {
                return mapEntityToDtoGet(repository.findById(id).get());
            }
        }
        return null;
    }

    @Override
    public PaiementDtoGet create(PaiementDtoPost newItem) {
        Paiement newPaiement = new Paiement(newItem.getNomPaiement(), newItem.getDescPaiement());
        if (equalsAny(newPaiement) == null) {
            repository.save(newPaiement);
            return mapEntityToDtoGet(newPaiement);
        }
        return null;
    }

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

    @Override
    public List<PaiementDtoGet> readActive() {
        List<Paiement> actifs = new ArrayList<>();
        for (Paiement paiement : read()) {
            if (paiement.isActifPaiement()) actifs.add(paiement);
        }
        return mapEntitiesToDtosGet(actifs);
    }

    private boolean exists(Long id) {
        boolean exists = false;
        for (Paiement paiement : read()) {
            if ((paiement.isActifPaiement() == true) && (paiement.getIdPaiement() == id)) exists = true;
        }
        return exists;
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
