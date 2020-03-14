package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.FactureArticleDto;
import be.iramps.florencemary.devsgbd.dto.FactureDto;
import be.iramps.florencemary.devsgbd.model.*;
import be.iramps.florencemary.devsgbd.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Service
public class FactureServiceImplemented implements FactureService {
    private FactureRepository repository;
    private PaiementRepository repositoryPaiement;
    private ClientRepository repositoryClient;
    private ArticleRepository repositoryArticle;
    private FactureArticlesRepository repositoryFactureArticles;

    @Autowired
    public FactureServiceImplemented(FactureRepository repository, PaiementRepository repositoryPaiement, ClientRepository repositoryClient, ArticleRepository repositoryArticle, FactureArticlesRepository repositoryFactureArticles) {
        this.repository = repository;
        this.repositoryPaiement = repositoryPaiement;
        this.repositoryClient = repositoryClient;
        this.repositoryArticle = repositoryArticle;
        this.repositoryFactureArticles = repositoryFactureArticles;
    }

    /**
     * Read
     *
     * @return liste des factures
     */
    @Override
    public List<Facture> read() {
        return (List<Facture>) repository.findAll();
    }

    /**
     * Read 1 facture
     *
     * @param id id de la facture
     * @return une facture
     */
    @Override
    public FactureDto readOne(Long id) {
        Facture facture;
        for (Facture fact : repository.findAll()) {
            if (fact.getIdFacture().equals(id)) {
                facture = repository.findById(id).get();
                return new FactureDto(facture.getClient().getIdClient(), facture.getPaiement().getIdPaiement());
            }
        }
        return null;
    }

    /**
     * Supprimer une facture
     *
     * @param id de la facture à supprimer
     * @return la facture qui a été effacée, ou null si pas de suppression
     */
    @Override
    public FactureDto delete(Long id) {
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
     *
     * @return la liste des factures actives
     */
    @Override
    public List<FactureDto> readActive() {
        List<Facture> actifs = new ArrayList<>();
        List<FactureDto> actifsDtos = new ArrayList<>();
        for (Facture facture : read()) {
            if (facture.isActiveFacture()) {
                actifs.add(facture);
                actifsDtos.add(new FactureDto(facture.getClient().getIdClient(), facture.getPaiement().getIdPaiement()));
            }
        }
        if (!actifsDtos.isEmpty()) {
            return actifsDtos;
        }
        return null;
    }

    /**
     * Vérifie qu'un FactureArticle se trouve sur la facture
     *
     * @param idFacture
     * @param idArticle
     * @return le FactureArticle trouvé, ou null si pas trouvé
     */
    private FactureArticlesLiaison isOnFacture(Long idFacture, Long idArticle) {
        Facture facture = repository.findById(idFacture).get();
        System.out.println(facture);
        for (FactureArticlesLiaison fal : facture.getArticlesList()) {
            if (fal.getIdArticle().equals(idArticle)) {
                return fal;
            }
        }
        return null;
    }

    /**
     * Ajoute un article sur la facture
     *
     * @param idFacture
     * @param articleDto
     * @return
     */
    @Override
    @Transactional
    public boolean addArticle(Long idFacture, FactureArticleDto articleDto) {
        boolean success = false;
        Article article = repositoryArticle.findById(articleDto.getIdArticle()).get();
        Facture facture = repository.findById(idFacture).get();
        if (exists(idFacture)) {
            List<FactureArticlesLiaison> articlesSurFacture = facture.getArticlesList();
            FactureArticlesLiaison factArt = isOnFacture(idFacture, article.getIdArticle());
            if (factArt != null) {
                articlesSurFacture.get(articlesSurFacture.indexOf(factArt)).setQuantite(factArt.getQuantite() + articleDto.getQuantite());
                factArt.setMontantLigne(factArt.getQuantite() * (article.getPrixUnitaire()));
                repositoryFactureArticles.save(factArt);
                success = true;
            } else {
                factArt = new FactureArticlesLiaison(
                        articleDto.getIdFacture(),
                        articleDto.getIdArticle(),
                        articleDto.getQuantite());
                factArt.setMontantLigne(factArt.getQuantite() * (article.getPrixUnitaire()));
                repositoryFactureArticles.save(factArt);
                success = articlesSurFacture.add(factArt);
                repository.save(facture);
            }
            article.setStock(article.getStock() - articleDto.getQuantite());
            repositoryArticle.save(article);
            // tester si la transaction s'arrête bien en cas de problème : System.exit(1);
        }
        return success;
    }

    /**
     * Fonction qui supprime un article (facture_article) de la facture (quantité = 0)
     *
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
                success = articlesSurFacture.remove(articleSurFacture);
                break;
            }
        }
        return success;
    }

    /**
     * Fonction qui supprime 1 unité de l'article (quantité -= 1) ou supprime l'article sur la quantité = 0
     *
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
                    deleteArticle(idFacture, idArticle);
                    break;
                } else {
                    articleSurFacture.setQuantite(articleSurFacture.getQuantite() - 1);
                    break;
                }
            }
        }
        return success;
    }

    @Override
    public FactureDto create(Long idClient, Long idPaiement) {
        Client client = repositoryClient.findById(idClient).get();
        Paiement paiement = repositoryPaiement.findById(idPaiement).get();
        Facture newFacture = new Facture(client, paiement);
        if (equalsAny(newFacture) == null) {
            System.out.println(newFacture);
            repository.save(newFacture);
            return new FactureDto(idClient, idPaiement);
        }
        return null;
    }

    /**
     * Finaliser la facture (créer une facture immutable)
     *
     * @param idFacture
     * @return
     */
    @Override
    @Transactional
    public FactureDto validateFacture(Long idFacture) {
        if (exists(idFacture)) {
            Facture factureAFinaliser = repository.findById(idFacture).get();
            if (!factureAFinaliser.getArticlesList().isEmpty()) {
                final Facture factureFinale = new Facture(factureAFinaliser.getClient(), factureAFinaliser.getPaiement());
                final List<FactureArticlesLiaison> listeFinale = new ArrayList<>();
                for (FactureArticlesLiaison fal : factureAFinaliser.getArticlesList()) {
                    final FactureArticlesLiaison falUnite = new FactureArticlesLiaison(fal.getIdFacture(), fal.getIdArticle(), fal.getQuantite());
                    repositoryFactureArticles.save(falUnite);
                    listeFinale.add(falUnite);
                }
                final Double total = calculerMontant(idFacture);
                final Double totalTva = calculerMontantTVA(idFacture);
                final Double totalTTC = calculerMontantTTC(idFacture);
                factureFinale.setListeArticlesFactures(listeFinale);
                factureFinale.setTotal(total);
                factureFinale.setTotalTva(totalTva);
                factureFinale.setTotalTTC(totalTTC);
                factureAFinaliser.setValidee(true);
                factureAFinaliser.setActiveFacture(false);
                repository.save(factureFinale);
                repository.save(factureAFinaliser);
                System.out.println("Facture finale : " + factureFinale +
                        " \n Total : " + factureFinale.getTotal() +
                        " \n Total TVA : " + factureFinale.getTotalTva() +
                        " \n Total TTC : " + factureFinale.getTotalTTC() +
                        " \n Articles : " + factureFinale.getArticlesList()
                );
                return new FactureDto(factureFinale.getClient().getIdClient(), factureFinale.getPaiement().getIdPaiement());
            }
        }
        return null;
    }

    private Double calculerMontant(Long idFacture) {
        Double montant = 0.0;
        Facture facture = repository.findById(idFacture).get();
        for (FactureArticlesLiaison fal : facture.getArticlesList()) {
            montant += (fal.getQuantite() * repositoryArticle.findById(fal.getIdArticle()).get().getPrixUnitaire());
        }
        return montant;
    }

    private Double calculerMontantTVA(Long idFacture) {
        Double montant = 0.0;
        Facture facture = repository.findById(idFacture).get();
        for (FactureArticlesLiaison fal : facture.getArticlesList()) {
            montant += (fal.getQuantite() * ((repositoryArticle.findById(fal.getIdArticle()).get().getPrixUnitaire() / 100) * repositoryArticle.findById(fal.getIdArticle()).get().getTva().getTauxTva()));
        }
        return montant;
    }

    private Double calculerMontantTTC(Long idFacture) {
        return calculerMontant(idFacture) + calculerMontantTVA(idFacture);
    }

    private boolean exists(Long id) {
        boolean exists = false;
        for (Facture facture : read()) {
            if ((facture.isActiveFacture() == true) && (facture.getIdFacture() == id)) exists = true;
        }
        return exists;
    }

    private Facture equalsAny(Facture facture) {
        if (!read().isEmpty()) {
            for (Facture factureCompared : read()) {
                if (facture.equals(factureCompared)) return repository.findById(factureCompared.getIdFacture()).get();
            }
            return null;
        }
        return null;
    }
}
