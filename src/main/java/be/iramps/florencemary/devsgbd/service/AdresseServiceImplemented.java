package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.AdresseDto;
import be.iramps.florencemary.devsgbd.model.Adresse;
import be.iramps.florencemary.devsgbd.repository.AdresseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdresseServiceImplemented implements AdresseService {
    private AdresseRepository repository;

    @Autowired
    public AdresseServiceImplemented(AdresseRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Adresse> read() {
        return null;
    }

    @Override
    public Adresse readOne(Long id) {
        return null;
    }

    @Override
    public void create(Adresse newItem) {

    }

    @Override
    public Adresse update(Long id, AdresseDto update) {
        return null;
    }

    @Override
    public Adresse delete(Long id) {
        return null;
    }
}
