package be.iramps.florencemary.devsgbd.model;

import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Utilisateur", schema = "public", catalog = "brico")
public class Utilisateur implements Serializable {

    /* _____________________________CHAMPS_____________________________ */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "utilisateur_generator")
    @SequenceGenerator(name = "utlisateur_generator", allocationSize = 1, initialValue = 20)
    @Column(name = "id_utilisateur")
    private Long idUtilisateur;

    @Column(name = "nom_utilisateur", nullable = false)
    private String nomUtilisateur;

    @Column(name = "prenom_utilisateur", nullable = false)
    private String prenomUtilisateur;

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "mot_de_passe", nullable = false)
    private String motDePasse;

    @Column(name = "poste", nullable = false)
    private String poste;

    @Column(name = "is_actif_util", nullable = false)
    private boolean isActifUtilisateur;

    /* _____________________________JOINTURES_____________________________ */
    @ManyToOne(targetEntity = Departement.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_departement",
            referencedColumnName = "id_departement",
            foreignKey = @ForeignKey(name = "FK_utilisateur_departement"))
    private Departement departement;

    /* _____________________________GETTERS/SETTERS_____________________________ */

    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getPrenomUtilisateur() {
        return prenomUtilisateur;
    }

    public void setPrenomUtilisateur(String prenomUtilisateur) {
        this.prenomUtilisateur = prenomUtilisateur;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if (login.length() > 3) this.login = login;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = BCrypt.hashpw(motDePasse, BCrypt.gensalt(12));
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public boolean isActifUtilisateur() {
        return isActifUtilisateur;
    }

    public void setActifUtilisateur(boolean isActifUtilisateur) {
        this.isActifUtilisateur = isActifUtilisateur;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
            this.departement = departement;
    }
    /* _____________________________CONSTRUCTEURS_____________________________ */

    public Utilisateur(String nomUtilisateur, String prenomUtilisateur, String login, String motDePasse, String poste, Departement departement) {
        this();
        this.nomUtilisateur = nomUtilisateur;
        this.prenomUtilisateur = prenomUtilisateur;
        this.login = login;
        this.motDePasse = BCrypt.hashpw(motDePasse, BCrypt.gensalt(12));
        this.poste = poste;
        this.departement = departement;
    }

    public Utilisateur() {
        this.isActifUtilisateur = true;
    }

    /* _____________________________EQUALS/HASHCODE/TOSTRING_____________________________ */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilisateur that = (Utilisateur) o;
        return isActifUtilisateur == that.isActifUtilisateur &&
                nomUtilisateur.equals(that.nomUtilisateur) &&
                prenomUtilisateur.equals(that.prenomUtilisateur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUtilisateur, nomUtilisateur, prenomUtilisateur, login, motDePasse, poste, isActifUtilisateur, departement);
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "idUtilisateur=" + idUtilisateur +
                ", nomUtilisateur='" + nomUtilisateur + '\'' +
                ", prenomUtilisateur='" + prenomUtilisateur + '\'' +
                ", login='" + login + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", poste='" + poste + '\'' +
                ", isActifUtilisateur=" + isActifUtilisateur +
                ", departement=" + departement +
                '}';
    }
}
