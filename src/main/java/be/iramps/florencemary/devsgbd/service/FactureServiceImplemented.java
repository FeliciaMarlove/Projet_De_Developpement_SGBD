package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.FactureArticleDto;
import be.iramps.florencemary.devsgbd.dto.FactureDto;
import be.iramps.florencemary.devsgbd.model.*;
import be.iramps.florencemary.devsgbd.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FactureServiceImplemented implements FactureService {
    private FactureRepository repository;
    private PaiementRepository repositoryPaiement;
    private ClientRepository repositoryClient;
    private ArticleRepository articleRepository;
    private FactureArticlesRepository repositoryFactureArticles;

    @Autowired
    public FactureServiceImplemented(FactureRepository repository, PaiementRepository repositoryPaiement, ClientRepository repositoryClient, ArticleRepository articleRepository, FactureArticlesRepository repositoryFactureArticles) {
        this.repository = repository;
        this.repositoryPaiement = repositoryPaiement;
        this.repositoryClient = repositoryClient;
        this.articleRepository = articleRepository;
        this.repositoryFactureArticles = repositoryFactureArticles;
    }

    /**
     * Read
     * @return liste des factures
     */
    @Override
    public List<Facture> read() {
        return (List<Facture>) repository.findAll();
    }

    /**
     * Read 1 facture
     * @param id id de la facture
     * @return une facture
     */
    @Override
    public Facture readOne(Long id) {
        for (Facture facture: repository.findAll()) {
            if (facture.getIdFacture().equals(id)) {
                return repository.findById(id).get();
            }
        }
        return null;
    }

    /**
     * Supprimer une facture
     * @param id de la facture à supprimer
     * @return la facture qui a été effacée, ou null si pas de suppression
     */
    @Override
    public Facture delete(Long id) {
        Facture facture = repository.findById(id).get();
        if (exists(id)) {
            facture.setActiveFacture(false);
            repository.save(facture);
            return readOne(id);
        }
        return null;
    }

    /**
     * Read des factures "actives" (non supprimées logiquement)
     * @return la liste des factures actives
     */
    @Override
    public List<Facture> readActive() {
        List<Facture> actifs = new ArrayList<>(read());
        for (Facture facture : actifs) {
            if (facture.isActiveFacture()) actifs.remove(facture);
        }
        return actifs;
    }

    /**
     * Vérifie qu'un FactureArticle se trouve sur la facture
     * @param idFacture
     * @param idArticle
     * @return le FactureArticle trouvé, ou null si pas trouvé
     */
    private FactureArticlesLiaison isOnFacture(Long idFacture, Long idArticle) {
        Facture facture = repository.findById(idFacture).get();
        for (FactureArticlesLiaison fal : facture.getArticlesList()) {
            if (fal.getIdArticle() == idArticle) {
                return fal;
            }
        }
        return null;
    }

    /**
     * Ajoute un article sur la facture
     * @param idFacture
     * @param article
     * @return
     */
    @Override
    public boolean addArticle(Long idFacture, FactureArticleDto article) {
        boolean success = false;
        Facture facture = repository.findById(idFacture).get();
        if (exists(idFacture)) {
            List<FactureArticlesLiaison> articlesSurFacture = new ArrayList<>(facture.getArticlesList());
            FactureArticlesLiaison factArt = isOnFacture(idFacture, article.getIdArticle());
            if (factArt != null) {
                articlesSurFacture.get(articlesSurFacture.indexOf(factArt)).setQuantite(factArt.getQuantite() + 1);
                factArt.setMontantLigne(factArt.getQuantite() * (articleRepository.findById(factArt.getIdArticle()).get().getPrixUnitaire()));
                success = true;
            } else {
                success = articlesSurFacture.add(new FactureArticlesLiaison(
                        article.getIdFacture(),
                        article.getIdArticle(),
                        article.getQuantite()
                ));
                factArt = articlesSurFacture.get(articlesSurFacture.size() - 1);
                factArt.setMontantLigne(factArt.getQuantite() * (articleRepository.findById(factArt.getIdArticle()).get().getPrixUnitaire()));
            }
        }
        return success;
    }

    /**
     * Fonction qui supprime un article (facture_article) de la facture (quantité = 0)
     * @param idFacture
     * @param idArticle
     * @return true sur l'article a été trouvé et supprimé
     */
    @Override
    public boolean deleteArticle(Long idFacture, Long idArticle) {
        boolean success = false;
        Facture facture = repository.findById(idFacture).get();
        List<FactureArticlesLiaison> articlesSurFacture = new ArrayList<>(facture.getArticlesList());
        for (FactureArticlesLiaison articleSurFacture : articlesSurFacture) {
            if (exists(idFacture) && (articleSurFacture.getIdArticle() == idArticle)) {
                success = articlesSurFacture.remove(articleSurFacture); break;
            }
        }
        return success;
    }

    /**
     * Fonction qui supprime 1 unité de l'article (quantité -= 1) ou supprime l'article sur la quantité = 0
     * @param idFacture
     * @param idArticle
     * @return true sur l'article a été trouvé et supprimé
     */
    @Override
    public boolean deleteOneArticle(Long idFacture, Long idArticle) {
        boolean success = false;
        Facture facture = repository.findById(idFacture).get();
        List<FactureArticlesLiaison> articlesSurFacture = new ArrayList<>(facture.getArticlesList());
        for (FactureArticlesLiaison articleSurFacture : articlesSurFacture) {
            if (exists(idFacture) && (articleSurFacture.getIdArticle() == idArticle)) {
                success = true;
                if (articleSurFacture.getIdArticle() == 1L) {
                    deleteArticle(idFacture, idArticle); break;
                } else {
                    articleSurFacture.setQuantite(articleSurFacture.getQuantite() - 1); break;
                }
            }
        }
        return success;
    }

    /**
     * Crée une facture avec un client et un moyen de paiement
     * @param newItem DTO facture
     */
    @Override
    public void create(FactureDto newItem) {
        Paiement paiement = repositoryPaiement.findById(newItem.getIdPaiement()).get();
        Client client = repositoryClient.findById(newItem.getIdClient()).get();
        Facture newFacture = new Facture(client, paiement);
        if (equalsAny(newFacture) == null) repository.save(newFacture);
    }

    /**
     * Finaliser la facture (créer une facture immutable)
     * @param idFacture
     */
    @Override
    public void validateFacture(Long idFacture) {
        Facture factureAFinaliser = repository.findById(idFacture).get();
        final Facture factureFinale = new Facture(factureAFinaliser.getClient(), factureAFinaliser.getPaiement());
        final List<FactureArticlesLiaison> listeFinale = new ArrayList<>();
        for (FactureArticlesLiaison fal : factureAFinaliser.getArticlesList()) {
            final FactureArticlesLiaison falUnite = new FactureArticlesLiaison(fal.getIdFacture(), fal.getIdArticle(), fal.getQuantite());
            listeFinale.add(falUnite);
        }
        final Double total = calculerMontant(idFacture);
        factureFinale.setTotal(total);
    }

    private Double calculerMontant(Long idFacture) {
        Double montant = 0.0;
        Facture facture = repository.findById(idFacture).get();
        for (FactureArticlesLiaison fal: facture.getArticlesList()) {
            montant += (fal.getQuantite() * articleRepository.findById(fal.getIdArticle()).get().getPrixUnitaire());
        }
        return montant;
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
