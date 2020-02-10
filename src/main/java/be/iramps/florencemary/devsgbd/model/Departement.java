package be.iramps.florencemary.devsgbd.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Departement", schema = "public", catalog = "brico")
public class Departement implements Serializable {

    /* _____________________________CHAMPS_____________________________ */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "departement_generator")
    @SequenceGenerator(name = "departement_generator", allocationSize = 1, initialValue = 1)
    @Column(name = "id_departement")
    private Long idDepartement;

    @Column(name = "nom_departement", nullable = false)
    private String nomDepartement;

    @Column(name = "is_actif_dept", nullable = false)
    private boolean isActifDepartement;

    /* _____________________________JOINTURES_____________________________ */

    @OneToMany(mappedBy = "departement", targetEntity = Utilisateur.class)
    private List<Utilisateur> utilisateursList = new ArrayList();

    /* _____________________________GETTERS/SETTERS_____________________________ */


    public Long getIdDepartement() {
        return idDepartement;
    }

    public String getNomDepartement() {
        return nomDepartement;
    }

    public void setNomDepartement(String nomDepartement) {
        this.nomDepartement = nomDepartement;
    }

    public boolean isActifDepartement() {
        return isActifDepartement;
    }

    public void setActifDepartement(boolean isActifDepartement) {
        this.isActifDepartement = isActifDepartement;
    }

    public List<Utilisateur> getUtilisateursList() {
        return utilisateursList;
    }

    public void setUtilisateursList(List<Utilisateur> utilisateursList) {
        this.utilisateursList = utilisateursList;
    }
    /* _____________________________CONSTRUCTEURS_____________________________ */

    public Departement(String nomDepartement) {
        this();
        this.nomDepartement = nomDepartement;
    }

    public Departement() {
        this.isActifDepartement = true;
        this.utilisateursList = new ArrayList<>();
    }
    /* _____________________________EQUALS/HASHCODE/TOSTRING_____________________________ */

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

    @Override
    public String toString() {
        return "Departement{" +
                "idDepartement=" + idDepartement +
                ", nomDepartement='" + nomDepartement + '\'' +
                ", isActifDepartement=" + isActifDepartement +
                ", utilisateursList=" + utilisateursList +
                '}';
    }
}
