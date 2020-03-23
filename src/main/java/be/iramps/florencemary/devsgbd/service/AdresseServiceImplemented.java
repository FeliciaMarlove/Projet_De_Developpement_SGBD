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

@Service
public class AdresseServiceImplemented implements AdresseService {
    private AdresseRepository repository;
    private ClientRepository repositoryClient;

    @Autowired
    public AdresseServiceImplemented(AdresseRepository repository, ClientRepository repositoryClient) {
        this.repository = repository;
        this.repositoryClient = repositoryClient;
    }

    @Override
    public List<Adresse> read() {
        return (List<Adresse>) repository.findAll();
    }

    @Override
    public AdresseDtoGet readOne(Long id) {
        for (Adresse adresse: repository.findAll()) {
            if (adresse.getIdAdresse().equals(id)) {
                return mapEntityToDtoGet(repository.findById(id).get());
            }
        }
        return null;
    }

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

    @Override
    public List<AdresseDtoGet> readActive() {
        List<Adresse> actifs = new ArrayList<>();
        for (Adresse adresse : read()) {
            if (adresse.isActifAdresse()) actifs.add(adresse);
        }
        return mapEntitiesToDtosGet(actifs);
    }

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
            return mapEntitiesToDtos(findClient.getAdressesList()) ;
        }
        return null;
    }

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
            return mapEntityToDto(toUpdate);
        }
        return null;
    }

    private Client findClient(Adresse adresse) {
        for (Client client: repositoryClient.findAll()) {
           if (client.getAdressesList().contains(adresse)) {
               return client;
           }
        }
        return null;
    }

    @Override
    public AdresseDtoGet delete(Long id) {
        Adresse adresse = repository.findById(id).get();
        Client clt = findClient(adresse);
        System.out.println(clt);
        if (exists(id)) {
            clt.getAdressesList().remove(adresse);
            System.out.println(clt);
            repositoryClient.save(clt);
            repository.delete(adresse);
            return readOne(id);
        }
        return null;
    }

    private boolean exists(Long id) {
        boolean exists = false;
        for (Adresse adresse : read()) {
            if ((adresse.isActifAdresse()) && (adresse.getIdAdresse().equals(id))) exists = true;
        }
        return exists;
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

    private List<AdresseDtoPost> mapEntitiesToDtos(List<Adresse> adresses) {
        List<AdresseDtoPost> adressesDtos = new ArrayList<>();
        for (Adresse adresse: adresses) {
            adressesDtos.add(mapEntityToDto(adresse));
        }
        return adressesDtos;
    }

    private AdresseDtoPost mapEntityToDto(Adresse adresse) {
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
