package be.iramps.florencemary.devsgbd.dto;

/**
 * DTO pour FactureArticlesLiaison
 */
public class FactureArticleDto {
    Long idFacture;
    Long idArticle;
    Integer quantite;
    Double montantLigne;

    /**
     * Constructeur
     * @param idFacture (Long)
     * @param idArticle (Long)
     * @param quantite (Integer)
     * @param montantLigne (Double)
     */
    public FactureArticleDto(Long idFacture, Long idArticle, Integer quantite, Double montantLigne) {
        this.idFacture = idFacture;
        this.idArticle = idArticle;
        this.quantite = quantite;
        this.montantLigne = montantLigne;
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

    public Double getMontantLigne() {
        return montantLigne;
    }

    public void setMontantLigne(Double montantLigne) {
        this.montantLigne = montantLigne;
    }

    @Override
    public String toString() {
        return "FactureArticleDto{" +
                "idFacture=" + idFacture +
                ", idArticle=" + idArticle +
                ", quantite=" + quantite +
                ", montantLigne=" + montantLigne +
                '}';
    }
}
