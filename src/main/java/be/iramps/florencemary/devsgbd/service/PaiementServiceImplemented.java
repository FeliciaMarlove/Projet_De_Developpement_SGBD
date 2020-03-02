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
        return repository.findById(id).get();
    }

    @Override
    public void create(PaiementDto newItem) {
        Paiement newPaiement = new Paiement(newItem.getNomPaiement(), newItem.getDescPaiement());
        if (equalsAny(newPaiement) == null) repository.save(newPaiement);
    }

    @Override
    public Paiement update(Long id, PaiementDto update) {
        Paiement toUpdate = repository.findById(id).get();
        if ((toUpdate != null) && exists(id)) {
            toUpdate.setNomPaiement(update.getNomPaiement());
            toUpdate.setDescPaiement(update.getDescPaiement());
            if (equalsAny(toUpdate) == null) repository.save(toUpdate);
        }
        return toUpdate;
    }

    @Override
    public Paiement delete(Long id) {
        if (exists(id)) {
            repository.findById(id).get().setActifPaiement(false);
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
}
