package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.AdresseDtoGet;
import be.iramps.florencemary.devsgbd.dto.AdresseDtoPost;
import be.iramps.florencemary.devsgbd.model.Adresse;
import be.iramps.florencemary.devsgbd.model.Client;
import be.iramps.florencemary.devsgbd.repository.AdresseRepository;
import be.iramps.florencemary.devsgbd.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service contenant la couche business sur l'entite Adresse
 */
@Service
public class AdresseServiceImplemented implements AdresseService {
    private AdresseRepository repository;
    private ClientRepository repositoryClient;

    @Autowired
    public AdresseServiceImplemented(AdresseRepository repository, ClientRepository repositoryClient) {
        this.repository = repository;
        this.repositoryClient = repositoryClient;
    }

    /**
     * Retourne la liste des adresses en DB
     * @return List Adresse toutes les adresses en DB
     */
    @Override
    public List<Adresse> read() {
        return (List<Adresse>) repository.findAll();
    }

    /**
     * Retourne une adresse
     * @param id (Long) : id de l'adresse a retourner
     * @return AdresseDtoGet DTO GET de l'adresse trouvee || null si l'adresse n'est pas trouvee
     */
    @Override
    public AdresseDtoGet readOne(Long id) {
        for (Adresse adresse: repository.findAll()) {
            if (adresse.getIdAdresse().equals(id)) {
                return mapEntityToDtoGet(repository.findById(id).get());
            }
        }
        return null;
    }

    /**
     * Retourne la liste des adresses d'un client
     * @param idClient (Long) : id du client
     * @return List AdresseDtoGet adresses du client
     */
    @Override
    public List<AdresseDtoGet> readFromClient(Long idClient) {
        List<AdresseDtoGet> clientsAdresses = new ArrayList<>();
        for (Adresse adresse: repository.findAll()) {
            if (adresse.getClient().getIdClient().equals(idClient)) {
                clientsAdresses.add(mapEntityToDtoGet(adresse));
            }
        }
        return clientsAdresses;
    }

    /**
     * Retourne les adresses actives
     * @return List AdresseDtoGet adresses actives
     */
    @Override
    public List<AdresseDtoGet> readActive() {
        List<Adresse> actifs = new ArrayList<>();
        for (Adresse adresse : read()) {
            if (adresse.isActifAdresse()) actifs.add(adresse);
        }
        return mapEntitiesToDtosGet(actifs);
    }

    /**
     * Enregistre une nouvelle adresse en DB rattachee a un client existant
     * @param idClient (Long) : id du client auquel est rattachee l'adresse
     * @param newItem (AdresseDtoPost) : DTO POST de l'adresse a creer
     * @return List AdresseDtoPost adresses du client || null si l'adresse existe deja en DB
     */
    @Override
    public List<AdresseDtoPost> create(Long idClient, AdresseDtoPost newItem) {
        if (equalsAny(newItem) == null) {
            Client findClient = repositoryClient.findById(idClient).get();
            Adresse newAdresse = new Adresse(
                    newItem.getRue(),
                    newItem.getNumero(),
                    newItem.getComplementNumero(),
                    newItem.getCodePostal(),
                    newItem.getVille(),
                    newItem.getPays(),
                    findClient
            );
            repository.save(newAdresse);
            repositoryClient.save(findClient);
            return mapEntitiesToDtosPost(findClient.getAdressesList()) ;
        }
        return null;
    }

    /**
     * Met a jour une adresse en DB
     * @param id (Long) : id de l'adresse a modifier
     * @param update (AdresseDtoPost) : DTO POST de l'adresse modifiee
     * @return AdresseDtoPost adresse modifiee || null si l'adresse n'a pas ete trouvee
     */
    @Override
    public AdresseDtoPost update(Long id, AdresseDtoPost update) {
        Adresse toUpdate;
        if (exists(id)) {
            toUpdate = repository.findById(id).get();
            toUpdate.setRue(update.getRue());
            toUpdate.setNumero(update.getNumero());
            toUpdate.setComplementNumero(update.getComplementNumero());
            toUpdate.setCodePostal(update.getCodePostal());
            toUpdate.setVille(update.getVille());
            toUpdate.setPays(update.getPays());
            toUpdate.setClient(repositoryClient.findById(update.getIdClient()).get());
            repository.save(toUpdate);
            return mapEntityToDtoPost(toUpdate);
        }
        return null;
    }

    /**
     * Supprime logiquement une adresse en DB et de la liste d'adresses du client auquel elle est rattachee
     * @param id (Long) : id de l'adresse
     * @return AdresseDtoGet adresse supprimee || null si l'adresse n'est pas trouvee
     */
    @Override
    public AdresseDtoGet delete(Long id) {
        Adresse adresse = repository.findById(id).get();
        Client clt = findClient(adresse);
        if (exists(id)) {
            clt.getAdressesList().remove(adresse);
            repositoryClient.save(clt);
            repository.delete(adresse);
            return readOne(id);
        }
        return null;
    }

    //__________________PRIVATE METHODS_________________________________________________________________________________

    private Client findClient(Adresse adresse) {
        for (Client client: repositoryClient.findAll()) {
            if (client.getAdressesList().contains(adresse)) {
                return client;
            }
        }
        return null;
    }

    private boolean exists(Long id) {
        for (Adresse adresse : read()) {
            if ((adresse.isActifAdresse()) && (adresse.getIdAdresse().equals(id))) return true;
        }
        return false;
    }

    private Adresse equalsAny(Adresse adresse) {
        for (Adresse adresseCompared : read()) {
            if (adresse.equals(adresseCompared)) return repository.findById(adresseCompared.getIdAdresse()).get();
        }
        return null;
    }

    private Adresse equalsAny(AdresseDtoPost adresseDtoPost) {
        for (Adresse adresseCompared : read()) {
            if ((adresseDtoPost.getComplementNumero() == null || adresseDtoPost.getComplementNumero().equals(adresseCompared.getComplementNumero()))
                    && (adresseDtoPost.getCodePostal() == adresseCompared.getCodePostal())
                    && (adresseDtoPost.getNumero() == adresseCompared.getNumero())
                    && (adresseDtoPost.getPays().equals(adresseCompared.getPays()))
                    && (adresseDtoPost.getRue().equals(adresseCompared.getRue()))
                    && (adresseDtoPost.getVille().equals(adresseCompared.getVille()))
            )
                return repository.findById(adresseCompared.getIdAdresse()).get();
        }
        return null;
    }

    private List<AdresseDtoPost> mapEntitiesToDtosPost(List<Adresse> adresses) {
        List<AdresseDtoPost> adressesDtos = new ArrayList<>();
        for (Adresse adresse: adresses) {
            adressesDtos.add(mapEntityToDtoPost(adresse));
        }
        return adressesDtos;
    }

    private AdresseDtoPost mapEntityToDtoPost(Adresse adresse) {
        return new AdresseDtoPost(adresse.getRue(), adresse.getNumero(), adresse.getComplementNumero(), adresse.getCodePostal(), adresse.getVille(), adresse.getPays(), adresse.getClient().getIdClient());
    }

    private List<AdresseDtoGet> mapEntitiesToDtosGet(List<Adresse> actifs) {
        List<AdresseDtoGet> dtos = new ArrayList<>();
        for (Adresse ad: read()) {
            dtos.add(mapEntityToDtoGet(ad));
        }
        return dtos;
    }

    private AdresseDtoGet mapEntityToDtoGet(Adresse adresse) {
        return new AdresseDtoGet(adresse.getIdAdresse(), adresse.getRue(), adresse.getNumero(), adresse.getComplementNumero(), adresse.getCodePostal(), adresse.getVille(), adresse.getPays(), adresse.getClient().getIdClient());
    }

}
