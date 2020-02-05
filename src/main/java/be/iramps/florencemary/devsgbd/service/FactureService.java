package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.FactureDto;
import be.iramps.florencemary.devsgbd.model.Facture;

import java.util.List;

public interface FactureService {
    List<Facture> read();
    Facture readOne(Long id);
    void create(Facture newItem);
    //Facture update(Long id, FactureDto update); //nonsense
    Facture delete(Long id);
}
