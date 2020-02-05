package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.ClientDto;
import be.iramps.florencemary.devsgbd.model.Client;
import be.iramps.florencemary.devsgbd.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Callable;

@Service
public class ClientServiceImplemented implements ClientService {
    private ClientRepository repository;

    @Autowired
    public ClientServiceImplemented(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Client> read() {
        return (List<Client>) repository.findAll();
    }

    @Override
    public Client readOne(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public void create(ClientDto newItem) {
        Client newClient = new Client(
                newItem.getNomClient(),
                newItem.getPrenomClient(),
                newItem.getTelephoneClient(),
                newItem.getDateNaissanceClient()
        );
        repository.save(newClient);
    }

    @Override
    public Client update(Long id, ClientDto update) {
        Client toUpdate = repository.findById(id).get();
        if (toUpdate != null) {
            toUpdate.setNomClient(update.getNomClient());
            toUpdate.setPrenomClient(update.getPrenomClient());
            toUpdate.setTelephoneClient(update.getTelephoneClient());
            toUpdate.setDateNaissanceClient(update.getDateNaissanceClient());
            repository.save(toUpdate);
        }
        return toUpdate;
    }

    @Override
    public Client delete(Long id) {
        return readOne(id);
    }
}
