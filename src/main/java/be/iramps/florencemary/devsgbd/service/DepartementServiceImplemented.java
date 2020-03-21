package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.DepartementDto;
import be.iramps.florencemary.devsgbd.model.Departement;
import be.iramps.florencemary.devsgbd.repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartementServiceImplemented implements DepartementService {
    private DepartementRepository repository;

    @Autowired
    public DepartementServiceImplemented(DepartementRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Departement> read() {
        return (List<Departement>) repository.findAll();
    }

    @Override
    public DepartementDto readOne(Long id) {
        for (Departement departement : repository.findAll()) {
            if (departement.getIdDepartement().equals(id)) {
                return mapEntityToDto(repository.findById(id).get());
            }
        }
        return null;
    }

    @Override
    public DepartementDto create(DepartementDto newItem) {
        Departement newDepartement = new Departement(newItem.getNomDepartement());
        if (equalsAny(newDepartement) == null) {
            repository.save(newDepartement);
            return mapEntityToDto(newDepartement);
        }
        return null;
    }

    @Override
    public DepartementDto update(Long id, DepartementDto update) {
        if (exists(id)) {
            Departement toUpdate = repository.findById(id).get();
            toUpdate.setNomDepartement(update.getNomDepartement());
            repository.save(toUpdate);
            return mapEntityToDto(toUpdate);
        }
        return null;
    }

    @Override
    public DepartementDto delete(Long id) {
        Departement departement = repository.findById(id).get();
        if (exists(id)) {
            departement.setActifDepartement(false);
            repository.save(departement);
            return readOne(id);
        }
        return null;
    }

    @Override
    public List<DepartementDto> readActive() {
        List<Departement> actifs = new ArrayList<>();
        for (Departement departement : read()) {
            if (departement.isActifDepartement()) actifs.add(departement);
        }
        return mapEntitiesToDtos(actifs);
    }

    private boolean exists(Long id) {
        boolean exists = false;
        for (Departement departement : read()) {
            if ((departement.isActifDepartement() == true) && (departement.getIdDepartement() == id)) exists = true;
        }
        return exists;
    }

    private Departement equalsAny(Departement departement) {
        for (Departement departementCompared : read()) {
            if (departement.equals(departementCompared))
                return repository.findById(departementCompared.getIdDepartement()).get();
        }
        return null;
    }

    private Departement equalsAny(DepartementDto departementDto) {
        for (Departement departementCompared : read()) {
            if (departementDto.getNomDepartement().equals(departementCompared.getNomDepartement()))
                return repository.findById(departementCompared.getIdDepartement()).get();
        }
        return null;
    }

    private DepartementDto mapEntityToDto(Departement departement) {
        return new DepartementDto(departement.getNomDepartement());
    }

    private List<DepartementDto> mapEntitiesToDtos(List<Departement> departements) {
        List<DepartementDto> dtos = new ArrayList<>();
        for (Departement dept : departements) {
            dtos.add(mapEntityToDto(dept));
        }
        return dtos;
    }

}
