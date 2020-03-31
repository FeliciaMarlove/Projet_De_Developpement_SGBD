package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.ClientDtoGet;
import be.iramps.florencemary.devsgbd.dto.ClientDtoPost;
import be.iramps.florencemary.devsgbd.model.Client;

import java.util.List;

/**
 * Interface non documentee : Ref Classe Implementee
 */
public interface ClientService {
    List<Client> read();
    ClientDtoGet readOne(Long id);
    ClientDtoPost create(ClientDtoPost newItem);
    ClientDtoPost update(Long id, ClientDtoPost update);
    ClientDtoGet delete(Long id);
    List<ClientDtoGet> readActive();
}
