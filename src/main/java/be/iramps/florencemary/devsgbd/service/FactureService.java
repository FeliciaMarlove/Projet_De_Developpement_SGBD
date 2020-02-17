package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.FactureArticleDto;
import be.iramps.florencemary.devsgbd.dto.FactureDto;
import be.iramps.florencemary.devsgbd.model.Facture;

import java.util.List;

public interface FactureService {
    List<Facture> read();
    Facture readOne(Long id);
    void create(FactureDto newItem);
    //Facture update(Long id, FactureDto update); //nonsense
    Facture delete(Long id);
    List<Facture> readActive();
    boolean addArticle(Long id, FactureArticleDto article);
    boolean deleteArticle(Long idFacture, Long idArticle);
    boolean deleteOneArticle(Long idFacture, Long idArticle);
    public void validateFacture(Long idFacture);
}
