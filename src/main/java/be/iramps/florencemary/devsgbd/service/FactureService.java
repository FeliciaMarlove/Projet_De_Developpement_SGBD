package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.FactureArticleDto;
import be.iramps.florencemary.devsgbd.dto.FactureDtoPost;
import be.iramps.florencemary.devsgbd.model.Facture;

import java.util.List;

public interface FactureService {
    List<Facture> read();
    FactureDtoPost readOne(Long id);
    FactureDtoPost create(Long idClient, Long idPaiement);
    //Facture update(Long id, FactureDto update); //nonsense?
    FactureDtoPost delete(Long id);
    List<FactureDtoPost> readActive();
    boolean addArticle(Long id, FactureArticleDto article);
    boolean deleteArticle(Long idFacture, Long idArticle);
    boolean articleMinusOne(Long idFacture, Long idArticle);
    FactureDtoPost validateFacture(Long idFacture);
}
