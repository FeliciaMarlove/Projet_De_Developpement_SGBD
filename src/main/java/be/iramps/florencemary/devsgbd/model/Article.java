package be.iramps.florencemary.devsgbd.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "Article", schema = "public", catalog = "brico")
public class Article implements Serializable {

    /* _____________________________CHAMPS_____________________________ */
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

    @Column(name = "is_actif_art")
    private boolean isActifArticle;

    /* _____________________________JOINTURES_____________________________ */
    @ManyToOne(targetEntity = Tva.class)
    @JoinColumn(name = "id_tva", referencedColumnName = "id_tva", foreignKey = @ForeignKey(name = "FK_tva_article"))
    private Tva tva;

    @ManyToMany(mappedBy = "articlesList", fetch = FetchType.LAZY)
    private List<Facture> facturesList;

    /* _____________________________GETTERS/SETTERS_____________________________ */
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

    public boolean isActifArticle() {
        return isActifArticle;
    }

    public void setActifArticle(boolean isActifArticle) {
        this.isActifArticle = isActifArticle;
    }

    public Tva getTva() {
        return tva;
    }

    public void setTva(Tva tva) {
        this.tva = tva;
    }

    public List<Facture> getFacturesList() {
        return facturesList;
    }

    /* _____________________________CONSTRUCTEURS_____________________________ */

    public Article(String nomArticle, String descArticle, int stock, Double prixUnitaire, Long codeEAN, Tva tva) {
        this();
        this.nomArticle = nomArticle;
        this.descArticle = descArticle;
        this.stock = stock;
        this.prixUnitaire = prixUnitaire;
        this.codeEAN = codeEAN;
        this.tva = tva;
    }

    public Article() {
        this.isActifArticle = true;
        this.facturesList = new ArrayList();
    }

    /* _____________________________EQUALS/HASHCODE/TOSTRING_____________________________ */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return codeEAN.equals(article.codeEAN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomArticle, codeEAN);
    }

    @Override
    public String toString() {
        return "Article " +
                "idArticle : " + idArticle +
                ", nomArticle : " + nomArticle + '\'' +
                ", descArticle : " + descArticle + '\'' +
                ", stock : " + stock +
                ", prixUnitaire : " + prixUnitaire +
                " €, codeEAN=" + codeEAN +
                ", article actif ? " + isActifArticle +
                ", tva : " + tva;
    }
}
