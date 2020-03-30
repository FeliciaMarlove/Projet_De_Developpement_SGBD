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

    @Column(name = "quantite", nullable = false)
    private Integer quantite;

    @Column(name = "montant_ligne")
    private Double montantLigne;

    /* _____________________________JOINTURES_____________________________ */
    @ManyToOne(targetEntity = Facture.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_facture", referencedColumnName = "id_facture", foreignKey = @ForeignKey(name = "FK_facture_articles_facture"))
    private Facture facture;

    //
    @ManyToOne(targetEntity = Article.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_article", referencedColumnName = "id_article", foreignKey = @ForeignKey(name = "FK_facture_articles_article"))
    private Article article;

    /* _____________________________GETTERS/SETTERS_____________________________ */

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

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    /* _____________________________CONSTRUCTEURS_____________________________ */

    public FactureArticlesLiaison(Facture facture, Article article, Integer quantite, Double montantLigne) {
        this.quantite = quantite;
        this.montantLigne = montantLigne;
        this.facture = facture;
        this.article = article;
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
                "idFactureArticles=" + idFactureArticles +
                ", quantite=" + quantite +
                ", montantLigne=" + montantLigne +
                ", facture=" + facture +
                ", article=" + article +
                '}';
    }
}
