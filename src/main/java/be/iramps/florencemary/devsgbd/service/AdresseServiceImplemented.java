package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.AdresseDto;
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
    public AdresseDto readOne(Long id) {
        for (Adresse adresse: repository.findAll()) {
            if (adresse.getIdAdresse().equals(id)) {
                return mapEntityToDto(repository.findById(id).get());
            }
        }
        return null;
    }

    @Override
    public List<AdresseDto> readActive() {
        List<Adresse> actifs = new ArrayList<>();
        for (Adresse adresse : read()) {
            if (adresse.isActifAdresse()) actifs.add(adresse);
        }
        return mapEntitiesToDtos(actifs);
    }

    @Override
    public List<AdresseDto> create(Long idClient, AdresseDto newItem) {
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
    public AdresseDto update(Long id, AdresseDto update) {
        Adresse toUpdate;
        if (exists(id) && (equalsAny(update) == null)) {
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

    @Override
    public AdresseDto delete(Long id) {
        Adresse adresse = repository.findById(id).get();
        if (exists(id)) {
            adresse.setActifAdresse(false);
            repository.save(adresse);
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

    private Adresse equalsAny(AdresseDto adresseDto) {
        for (Adresse adresseCompared : read()) {
            if ((adresseDto.getIdClient() == adresseCompared.getClient().getIdClient())
                   && (adresseDto.getComplementNumero() == null || adresseDto.getComplementNumero().equals(adresseCompared.getComplementNumero()))
                    && (adresseDto.getCodePostal() == adresseCompared.getCodePostal())
                    && (adresseDto.getNumero() == adresseCompared.getNumero())
                    && (adresseDto.getPays().equals(adresseCompared.getPays()))
                    && (adresseDto.getRue().equals(adresseCompared.getRue()))
                    && (adresseDto.getVille().equals(adresseCompared.getVille()))
            )
                return repository.findById(adresseCompared.getIdAdresse()).get();
        }
        return null;
    }

    private List<AdresseDto> mapEntitiesToDtos(List<Adresse> adresses) {
        List<AdresseDto> adressesDtos = new ArrayList<>();
        for (Adresse adresse: adresses) {
            adressesDtos.add(mapEntityToDto(adresse));
        }
        return adressesDtos;
    }

    private AdresseDto mapEntityToDto(Adresse adresse) {
        return new AdresseDto(adresse.getRue(), adresse.getNumero(), adresse.getComplementNumero(), adresse.getCodePostal(), adresse.getVille(), adresse.getPays(), adresse.getClient().getIdClient());
    }


}
