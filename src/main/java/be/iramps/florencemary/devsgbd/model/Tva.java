package be.iramps.florencemary.devsgbd.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Tva", schema = "public", catalog = "brico")
public class Tva implements Serializable {

    /* _____________________________CHAMPS_____________________________ */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tva_generator")
    @SequenceGenerator(name = "tva_generator", allocationSize = 1, initialValue = 1)
    @Column(name = "id_tva")
    private Long idTva;

    @Column(name = "taux_tva", nullable = false)
    private Integer tauxTva;

    @Column(name = "nom_tva")
    private String nom;

    @Column(name = "is_actif_tva", nullable = false)
    private boolean isActifTva;

    /* _____________________________JOINTURES_____________________________ */
    @OneToMany(mappedBy = "tva", targetEntity = Article.class)
    private List<Article> articlesList = new ArrayList();

    /* _____________________________GETTERS/SETTERS_____________________________ */
    public Long getIdTva() {
        return idTva;
    }

    public Integer getTauxTva() {
        return tauxTva;
    }

    public void setTauxTva(Integer tauxTva) {
        this.tauxTva = tauxTva;
        this.nom = tauxTva.toString() + "%";
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNom(Integer taux) {
        this.nom = taux.toString() + "%";;
    }

    public boolean isActifTva() {
        return isActifTva;
    }

    public void setActifTva(boolean isActifTva) {
        this.isActifTva = isActifTva;
    }

    /* _____________________________CONSTRUCTEURS_____________________________ */

    public Tva(Integer tauxTva) {
        this();
        this.tauxTva = tauxTva;
        this.nom = tauxTva.toString() + "%";
    }

    public Tva() {
        this.isActifTva = true;
        this.articlesList = new ArrayList<>();
    }
    /* _____________________________EQUALS/HASHCODE/TOSTRING_____________________________ */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tva tva = (Tva) o;
        return tauxTva.equals(tva.tauxTva);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tauxTva);
    }

    @Override
    public String toString() {
        return "Tva{" +
                "idTva=" + idTva +
                ", tauxTva=" + tauxTva +
                ", nom='" + nom + '\'' +
                ", isActifTva=" + isActifTva;
    }
}
