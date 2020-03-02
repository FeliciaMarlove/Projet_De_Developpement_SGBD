package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.PaiementDto;
import be.iramps.florencemary.devsgbd.model.Paiement;

import java.util.List;

public interface PaiementService {
    List<Paiement> read();
    Paiement readOne(Long id);
    Paiement create(PaiementDto newItem);
    Paiement update(Long id, PaiementDto update);
    Paiement delete(Long id);
    List<Paiement> readActive();
}
