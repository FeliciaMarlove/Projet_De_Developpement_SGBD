package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.DepartementDto;
import be.iramps.florencemary.devsgbd.model.Departement;
import be.iramps.florencemary.devsgbd.repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return null;
    }

    @Override
    public Departement readOne(Long id) {
        return null;
    }

    @Override
    public void create(Departement newItem) {

    }

    @Override
    public Departement update(Long id, DepartementDto update) {
        return null;
    }

    @Override
    public Departement delete(Long id) {
        return null;
    }
}
