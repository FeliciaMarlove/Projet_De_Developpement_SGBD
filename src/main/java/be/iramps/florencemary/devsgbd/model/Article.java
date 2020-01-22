package be.iramps.florencemary.devsgbd.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Article", schema = "public", catalog = "brico")
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "article_generator")
    @SequenceGenerator(name = "article_generator", allocationSize = 1, initialValue = 1)
    @Column(name = "id_article")
    private Long idArticle;

    @Column(name = "nom_article")
    private String nomArticle;

    @Column(name = "desc_article")
    private String descArticle;

    @Column(name = "stock")
    private int stock;

    @Column(name = "prix_unitaire")
    private Double prixUnitaire;

    @Column(name = "ean")
    private Long codeEAN;

    public Article(String nomArticle, String descArticle, int stock, Double prixUnitaire, Long codeEAN) {
        this.nomArticle = nomArticle;
        this.descArticle = descArticle;
        this.stock = stock;
        this.prixUnitaire = prixUnitaire;
        this.codeEAN = codeEAN;
    }

    public Long getIdArticle() {
        return idArticle;
    }

    public String getNomArticle() {
        return nomArticle;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    public String getDescArticle() {
        return descArticle;
    }

    public void setDescArticle(String descArticle) {
        this.descArticle = descArticle;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Long getCodeEAN() {
        return codeEAN;
    }

    public void setCodeEAN(Long codeEAN) {
        this.codeEAN = codeEAN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return nomArticle.equals(article.nomArticle) &&
                codeEAN.equals(article.codeEAN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomArticle, codeEAN);
    }
}
