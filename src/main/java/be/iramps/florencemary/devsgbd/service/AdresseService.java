package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.AdresseDto;
import be.iramps.florencemary.devsgbd.model.Adresse;

import java.util.List;

public interface AdresseService {
    List<Adresse> read();
    AdresseDto readOne(Long id);
    List<AdresseDto> create(Long idClient, AdresseDto newItem);
    AdresseDto update(Long id, AdresseDto update);
    AdresseDto delete(Long id);
    List<AdresseDto> readActive();
}
