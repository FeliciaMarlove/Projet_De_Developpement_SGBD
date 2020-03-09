package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.FactureArticleDto;
import be.iramps.florencemary.devsgbd.dto.FactureDto;
import be.iramps.florencemary.devsgbd.model.Facture;

import java.util.List;

public interface FactureService {
    List<Facture> read();
    FactureDto readOne(Long id);
    FactureDto create(Long idClient, Long idPaiement);
    //Facture update(Long id, FactureDto update); //nonsense?
    FactureDto delete(Long id);
    List<FactureDto> readActive();
    boolean addArticle(Long id, FactureArticleDto article);
    boolean deleteArticle(Long idFacture, Long idArticle);
    boolean deleteOneArticle(Long idFacture, Long idArticle);
    Facture validateFacture(Long idFacture);
}
