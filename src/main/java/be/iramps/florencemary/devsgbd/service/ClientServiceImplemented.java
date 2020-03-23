package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.ClientDtoGet;
import be.iramps.florencemary.devsgbd.dto.ClientDtoPost;
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
    public ClientDtoGet readOne(Long id) {
        for (Client client: repository.findAll()) {
            if (client.getIdClient().equals(id)) {
                return mapEntityToDtoGet(repository.findById(id).get());
            }
        }
        return null;
    }

    @Override
    public ClientDtoPost create(ClientDtoPost newItem) {
        Client newClient = new Client(
                newItem.getNomClient(),
                newItem.getPrenomClient(),
                newItem.getTelephoneClient() != null? newItem.getTelephoneClient() : "Non renseigné",
                newItem.getDateNaissanceClient()
        );
        if (equalsAny(newClient) == null) {
            repository.save(newClient);
            return newItem;
        }
        return null;
    }

    @Override
    public ClientDtoPost update(Long id, ClientDtoPost update) {
        if (exists(id)) {
            Client toUpdate = repository.findById(id).get();
            toUpdate.setNomClient(update.getNomClient());
            toUpdate.setPrenomClient(update.getPrenomClient());
            toUpdate.setTelephoneClient(update.getTelephoneClient() != null ? update.getTelephoneClient() : "Non renseigné");
            toUpdate.setDateNaissanceClient(update.getDateNaissanceClient());
            repository.save(toUpdate);
            return update;
        }
        return null;
    }

    @Override
    public ClientDtoGet delete(Long id) {
        Client client = repository.findById(id).get();
        if (exists(id)) {
            client.setActifClient(false);
            repository.save(client);
            return readOne(id);
        }
        return null;
    }

    @Override
    public List<ClientDtoGet> readActive() {
        List<Client> actifs = new ArrayList<>();
        for (Client client : read()) {
            if (client.isActifClient()) actifs.add(client);
        }
        return mapEntitiesToDtosGet(actifs);
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

    private Client equalsAny(ClientDtoPost clientDtoPost) {
        for (Client clientCompared : read()) {
            if (clientDtoPost.getNomClient().equals(clientCompared.getNomClient())
                && (clientDtoPost.getPrenomClient().equals(clientCompared.getPrenomClient()))
                && (clientDtoPost.getDateNaissanceClient().equals(clientCompared.getDateNaissanceClient())))
                return repository.findById(clientCompared.getIdClient()).get();
        }
        return null;
    }

    private ClientDtoPost mapEntityToDto(Client client) {
        return new ClientDtoPost(client.getNomClient(), client.getPrenomClient(), client.getTelephoneClient() == null ? "Non renseigné" : client.getTelephoneClient(), client.getDateNaissanceClient());
    }

    private List<ClientDtoPost> mapEntitiesToDtos(List<Client> clients) {
        List<ClientDtoPost> dtos = new ArrayList<>();
        for (Client client: clients) {
            dtos.add(mapEntityToDto(client));
        }
        return dtos;
    }

    private ClientDtoGet mapEntityToDtoGet(Client client) {
        return new ClientDtoGet(client.getIdClient(), client.getNomClient(), client.getPrenomClient(), client.getTelephoneClient() == null ? "Non renseigné" : client.getTelephoneClient(), client.getDateNaissanceClient(), client.isActifClient());
    }

    private List<ClientDtoGet> mapEntitiesToDtosGet(List<Client> clients) {
        List<ClientDtoGet> dtos = new ArrayList<>();
        for (Client client: clients) {
            dtos.add(mapEntityToDtoGet(client));
        }
        return dtos;
    }
}
