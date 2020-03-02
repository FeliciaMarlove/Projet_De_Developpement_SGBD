package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.ClientDto;
import be.iramps.florencemary.devsgbd.model.Client;

import java.util.List;

public interface ClientService {
    List<Client> read();
    Client readOne(Long id);
    Client create(ClientDto newItem);
    Client update(Long id, ClientDto update);
    Client delete(Long id);
    List<Client> readActive();
}
