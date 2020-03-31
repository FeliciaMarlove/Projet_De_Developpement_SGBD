package be.iramps.florencemary.devsgbd.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

import java.util.*;

@Entity
@Table(name = "Client", schema = "public", catalog = "brico")
public class Client implements Serializable {

    /* _____________________________CHAMPS_____________________________ */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_generator")
    @SequenceGenerator(name = "client_generator", allocationSize = 1, initialValue = 1)
    @Column(name = "id_client")
    private Long idClient;

    @Column(name = "nom_client", nullable = false)
    private String nomClient;

    @Column(name = "prenom_client", nullable = false)
    private String prenomClient;

    @Column(name = "telephone", nullable = false)
    private String telephoneClient;

    @Column(name = "date_naissance", nullable = false)
    private LocalDate dateNaissanceClient;

    @Column(name = "is_actif_clt", nullable = false)
    private boolean isActifClient;

    /* _____________________________JOINTURES_____________________________ */
    @OneToMany(mappedBy = "client", targetEntity = Adresse.class, fetch = FetchType.LAZY)
    private List<Adresse> adressesList;

    @OneToMany(mappedBy = "client", targetEntity = Facture.class, fetch = FetchType.LAZY)
    private List<Facture> facturesList;
    /* _____________________________GETTERS/SETTERS_____________________________ */

    public Long getIdClient() {
        return idClient;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }

    public String getTelephoneClient() {
        return telephoneClient;
    }

    public void setTelephoneClient(String telephoneClient) {
        if ((telephoneClient.length() > 8) && (telephoneClient.matches("-?\\d+(\\.\\d+)?")))
            this.telephoneClient = telephoneClient;
    }

    public LocalDate getDateNaissanceClient() {
        return dateNaissanceClient;
    }

    public void setDateNaissanceClient(LocalDate dateNaissanceClient) {
            this.dateNaissanceClient = dateNaissanceClient;
    }

    public boolean isActifClient() {
        return isActifClient;
    }

    public void setActifClient(boolean isActifClient) {
        this.isActifClient = isActifClient;
    }

    public List<Adresse> getAdressesList() {
        return adressesList;
    }

    public List<Facture> getFacturesList() {
        return facturesList;
    }

    /* _____________________________CONSTRUCTEURS_____________________________ */

    public Client(String nomClient, String prenomClient, String telephoneClient, LocalDate dateNaissanceClient) {
        this();
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.telephoneClient = telephoneClient;
        this.dateNaissanceClient = dateNaissanceClient;
    }

    public Client(String nomClient, String prenomClient, LocalDate dateNaissanceClient) {
        this();
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.dateNaissanceClient = dateNaissanceClient;
    }

    public Client() {
        this.isActifClient = true;
        this.adressesList = new ArrayList<>();
        this.facturesList = new ArrayList<>();
    }

    /* _____________________________EQUALS/HASHCODE/TOSTRING_____________________________ */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return isActifClient == client.isActifClient &&
                nomClient.equals(client.nomClient) &&
                prenomClient.equals(client.prenomClient) &&
                dateNaissanceClient.equals(client.dateNaissanceClient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idClient, nomClient, prenomClient, telephoneClient, dateNaissanceClient, isActifClient, adressesList, facturesList);
    }

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", nomClient='" + nomClient + '\'' +
                ", prenomClient='" + prenomClient + '\'' +
                ", telephoneClient=" + telephoneClient +
                ", dateNaissanceClient=" + dateNaissanceClient +
                ", isActifClient=" + isActifClient +
                ", adressesList=" + adressesList +
//                ", facturesList=" + facturesList +
                '}';
    }
}
