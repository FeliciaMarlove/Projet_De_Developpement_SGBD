package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.FactureArticleDto;
import be.iramps.florencemary.devsgbd.dto.FactureDtoGet;
import be.iramps.florencemary.devsgbd.dto.FactureDtoPost;
import be.iramps.florencemary.devsgbd.model.Facture;
import be.iramps.florencemary.devsgbd.model.FactureArticlesLiaison;

import java.util.List;

public interface FactureService {
    List<Facture> read();
    Object readOne(Long id);
    FactureDtoGet create(Long idClient, Long idPaiement);
    Object delete(Long id);
    List<Object> readActive();
    FactureArticlesLiaison addArticle(Long id, FactureArticleDto article);
    boolean deleteArticle(Long idFacture, Long idArticle);
    boolean articleMinusOne(Long idFacture, Long idArticle);
    FactureDtoGet validateFacture(Long idFacture);
    List<FactureArticlesLiaison> readArticlesOnFacture(Long id);
}
