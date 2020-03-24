package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.PaiementDtoGet;
import be.iramps.florencemary.devsgbd.dto.PaiementDtoPost;
import be.iramps.florencemary.devsgbd.model.Paiement;

import java.util.List;

public interface PaiementService {
    List<Paiement> read();
    PaiementDtoGet readOne(Long id);
    PaiementDtoGet create(PaiementDtoPost newItem);
    PaiementDtoGet update(Long id, PaiementDtoPost update);
    PaiementDtoGet delete(Long id);
    List<PaiementDtoGet> readActive();
}
