package be.iramps.florencemary.devsgbd.model;

import javax.persistence.*;
import java.io.Serializable;

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
}
