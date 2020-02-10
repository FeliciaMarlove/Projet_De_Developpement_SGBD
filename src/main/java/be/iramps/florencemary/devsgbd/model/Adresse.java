package be.iramps.florencemary.devsgbd.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Adresse", schema = "public", catalog = "brico")
public class Adresse implements Serializable {

    /* _____________________________CHAMPS_____________________________ */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adresse_generator")
    @SequenceGenerator(name = "adresse_generator", allocationSize = 1, initialValue = 1)
    @Column(name = "id_adresse")
    private Long idAdresse;

    @Column(name = "rue", nullable = false)
    private String rue;

    @Column(name = "numero")
    private int numero;

    @Column(name = "code_postal", nullable = false)
    private int codePostal;

    @Column(name = "ville", nullable = false)
    private String ville;

    @Column(name = "pays", nullable = false)
    private String pays;

    @Column(name = "is_actif_adr", nullable = false)
    private boolean isActifAdresse;

    /* _____________________________JOINTURES_____________________________ */
    @ManyToOne(targetEntity = Client.class)
    @JoinColumn(name = "id_client", referencedColumnName = "id_client", foreignKey = @ForeignKey(name = "FK_client_adresse"))
    private Client client;

    /* _____________________________GETTERS/SETTERS_____________________________ */
    public Long getIdAdresse() {
        return idAdresse;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public boolean isActifAdresse() {
        return isActifAdresse;
    }

    public void setActifAdresse(boolean isActifAdresse) {
        this.isActifAdresse = isActifAdresse;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    /* _____________________________CONSTRUCTEURS_____________________________ */
    public Adresse(String rue, int numero, int codePostal, String ville, String pays, Client client) {
        this();
        this.rue = rue;
        this.numero = numero;
        this.codePostal = codePostal;
        this.ville = ville;
        this.pays = pays;
        this.client = client;
    }

    public Adresse() {
        this.isActifAdresse = true;
    }

    /* _____________________________EQUALS/HASHCODE/TOSTRING_____________________________ */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adresse adresse = (Adresse) o;
        return numero == adresse.numero &&
                codePostal == adresse.codePostal &&
                isActifAdresse == adresse.isActifAdresse &&
                idAdresse.equals(adresse.idAdresse) &&
                rue.equals(adresse.rue) &&
                ville.equals(adresse.ville) &&
                pays.equals(adresse.pays) &&
                client.equals(adresse.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAdresse, rue, numero, codePostal, ville, pays, isActifAdresse, client);
    }

    @Override
    public String toString() {
        return "Adresse " +
                "id n° " + idAdresse +
                ", rue = " + rue + '\'' +
                ", numero = " + numero +
                ", codePostal = " + codePostal +
                ", ville = " + ville + '\'' +
                ", pays = " + pays + '\'' +
                ", adresse active ?" + isActifAdresse +
                ", client =" + client;
    }
}
