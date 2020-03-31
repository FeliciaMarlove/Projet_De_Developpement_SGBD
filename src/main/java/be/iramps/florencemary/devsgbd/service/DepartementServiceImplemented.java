package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.DepartementDto;
import be.iramps.florencemary.devsgbd.model.Departement;
import be.iramps.florencemary.devsgbd.repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service contenant la couche business sur l'entite Departement
 */
@Service
public class DepartementServiceImplemented implements DepartementService {
    private DepartementRepository repository;

    @Autowired
    public DepartementServiceImplemented(DepartementRepository repository) {
        this.repository = repository;
    }

    /**
     * Retourne les departements en DB
     * @return List Departement tous les departements en DB
     */
    @Override
    public List<Departement> read() {
        return (List<Departement>) repository.findAll();
    }

    /**
     * Retourne un departement
     * @param name (String) : nom du departement a retourner
     * @return DepartementDto departement trouve || null si pas de correspondance
     */
    @Override
    public DepartementDto readOne(String name) {
        for (Departement departement : repository.findAll()) {
            if (departement.getNomDepartement().equals(name)) {
                return mapEntityToDto(repository.findDepartementByNomDepartement(name));
            }
        }
        return null;
    }

    /**
     * Retourne les departements actifs
     * @return List DepartementDto departements actifs
     */
    @Override
    public List<DepartementDto> readActive() {
        List<Departement> actifs = new ArrayList<>();
        for (Departement departement : read()) {
            if (departement.isActifDepartement()) actifs.add(departement);
        }
        return mapEntitiesToDtos(actifs);
    }

    /**
     * Cree un enregistrement de departement en DB
     * @param newItem (DepartementDto) : departement a creer
     * @return DepartementDto departement cree || null si le departement existait deja en DB
     */
    @Override
    public DepartementDto create(DepartementDto newItem) {
        Departement newDepartement = new Departement(newItem.getNomDepartement());
        if (equalsAny(newDepartement) == null) {
            repository.save(newDepartement);
            return mapEntityToDto(newDepartement);
        }
        return null;
    }

    /**
     * Met a jour un departement en DB
     * @param id (Long) : id du departement a modifier
     * @param update (DepartementDto) : departement modifie
     * @return DepartementDto departement modifie || null si le departement n'a pas ete trouve en DB
     */
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

    /**
     * Supprime logiquement un departement
     * @param name (String) : nom du departement a supprimer
     * @return DepartementDto departement supprime
     */
    @Override
    public DepartementDto delete(String name) {
        Departement departement = repository.findDepartementByNomDepartement(name);
        if (exists(departement.getIdDepartement())) {
            departement.setActifDepartement(false);
            repository.save(departement);
            return readOne(name);
        }
        return null;
    }

    //__________________PRIVATE METHODS_________________________________________________________________________________

    private boolean exists(Long id) {
        for (Departement departement : read()) {
            if ((departement.isActifDepartement()) && (departement.getIdDepartement().equals(id))) {
                return true;
            }
        }
        return false;
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
