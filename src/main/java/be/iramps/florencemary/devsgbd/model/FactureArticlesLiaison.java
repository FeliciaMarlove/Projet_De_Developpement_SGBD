package be.iramps.florencemary.devsgbd.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "facture_articles", schema = "public", catalog = "brico")
public class FactureArticlesLiaison implements Serializable {

    /* _____________________________CHAMPS_____________________________ */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "facture_articles_generator")
    @SequenceGenerator(name = "facture_articles_generator", allocationSize = 1, initialValue = 1)
    @Column(name = "id_facture_articles")
    private Long idFactureArticles;

    @Column(name = "id_facture", nullable = false)
    private Long idFacture;

    @Column(name = "id_article", nullable = false)
    private Long idArticle;

    @Column(name = "quantite", nullable = false)
    private Integer quantite;

    @Transient
    private Double montantLigne;

    /* _____________________________JOINTURES_____________________________ */


    /* _____________________________GETTERS/SETTERS_____________________________ */

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

    /* _____________________________CONSTRUCTEURS_____________________________ */
    public FactureArticlesLiaison(Long idFacture, Long idArticle, Integer quantite) {
        this.idFacture = idFacture;
        this.idArticle = idArticle;
        this.quantite = quantite;
    }

    public FactureArticlesLiaison() {
    }

    /* _____________________________EQUALS/HASHCODE/TOSTRING_____________________________ */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FactureArticlesLiaison that = (FactureArticlesLiaison) o;
        return idFactureArticles.equals(that.idFactureArticles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFactureArticles);
    }

    @Override
    public String toString() {
        return "FactureArticlesLiaison{" +
                "idFacture=" + idFacture +
                ", idArticle=" + idArticle +
                ", quantite=" + quantite +
                '}';
    }
}
