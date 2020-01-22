package be.iramps.florencemary.devsgbd.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Departement", schema = "public", catalog = "brico")
public class Departement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "departement_generator")
    @SequenceGenerator(name = "departement_generator", allocationSize = 1, initialValue = 1)
    @Column(name = "id_departement")
    private Long idDepartement;

    @Column(name = "nom_departement")
    private String nomDepartement;

    public Departement(String nomDepartement) {
        this.nomDepartement = nomDepartement;
    }

    public Long getIdDepartement() {
        return idDepartement;
    }

    public String getNomDepartement() {
        return nomDepartement;
    }

    public void setNomDepartement(String nomDepartement) {
        this.nomDepartement = nomDepartement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Departement that = (Departement) o;
        return nomDepartement.equals(that.nomDepartement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomDepartement);
    }
}
