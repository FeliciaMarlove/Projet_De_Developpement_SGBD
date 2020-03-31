package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.ClientDtoGet;
import be.iramps.florencemary.devsgbd.dto.ClientDtoPost;
import be.iramps.florencemary.devsgbd.model.Client;
import be.iramps.florencemary.devsgbd.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service contenant la couche business sur l'entite Client
 */
@Service
public class ClientServiceImplemented implements ClientService {
    private ClientRepository repository;

    @Autowired
    public ClientServiceImplemented(ClientRepository repository) {
        this.repository = repository;
    }

    /**
     * Retourne la liste des clients en DB
     * @return List Client la liste de tous les clients en DB
     */
    @Override
    public List<Client> read() {
        return (List<Client>) repository.findAll();
    }

    /**
     * Retourne un client
     * @param id (Long) : id du client a retourner
     * @return ClientDtoGet client trouve || null si pas de correspondance
     */
    @Override
    public ClientDtoGet readOne(Long id) {
        for (Client client: repository.findAll()) {
            if (client.getIdClient().equals(id)) {
                return mapEntityToDtoGet(repository.findById(id).get());
            }
        }
        return null;
    }

    /**
     * Retourne la liste des clients actifs
     * @return List ClientDtoGet clients actifs
     */
    @Override
    public List<ClientDtoGet> readActive() {
        List<Client> actifs = new ArrayList<>();
        for (Client client : read()) {
            if (client.isActifClient()) actifs.add(client);
        }
        return mapEntitiesToDtosGet(actifs);
    }

    /**
     * Cree un nouvel enregistrement de client en DB
     * @param newItem (ClientDtoPost) : DTO POST du client a creer
     * @return ClientDtoPost client cree || null si le client est deja en DB
     */
    @Override
    public ClientDtoPost create(ClientDtoPost newItem) {
        if (equalsAny(newItem) == null) {
        Client newClient = new Client(
                newItem.getNomClient(),
                newItem.getPrenomClient(),
                newItem.getTelephoneClient(),
                newItem.getDateNaissanceClient()
        );
            repository.save(newClient);
            return newItem;
        }
        return null;
    }

    /**
     * Met a jour un client en DB
     * @param id (Long) : id du client a mettre a jour
     * @param update (ClientDtoPost) : client modifie
     * @return ClientDtoPost client modifie || null si le client n'existait pas en DB
     */
    @Override
    public ClientDtoPost update(Long id, ClientDtoPost update) {
        if (exists(id)) {
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

    /**
     * Supprime logiquement un client en DB
     * @param id (Long) : id du client a supprimer
     * @return ClientDtoGet client supprime || null si le client n'a pas ete trouvé en DB
     */
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

    //__________________PRIVATE METHODS_________________________________________________________________________________

    private boolean exists(Long id) {
        for (Client client : read()) {
            if ((client.isActifClient()) && (client.getIdClient().equals(id))) {
                return true;
            }
        }
        return false;
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
        return new ClientDtoPost(client.getNomClient(), client.getPrenomClient(), client.getTelephoneClient(), client.getDateNaissanceClient());
    }

    private List<ClientDtoPost> mapEntitiesToDtos(List<Client> clients) {
        List<ClientDtoPost> dtos = new ArrayList<>();
        for (Client client: clients) {
            dtos.add(mapEntityToDto(client));
        }
        return dtos;
    }

    private ClientDtoGet mapEntityToDtoGet(Client client) {
        return new ClientDtoGet(client.getIdClient(), client.getNomClient(), client.getPrenomClient(), client.getTelephoneClient(), client.getDateNaissanceClient(), client.isActifClient());
    }

    private List<ClientDtoGet> mapEntitiesToDtosGet(List<Client> clients) {
        List<ClientDtoGet> dtos = new ArrayList<>();
        for (Client client: clients) {
            dtos.add(mapEntityToDtoGet(client));
        }
        return dtos;
    }
}
