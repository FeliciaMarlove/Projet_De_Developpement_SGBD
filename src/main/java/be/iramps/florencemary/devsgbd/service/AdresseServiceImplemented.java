package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.AdresseDto;
import be.iramps.florencemary.devsgbd.model.Adresse;
import be.iramps.florencemary.devsgbd.model.Client;
import be.iramps.florencemary.devsgbd.repository.AdresseRepository;
import be.iramps.florencemary.devsgbd.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdresseServiceImplemented implements AdresseService {
    private AdresseRepository repository;
    private ClientRepository repositoryClient;

    @Autowired
    public AdresseServiceImplemented(AdresseRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Adresse> read() {
        return (List<Adresse>) repository.findAll();
    }

    @Override
    public Adresse readOne(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public void create(AdresseDto newItem) {
        Client findClient = repositoryClient.findById(newItem.getIdClient()).get();
        Adresse newAdresse = new Adresse(
                newItem.getRue(),
                newItem.getNumero(),
                newItem.getCodePostal(),
                newItem.getVille(),
                newItem.getPays(),
                findClient
        );
        repository.save(newAdresse);
    }

    @Override
    public Adresse update(Long id, AdresseDto update) {
        Adresse toUpdate = repository.findById(id).get();
        if ((toUpdate != null) && exists(id)) {
            toUpdate.setRue(update.getRue());
            toUpdate.setNumero(update.getNumero());
            toUpdate.setCodePostal(update.getCodePostal());
            toUpdate.setVille(update.getVille());
            toUpdate.setPays(update.getPays());
            toUpdate.setClient(repositoryClient.findById(update.getIdClient()).get());
            repository.save(toUpdate);
        }
        return toUpdate;
    }

    @Override
    public Adresse delete(Long id) {
        if (exists(id)) {
            repository.findById(id).get().setActifAdresse(false);
            return readOne(id);
        }
        return null;
    }

    private boolean exists(Long id) {
        boolean exists = false;
        for (Adresse adresse : read()) {
            if ((adresse.isActifAdresse() == true) && (adresse.getIdAdresse() == id)) exists = true;
        }
        return exists;
    }
}
