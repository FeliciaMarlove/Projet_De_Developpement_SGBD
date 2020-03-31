package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.AdresseDtoGet;
import be.iramps.florencemary.devsgbd.dto.AdresseDtoPost;
import be.iramps.florencemary.devsgbd.model.Adresse;

import java.util.List;

/**
 * Interface non documentee : Ref Classe Implementee
 */
public interface AdresseService {
    List<Adresse> read();
    AdresseDtoGet readOne(Long id);
    List<AdresseDtoPost> create(Long idClient, AdresseDtoPost newItem);
    AdresseDtoPost update(Long id, AdresseDtoPost update);
    AdresseDtoGet delete(Long id);
    List<AdresseDtoGet> readActive();
    List<AdresseDtoGet> readFromClient(Long idClient);
}
