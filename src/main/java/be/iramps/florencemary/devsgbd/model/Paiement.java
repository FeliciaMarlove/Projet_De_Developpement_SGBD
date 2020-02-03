package be.iramps.florencemary.devsgbd.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "Paiement", schema = "public", catalog = "brico")
public class Paiement implements Serializable {

    /* _____________________________CHAMPS_____________________________ */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paiement_generator")
    @SequenceGenerator(name = "paiement_generator", allocationSize = 1, initialValue = 1)
    @Column(name = "id_paiement")
    private Long idPaiement;

    @Column(name = "nom_paiement", nullable = false)
    private String nomPaiement;

    @Column(name = "desc_paiement")
    private String descPaiement;

    @Column(name = "actif_paiement")
    private boolean isActifPaiement;

    /* _____________________________JOINTURES_____________________________ */
    @OneToMany(mappedBy = "paiement", targetEntity = Facture.class)
    private List<Facture> facturesList;

    /* _____________________________GETTERS/SETTERS_____________________________ */
    public Long getIdPaiement() {
        return idPaiement;
    }

    public String getNomPaiement() {
        return nomPaiement;
    }

    public void setNomPaiement(String nomPaiement) {
        this.nomPaiement = nomPaiement;
    }

    public String getDescPaiement() {
        return descPaiement;
    }

    public void setDescPaiement(String descPaiement) {
        this.descPaiement = descPaiement;
    }

    public boolean isActifPaiement() {
        return isActifPaiement;
    }

    public void setActifPaiement(boolean isActifPaiement) {
        this.isActifPaiement = isActifPaiement;
    }

    public List<Facture> getFacturesList() {
        return facturesList;
    }

    /* _____________________________CONSTRUCTEURS_____________________________ */

    public Paiement(String nomPaiement, String descPaiement) {
        this();
        this.nomPaiement = nomPaiement;
        this.descPaiement = descPaiement;
    }

    public Paiement() {
        this.facturesList = new ArrayList<>();
        this.isActifPaiement = true;
    }
    /* _____________________________EQUALS/HASHCODE/TOSTRING_____________________________ */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paiement paiement = (Paiement) o;
        return nomPaiement.equals(paiement.nomPaiement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomPaiement);
    }

    @Override
    public String toString() {
        return "Paiement{" +
                "idPaiement=" + idPaiement +
                ", nomPaiement='" + nomPaiement + '\'' +
                ", descPaiement='" + descPaiement + '\'' +
                ", isActifPaiement=" + isActifPaiement;
    }
}
