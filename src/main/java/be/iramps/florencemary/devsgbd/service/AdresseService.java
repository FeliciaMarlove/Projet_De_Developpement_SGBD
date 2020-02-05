package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.AdresseDto;
import be.iramps.florencemary.devsgbd.model.Adresse;

import java.util.List;

public interface AdresseService {
    List<Adresse> read();
    Adresse readOne(Long id);
    void create(AdresseDto newItem);
    Adresse update(Long id, AdresseDto update);
    Adresse delete(Long id);
}
