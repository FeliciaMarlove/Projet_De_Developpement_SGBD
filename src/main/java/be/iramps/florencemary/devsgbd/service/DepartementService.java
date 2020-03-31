package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.DepartementDto;
import be.iramps.florencemary.devsgbd.model.Departement;

import java.util.List;

/**
 * Interface non documentee : Ref Classe Implementee
 */
public interface DepartementService {
    List<Departement> read();
    DepartementDto readOne(String deptName);
    DepartementDto create(DepartementDto newItem);
    DepartementDto update(Long id, DepartementDto update);
    DepartementDto delete(String deptName);
    List<DepartementDto> readActive();
}
