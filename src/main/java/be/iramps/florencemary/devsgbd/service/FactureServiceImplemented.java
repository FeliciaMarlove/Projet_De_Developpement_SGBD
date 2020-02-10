package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.FactureArticleDto;
import be.iramps.florencemary.devsgbd.dto.FactureDto;
import be.iramps.florencemary.devsgbd.model.Client;
import be.iramps.florencemary.devsgbd.model.Facture;
import be.iramps.florencemary.devsgbd.model.FactureArticlesLiaison;
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
    private FactureArticlesLiaison repositoryFactureArticles;

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

    @Override
    public boolean addArticle(Long id, FactureArticleDto article) {
        boolean success = false;
        Facture facture = repository.findById(id).get();
        if (exists(id)) {
            List<FactureArticlesLiaison> articlesSurFacture = new ArrayList<>(facture.getArticlesList());
            success = articlesSurFacture.add(new FactureArticlesLiaison(
                    article.getIdFacture(),
                    article.getIdArticle(),
                    article.getQuantite()
            ));
        }
        return success;
    }

    @Override
    public boolean deleteArticle(Long id, FactureArticleDto article) {
        boolean success = false;
        Facture facture = repository.findById(id).get();
        // récup du truc à supprimer !
        for (FactureArticlesLiaison articleSurFacture : facture.getArticlesList()) {
            if (articleSurFacture.getIdArticle() == article.getIdArticle() && exists(id)) {
                List<FactureArticlesLiaison> articlesSurFacture = new ArrayList<>(facture.getArticlesList());
                //success = articlesSurFacture.remove()); -> récup tous articles (! si ajout plusieurs fois le mm -> same line)
            }
        }
        return success;
    }

    // TO DO -> -1 sur quantité du art-fact; si qté = 0 delete
    @Override
    public boolean deleteOneArticle(Long id, FactureArticleDto article) {
        boolean success = false;

        return success;
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
