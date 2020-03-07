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
    public ClientDto readOne(Long id) {
        for (Client client: repository.findAll()) {
            if (client.getIdClient().equals(id)) {
                return mapEntityToDto(repository.findById(id).get());
            }
        }
        return null;
    }

    @Override
    public ClientDto create(ClientDto newItem) {
        Client newClient = new Client(
                newItem.getNomClient(),
                newItem.getPrenomClient(),
                newItem.getTelephoneClient(),
                newItem.getDateNaissanceClient()
        );
        if (equalsAny(newClient) == null) {
            repository.save(newClient);
            return newItem;
        }
        return null;
    }

    @Override
    public ClientDto update(Long id, ClientDto update) {
        if (exists(id) && (equalsAny(update) == null)) {
            Client toUpdate = repository.findById(id).get();
            toUpdate.setNomClient(update.getNomClient());
            toUpdate.setPrenomClient(update.getPrenomClient());
            toUpdate.setTelephoneClient(update.getTelephoneClient());
            toUpdate.setDateNaissanceClient(update.getDateNaissanceClient());
            repository.save(toUpdate);
            return update;
        }
        return null;
    }

    @Override
    public ClientDto delete(Long id) {
        Client client = repository.findById(id).get();
        if (exists(id)) {
            client.setActifClient(false);
            repository.save(client);
            return readOne(id);
        }
        return null;
    }

    @Override
    public List<ClientDto> readActive() {
        List<Client> actifs = new ArrayList<>();
        for (Client client : read()) {
            if (client.isActifClient()) actifs.add(client);
        }
        return mapEntitiesToDtos(actifs);
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

    private Client equalsAny(ClientDto clientDto) {
        for (Client clientCompared : read()) {
            if (clientDto.getNomClient().equals(clientCompared.getNomClient())
                && (clientDto.getPrenomClient().equals(clientCompared.getPrenomClient()))
                && (clientDto.getDateNaissanceClient().equals(clientCompared.getDateNaissanceClient())))
                return repository.findById(clientCompared.getIdClient()).get();
        }
        return null;
    }

    private ClientDto mapEntityToDto(Client client) {
        return new ClientDto(client.getNomClient(), client.getPrenomClient(), client.getTelephoneClient() == null? 0 : client.getTelephoneClient(), client.getDateNaissanceClient());
    }

    private List<ClientDto> mapEntitiesToDtos(List<Client> clients) {
        List<ClientDto> dtos = new ArrayList<>();
        for (Client client: clients) {
            dtos.add(mapEntityToDto(client));
        }
        return dtos;
    }
}
