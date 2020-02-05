package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.FactureDto;
import be.iramps.florencemary.devsgbd.model.Facture;
import be.iramps.florencemary.devsgbd.repository.FactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactureServiceImplemented implements FactureService {
    private FactureRepository repository;

    @Autowired
    public FactureServiceImplemented(FactureRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Facture> read() {
        return (List<Facture>) repository.findAll();
    }

    @Override
    public Facture readOne(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public void create(Facture newItem) {
        repository.save(newItem);
    }

    @Override
    public Facture update(Long id, FactureDto update) {
        return null;
    }

    @Override
    public Facture delete(Long id) {
        return readOne(id);
    }
}
