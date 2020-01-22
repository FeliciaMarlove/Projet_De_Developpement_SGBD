package be.iramps.florencemary.devsgbd.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Paiement", schema = "public", catalog = "brico")
public class Paiement implements Serializable {

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

    public Paiement(String nomPaiement, String descPaiement) {
        this.nomPaiement = nomPaiement;
        this.descPaiement = descPaiement;
    }

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

    public void setActifPaiement(boolean actifPaiement) {
        isActifPaiement = actifPaiement;
    }

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
}
