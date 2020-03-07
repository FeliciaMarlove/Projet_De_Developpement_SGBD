package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.ClientDto;
import be.iramps.florencemary.devsgbd.model.Client;

import java.util.List;

public interface ClientService {
    List<Client> read();
    ClientDto readOne(Long id);
    ClientDto create(ClientDto newItem);
    ClientDto update(Long id, ClientDto update);
    ClientDto delete(Long id);
    List<ClientDto> readActive();
}
