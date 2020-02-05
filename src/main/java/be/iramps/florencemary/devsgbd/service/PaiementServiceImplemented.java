package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.PaiementDto;
import be.iramps.florencemary.devsgbd.model.Paiement;
import be.iramps.florencemary.devsgbd.repository.AdresseRepository;
import be.iramps.florencemary.devsgbd.repository.PaiementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void create(Paiement newItem) {
        repository.save(newItem);
    }

    @Override
    public Paiement update(Long id, PaiementDto update) {
        Paiement toUpdate = repository.findById(id).get();
        if (toUpdate != null) {
            toUpdate.setNomPaiement(update.getNomPaiement());
            toUpdate.setDescPaiement(update.getDescPaiement());
            repository.save(toUpdate);
        }
        return toUpdate;
    }

    @Override
    public Paiement delete(Long id) {
        return readOne(id);
    }
}
