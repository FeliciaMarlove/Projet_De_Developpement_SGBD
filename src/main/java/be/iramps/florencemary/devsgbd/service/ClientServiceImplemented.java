package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.ClientDto;
import be.iramps.florencemary.devsgbd.model.Client;
import be.iramps.florencemary.devsgbd.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public Client create(ClientDto newItem) {
        Client newClient = new Client(
                newItem.getNomClient(),
                newItem.getPrenomClient(),
                newItem.getTelephoneClient(),
                newItem.getDateNaissanceClient()
        );
        if (equalsAny(newClient) == null) repository.save(newClient);
        return newClient;
    }

    @Override
    public Client update(Long id, ClientDto update) {
        Client toUpdate = repository.findById(id).get();
        if ((toUpdate != null) && exists(id)) {
            toUpdate.setNomClient(update.getNomClient());
            toUpdate.setPrenomClient(update.getPrenomClient());
            toUpdate.setTelephoneClient(update.getTelephoneClient());
            toUpdate.setDateNaissanceClient(update.getDateNaissanceClient());
            if (equalsAny(toUpdate) == null) repository.save(toUpdate);
        }
        return toUpdate;
    }

    @Override
    public Client delete(Long id) {
        if (exists(id)) {
            repository.findById(id).get().setActifClient(false);
            return readOne(id);
        }
        return null;
    }

    @Override
    public List<Client> readActive() {
        List<Client> actifs = new ArrayList<>();
        for (Client client : read()) {
            if (client.isActifClient()) actifs.add(client);
        }
        return actifs;
    }

    private boolean exists(Long id) {
        boolean exists = false;
        for (Client client : read()) {
            if ((client.isActifClient() == true) && (client.getIdClient() == id)) exists = true;
        }
        return exists;
    }

    private Client equalsAny(Client client) {
        for (Client clientCompared : read()) {
            if (client.equals(clientCompared)) return repository.findById(clientCompared.getIdClient()).get();
        }
        return null;
    }
}
