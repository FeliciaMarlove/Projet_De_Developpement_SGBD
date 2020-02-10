package be.iramps.florencemary.devsgbd.dto;

public class FactureArticleDto {
    Long idFacture;
    Long idArticle;
    Integer quantite;

    public FactureArticleDto(Long idFacture, Long idArticle, Integer quantite) {
        this.idFacture = idFacture;
        this.idArticle = idArticle;
        this.quantite = quantite;
    }

    public FactureArticleDto() {
    }

    public Long getIdFacture() {
        return idFacture;
    }

    public Long getIdArticle() {
        return idArticle;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }
}
