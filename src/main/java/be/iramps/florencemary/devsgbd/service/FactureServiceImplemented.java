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

/**
 * Service contenant la couche business sur l'entite Facture
 */
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
     * Retourne les factures en DB
     * @return List Facture toutes les factures en DB
     */
    @Override
    public List<Facture> read() {
        return (List<Facture>) repository.findAll();
    }

    /**
     * Retourne une facture
     * @param id (Long) : id de la facture a retourner
     * @return Object (FactureDtoPost) si la facture n'est pas encore validee, (FactureDtoGet) si la facture est validee || null si pas de correspondance
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

    /**
     * Retourne les factures actives
     * @return List Object (FactureDtoPost/FactureDtoGet) factures actives
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
        return actifsDtos;
    }

    /**
     * Retourne la liste des articles sur une facture
     * @param id (Long) : id de la facture
     * @return List FactureArticleDto liste des articles sous forme de FactureArticle || null si la facture n'est pas trouvee
     */
    @Override
    public List<FactureArticleDto> readArticlesOnFacture(Long id) {
        if (exists(id)) {
            Facture facture = repository.findById(id).get();
            return mapFALEntitiesToDtos(facture.getArticlesList());
        }
       return null;
    }

    /**
     * Supprime logiquement une facture
     * @param id (Long) : id de la facture a supprimer
     * @return Object (FactureDtoPost) si la facture n'est pas encore validee, (FactureDtoGet) si la facture est validee || null si pas de correspondance
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
     * Transaction. Ajoute un article sur la facture avec une quantite determinee. Si l'article est deja present sur la facture, la ligne (FactureArticle) est mise a jour, sinon une nouvelle ligne est cree. Gere la mise a jour du montant par ligne et le stock de l'article.
     * @param idFacture (Long) : id de la facture sur laquelle ajouter l'article
     * @param articleDto (FactureArticleDto) : FactureArticle a ajouter sur la facture
     * @return FactureArticleDto l'article ajoute || null si la facture n'est pas trouvee ou que le stock de l'article est insuffisant
     */
    @Override
    @Transactional
    public FactureArticleDto addArticle(Long idFacture, FactureArticleDto articleDto) {
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
                        facture,
                        article,
                        articleDto.getQuantite(),
                        articleDto.getMontantLigne());
                factArt.setMontantLigne(articleDto.getQuantite() * (article.getPrixUnitaire()));
                repositoryFactureArticles.save(factArt);
                articlesSurFacture.add(factArt);
                repository.save(facture);
            }
            article.setStock(article.getStock() - articleDto.getQuantite());
            repositoryArticle.save(article);
            // pour tester si la transaction s'arrête bien en cas de problème : System.exit(1);
            return mapFALEntitiesToDtos(factArt);
        }
        return null;
    }

    /**
     * Transaction. Supprime un article sur une facture dans sa totalite (quantite = 0). La quantite et le montant du FactureArticle sont mis a zero. Le stock de l'objet est incremente de la quantite.
     * @param idFacture (Long) : id de la facture sur laquelle l'article doit etre supprime
     * @param idArticle (Long) : id de l'article a supprimer
     * @return boolean true si l'operation a reussi, false si l'id de la facture n'existe pas ou que l'article ne se trouve pas sur la facture
     */
    @Override
    @Transactional
    public boolean deleteArticle(Long idFacture, Long idArticle) {
        Facture facture = repository.findById(idFacture).get();
        List<FactureArticlesLiaison> articlesSurFacture = new ArrayList<>(facture.getArticlesList());
        for (FactureArticlesLiaison articleSurFacture : articlesSurFacture) {
            if (exists(idFacture) && (articleSurFacture.getArticle().getIdArticle().equals(idArticle))) {
                Article article = repositoryArticle.findById(articleSurFacture.getArticle().getIdArticle()).get();
                article.setStock(article.getStock() + articleSurFacture.getQuantite());
                articlesSurFacture.remove(articleSurFacture);
                articleSurFacture.setQuantite(0);
                articleSurFacture.setMontantLigne(0.0);
                repository.save(facture);
                return true;
            }
        }
        return false;
    }

    /**
     * Transaction. Supprime une unite d'un article sur une facture. Le montant de la ligne et le stock de l'article sont geres. Si la quantite de la ligne vaut zero, l'article est supprime.
     * @param idFacture (Long) : id de la facture sur laquelle se trouve l'article
     * @param idArticle (Long) : id de l'article dont il faut supprimer une unite
     * @return boolean true si l'operation a reussi, false si l'article ne se trouve pas sur la facture ou que la facture n'est pas trouvee
     */
    @Override
    @Transactional
    public boolean articleMinusOne(Long idFacture, Long idArticle) {
        boolean success = false;
        if (exists(idFacture)) {
            Facture facture = repository.findById(idFacture).get();
            List<FactureArticlesLiaison> articlesSurFacture = new ArrayList<>(facture.getArticlesList());
            for (FactureArticlesLiaison articleSurFacture : articlesSurFacture) {
                if (articleSurFacture.getArticle().getIdArticle().equals(idArticle)) {
                    success = true;
                    if (articleSurFacture.getQuantite().equals(1)) {
                        deleteArticle(idFacture, idArticle);
                        break;
                    } else {
                        Article article = repositoryArticle.findById(articleSurFacture.getArticle().getIdArticle()).get();
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

    /**
     * Cree une facture vierge.
     * @param idClient (Long) : id du client pour lequel la facture est creee
     * @param idPaiement (Long) : id du mode de paiement affecte a la facture creee
     * @return FactureDtoGet facture creee || null si le client ou le paiement n'ont pas ete trouves
     */
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
     * Transaction. Valide une facture qui contient un client, un paiement et une liste d'au moins un article. Cree une nouvelle facture finale dont tous les membres sont finaux. Les montants totaux sont calcules et persistes en DB. Ignore les lignes d'articles ou quantite == 0. La facture initiale est rendue inactive.
     * @param idFacture (Long) : id de la facture a valider
     * @return FactureDtoGet facture validee || null si la liste d'articles est vide ou si la facture n'est pas trouvee
     */
    @Override
    @Transactional
    public FactureDtoGet validateFacture(Long idFacture) {
        if (exists(idFacture)) {
            Facture factureAFinaliser = repository.findById(idFacture).get();
            System.out.println(factureAFinaliser);
            System.out.println(factureAFinaliser.getArticlesList());
            if (!factureAFinaliser.getArticlesList().isEmpty()) {
                final Facture factureFinale = new Facture(factureAFinaliser.getClient(), factureAFinaliser.getPaiement());
                final List<FactureArticlesLiaison> listeFinale = new ArrayList<>();
                for (FactureArticlesLiaison fal : factureAFinaliser.getArticlesList()) {
                    if (fal.getQuantite() > 0) {
                        final FactureArticlesLiaison falUnite = new FactureArticlesLiaison(fal.getFacture(), fal.getArticle(), fal.getQuantite(), fal.getMontantLigne());
                        repositoryFactureArticles.save(falUnite);
                        listeFinale.add(falUnite);
                    }
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

    //__________________PRIVATE METHODS_________________________________________________________________________________

    private Double calculerMontant(Long idFacture) {
        Double montant = 0.0;
        Facture facture = repository.findById(idFacture).get();
        for (FactureArticlesLiaison fal : facture.getArticlesList()) {
            montant += (fal.getQuantite() * repositoryArticle.findById(fal.getArticle().getIdArticle()).get().getPrixUnitaire());
        }
        return montant;
    }

    private Double calculerMontantTVA(Long idFacture) {
        Double montant = 0.0;
        Facture facture = repository.findById(idFacture).get();
        for (FactureArticlesLiaison fal : facture.getArticlesList()) {
            montant += (fal.getQuantite() * ((repositoryArticle.findById(fal.getArticle().getIdArticle()).get().getPrixUnitaire() / 100) * repositoryArticle.findById(fal.getArticle().getIdArticle()).get().getTva().getTauxTva()));
        }
        return montant;
    }

    private Double calculerMontantTTC(Long idFacture) {
        return calculerMontant(idFacture) + calculerMontantTVA(idFacture);
    }

    private boolean exists(Long id) {
        for (Facture facture : read()) {
            if ((facture.isActiveFacture()) && (facture.getIdFacture().equals(id))) {
                return true;
            }
        }
        return false;
    }

    private FactureArticleDto mapFALEntitiesToDtos(FactureArticlesLiaison fal) {
        return new FactureArticleDto(fal.getFacture().getIdFacture(), fal.getArticle().getIdArticle(), fal.getQuantite(), fal.getMontantLigne());
    }

    private List<FactureArticleDto> mapFALEntitiesToDtos(List<FactureArticlesLiaison> falList) {
        List<FactureArticleDto> dtos = new ArrayList<>();
        for (FactureArticlesLiaison fal: falList) {
            dtos.add(mapFALEntitiesToDtos(fal));
        }
        return dtos;
    }

    private FactureArticlesLiaison isOnFacture(Long idFacture, Long idArticle) {
        Facture facture = repository.findById(idFacture).get();
        System.out.println(facture);
        for (FactureArticlesLiaison fal : facture.getArticlesList()) {
            if (fal.getArticle().getIdArticle().equals(idArticle)) {
                return fal;
            }
        }
        return null;
    }
}
