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
    @SequenceGenerator(name = "adresse_generator", allocationSize = 1, initialValue = 20)
    @Column(name = "id_adresse")
    private Long idAdresse;

    @Column(name = "rue", nullable = false)
    private String rue;

    @Column(name = "numero")
    private int numero;

    @Column(name = "complement")
    private String complementNumero;

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
        if (numero > 0)
            this.numero = numero;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        if (codePostal > 0 && codePostal < 99999)
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
        if (pays.equalsIgnoreCase("Belgique") || pays.equalsIgnoreCase("France"))
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
        if (client != null)
            this.client = client;
    }

    public String getComplementNumero() {
        return complementNumero;
    }

    public void setComplementNumero(String complementNumero) {
        this.complementNumero = complementNumero;
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

    public Adresse(String rue, int numero, String complementNumero, int codePostal, String ville, String pays, Client client) {
        this();
        this.rue = rue;
        this.numero = numero;
        this.complementNumero = complementNumero;
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
                complementNumero.equals(adresse.complementNumero) &&
                codePostal == adresse.codePostal &&
                isActifAdresse == adresse.isActifAdresse &&
                rue.equals(adresse.rue) &&
                ville.equals(adresse.ville) &&
                pays.equals(adresse.pays) &&
                client.getIdClient().equals(adresse.client.getIdClient());
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAdresse, rue, numero, codePostal, ville, pays, isActifAdresse);
    }

    @Override
    public String toString() {
        return "Adresse " +
                "id n° " + idAdresse +
                ", rue = " + rue + '\'' +
                ", numero = " + numero +
                ", complément d'adresse = " + complementNumero +
                ", codePostal = " + codePostal +
                ", ville = " + ville + '\'' +
                ", pays = " + pays + '\'' +
                ", adresse active ?" + isActifAdresse
                ;
    }
}
