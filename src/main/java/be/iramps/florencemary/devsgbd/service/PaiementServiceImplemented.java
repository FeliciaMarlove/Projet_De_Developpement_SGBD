package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.PaiementDto;
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
    public Paiement readOne(Long id) {
        for (Paiement paiement: repository.findAll()) {
            if (paiement.getIdPaiement().equals(id)) {
                return repository.findById(id).get();
            }
        }
        return null;
    }

    @Override
    public Paiement create(PaiementDto newItem) {
        Paiement newPaiement = new Paiement(newItem.getNomPaiement(), newItem.getDescPaiement());
        if (equalsAny(newPaiement) == null) {
            repository.save(newPaiement);
            return newPaiement;
        }
        return null;
    }

    @Override
    public Paiement update(Long id, PaiementDto update) {
        if (exists(id)) {
            Paiement toUpdate = repository.findById(id).get();
            toUpdate.setNomPaiement(update.getNomPaiement());
            toUpdate.setDescPaiement(update.getDescPaiement());
            repository.save(toUpdate);
            return toUpdate;
        }
        return null;
    }

    @Override
    public Paiement delete(Long id) {
        Paiement paiement = repository.findById(id).get();
        if (exists(id)) {
            paiement.setActifPaiement(false);
            repository.save(paiement);
            return readOne(id);
        }
        return null;
    }

    @Override
    public List<Paiement> readActive() {
        List<Paiement> actifs = new ArrayList<>();
        for (Paiement paiement : read()) {
            if (paiement.isActifPaiement()) actifs.add(paiement);
        }
        return actifs;
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

    private Paiement equalsAny(PaiementDto paiementDto) {
        for (Paiement paiementCompared : read()) {
            if (paiementCompared.getNomPaiement().equals(paiementDto.getNomPaiement())) return repository.findById(paiementCompared.getIdPaiement()).get();
        }
        return null;
    }
}
