package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.FactureDto;
import be.iramps.florencemary.devsgbd.model.Client;
import be.iramps.florencemary.devsgbd.model.Facture;
import be.iramps.florencemary.devsgbd.model.Paiement;
import be.iramps.florencemary.devsgbd.repository.ClientRepository;
import be.iramps.florencemary.devsgbd.repository.FactureRepository;
import be.iramps.florencemary.devsgbd.repository.PaiementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FactureServiceImplemented implements FactureService {
    private FactureRepository repository;
    private PaiementRepository repositoryPaiement;
    private ClientRepository repositoryClient;

    @Autowired
    public FactureServiceImplemented(FactureRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Facture> read() {
        return (List<Facture>) repository.findAll();
    }

    @Override
    public Facture readOne(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public void create(FactureDto newItem) {
        Paiement paiement = repositoryPaiement.findById(newItem.getIdPaiement()).get();
        Client client = repositoryClient.findById(newItem.getIdClient()).get();
        Facture newFacture = new Facture(client, paiement);
        if (equalsAny(newFacture) == null) repository.save(newFacture);
    }

    //nonsense?
    /*@Override
    public Facture update(Long id, FactureDto update) {
        return null;
    }*/

    @Override
    public Facture delete(Long id) {
        if (exists(id)) {
            repository.findById(id).get().setActiveFacture(false);
            return readOne(id);
        }
        return null;
    }

    @Override
    public List<Facture> readActive() {
        List<Facture> actifs = new ArrayList<>(read());
        for (Facture facture : actifs) {
            if (facture.isActiveFacture()) actifs.remove(facture);
        }
        return actifs;
    }

    private boolean exists(Long id) {
        boolean exists = false;
        for (Facture facture : read()) {
            if ((facture.isActiveFacture() == true) && (facture.getIdFacture() == id)) exists = true;
        }
        return exists;
    }

    private Facture equalsAny(Facture facture) {
        for (Facture factureCompared : read()) {
            if (facture.equals(factureCompared)) return repository.findById(factureCompared.getIdFacture()).get();
        }
        return null;
    }
}
