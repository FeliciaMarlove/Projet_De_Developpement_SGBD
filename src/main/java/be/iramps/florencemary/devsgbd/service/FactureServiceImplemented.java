package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.FactureArticleDto;
import be.iramps.florencemary.devsgbd.dto.FactureDtoGet;
import be.iramps.florencemary.devsgbd.dto.FactureDtoPost;
import be.iramps.florencemary.devsgbd.model.*;
import be.iramps.florencemary.devsgbd.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * Read 1 facture, retourne un DTO post si la facture n'est pas validée ou un DTO get si la facture est validée
     * @param id id de la facture
     * @return FactureDtoGet si la facture est validée, FactureDtoPost si la facture est ouverte, null si la facture n'est pas trouvée
     */
    @Override
    public Object readOne(Long id) {
        Facture facture;
        for (Facture fact : repository.findAll()) {
            if (fact.getIdFacture().equals(id)) {
                facture = repository.findById(id).get();
                if (facture.isValidee()) {
                    return new FactureDtoGet(facture.getIdFacture(), facture.getClient().getIdClient(), facture.getPaiement().getIdPaiement(), facture.getRefFacture(), facture.getDateHeure(), facture.isActiveFacture(), facture.isValidee(), facture.getTotal(), facture.getTotalTva(), facture.getTotalTTC());
                } else {
                    return new FactureDtoPost(facture.getClient().getIdClient(), facture.getPaiement().getIdPaiement());
                }
            }
        }
        return null;
    }

    @Override
    public List<FactureArticlesLiaison> readArticlesOnFacture(Long id) {
        Facture facture = repository.findById(id).get();
        return facture.getArticlesList();
    }

    /**
     * Supprimer une facture
     * @param id de la facture à supprimer
     * @return la facture qui a été effacée, ou null si pas de suppression
     */
    @Override
    public Object delete(Long id) {
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
    public List<Object> readActive() {
        List<Object> actifsDtos = new ArrayList<>();
        for (Facture facture : read()) {
            if (facture.isActiveFacture()) {
                if (facture.isValidee()) {
                    actifsDtos.add(new FactureDtoGet(facture.getIdFacture(), facture.getClient().getIdClient(), facture.getPaiement().getIdPaiement(), facture.getRefFacture(), facture.getDateHeure(), facture.isActiveFacture(), facture.isValidee(), facture.getTotal(), facture.getTotalTva(), facture.getTotalTTC()));
                } else {
                    actifsDtos.add(new FactureDtoPost(facture.getClient().getIdClient(), facture.getPaiement().getIdPaiement()));
                }
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
    public FactureArticlesLiaison addArticle(Long idFacture, FactureArticleDto articleDto) {
        Article article = repositoryArticle.findById(articleDto.getIdArticle()).get();
        Facture facture = repository.findById(idFacture).get();
        if (exists(idFacture) && (article.getStock() >= articleDto.getQuantite())) {
            List<FactureArticlesLiaison> articlesSurFacture = facture.getArticlesList();
            FactureArticlesLiaison factArt = isOnFacture(idFacture, article.getIdArticle());
            if (factArt != null) {
                articlesSurFacture.get(articlesSurFacture.indexOf(factArt)).setQuantite(factArt.getQuantite() + articleDto.getQuantite());
                factArt.setMontantLigne(factArt.getQuantite() * (article.getPrixUnitaire()));
                repositoryFactureArticles.save(factArt);
            } else {
                factArt = new FactureArticlesLiaison(
                        articleDto.getIdFacture(),
                        articleDto.getIdArticle(),
                        articleDto.getQuantite());
                factArt.setMontantLigne(factArt.getQuantite() * (article.getPrixUnitaire()));
                repositoryFactureArticles.save(factArt);
                articlesSurFacture.add(factArt);
                repository.save(facture);
            }
            article.setStock(article.getStock() - articleDto.getQuantite());
            repositoryArticle.save(article);
            // pour tester si la transaction s'arrête bien en cas de problème : System.exit(1);
            return factArt;
        }
        return null;
    }

    /**
     * Fonction qui supprime un article (facture_article) de la facture (quantité = 0)
     * @param idFacture
     * @param idArticle
     * @return true sur l'article a été trouvé et supprimé
     */
    @Override
    public boolean deleteArticle(Long idFacture, Long idArticle) {
        Facture facture = repository.findById(idFacture).get();
        List<FactureArticlesLiaison> articlesSurFacture = new ArrayList<>(facture.getArticlesList());
        for (FactureArticlesLiaison articleSurFacture : articlesSurFacture) {
            if (exists(idFacture) && (articleSurFacture.getIdArticle().equals(idArticle))) {
                Article article = repositoryArticle.findById(articleSurFacture.getIdArticle()).get();
                article.setStock(article.getStock() + articleSurFacture.getQuantite());
                articlesSurFacture.remove(articleSurFacture);
                articleSurFacture.setQuantite(0);
                repository.save(facture);
                return true;
            }
        }
        return false;
    }

    /**
     * Fonction qui supprime 1 unité de l'article (quantité -= 1) ou supprime l'article si la quantité = 0
     *
     * @param idFacture
     * @param idArticle
     * @return true sur l'article a été trouvé et supprimé
     */
    @Override
    public boolean articleMinusOne(Long idFacture, Long idArticle) {
        boolean success = false;
        if (exists(idFacture)) {
            Facture facture = repository.findById(idFacture).get();
            List<FactureArticlesLiaison> articlesSurFacture = new ArrayList<>(facture.getArticlesList());
            for (FactureArticlesLiaison articleSurFacture : articlesSurFacture) {
                if (articleSurFacture.getIdArticle().equals(idArticle)) {
                    success = true;
                    if (articleSurFacture.getQuantite().equals(1)) {
                        deleteArticle(idFacture, idArticle);
                        break;
                    } else {
                        Article article = repositoryArticle.findById(articleSurFacture.getIdArticle()).get();
                        article.setStock(article.getStock() + 1);
                        articleSurFacture.setQuantite(articleSurFacture.getQuantite() - 1);
                        repositoryFactureArticles.save(articleSurFacture);
                        break;
                    }
                }
            }
        }
        return success;
    }

    @Override
    public FactureDtoGet create(Long idClient, Long idPaiement) {
        Client client = repositoryClient.findById(idClient).get();
        Paiement paiement = repositoryPaiement.findById(idPaiement).get();
        if ((client != null) && (paiement != null)) {
            Facture newFacture = new Facture(client, paiement);
            System.out.println(newFacture);
            repository.save(newFacture);
            return new FactureDtoGet(newFacture.getIdFacture(), idClient, idPaiement, newFacture.getRefFacture(), newFacture.getDateHeure(), newFacture.isActiveFacture(), newFacture.isValidee(), newFacture.getTotal(), newFacture.getTotalTva(), newFacture.getTotalTTC());
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
    public FactureDtoGet validateFacture(Long idFacture) {
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
                factureFinale.setValidee(true);
                factureAFinaliser.setActiveFacture(false);
                repository.save(factureFinale);
                repository.save(factureAFinaliser);
                System.out.println("Facture finale : " + factureFinale +
                        " \n Total : " + factureFinale.getTotal() +
                        " \n Total TVA : " + factureFinale.getTotalTva() +
                        " \n Total TTC : " + factureFinale.getTotalTTC() +
                        " \n Articles : " + factureFinale.getArticlesList()
                );
                return new FactureDtoGet(factureFinale.getIdFacture(), factureFinale.getClient().getIdClient(), factureFinale.getPaiement().getIdPaiement(), factureFinale.getRefFacture(), factureFinale.getDateHeure(), factureFinale.isActiveFacture(), factureFinale.isValidee(), factureFinale.getTotal(), factureFinale.getTotalTva(), factureFinale.getTotalTTC());
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
}
