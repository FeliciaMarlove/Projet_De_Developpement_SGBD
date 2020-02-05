package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.DepartementDto;
import be.iramps.florencemary.devsgbd.model.Departement;

import java.util.List;

public interface DepartementService {
    List<Departement> read();
    Departement readOne(Long id);
    void create(Departement newItem);
    Departement update(Long id, DepartementDto update);
    Departement delete(Long id);
}
