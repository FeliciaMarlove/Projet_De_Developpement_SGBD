package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.ClientDto;
import be.iramps.florencemary.devsgbd.model.Client;
import be.iramps.florencemary.devsgbd.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return null;
    }

    @Override
    public Client readOne(Long id) {
        return null;
    }

    @Override
    public void create(Client newItem) {

    }

    @Override
    public Client update(Long id, ClientDto update) {
        return null;
    }

    @Override
    public Client delete(Long id) {
        return null;
    }
}
