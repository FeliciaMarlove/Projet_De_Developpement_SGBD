package be.iramps.florencemary.devsgbd.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "Facture", schema = "public", catalog = "brico")
public class Facture implements Serializable {

    //_____________________________CHAMPS_____________________________

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "facture_generator")
    @SequenceGenerator(name = "facture_generator", allocationSize = 1, initialValue = 1)
    @Column(name = "id_facture")
    private Long idFacture;

    @Column(name = "ref_facture", nullable = false)
    private String refFacture;

    @Column(name = "date_heure", insertable = false, updatable = false, nullable = false)
    private LocalDateTime dateHeure;

    @Column(name = "actif_facture", nullable = false)
    private boolean isActiveFacture;

    @Transient
    private Double total;

    //_____________________________JOINTURES_____________________________
    @ManyToOne(targetEntity = Client.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client", referencedColumnName = "id_client", foreignKey = @ForeignKey(name = "FK_client_facture"))
    private Client client;

    @OneToMany(mappedBy = "facture", targetEntity = FactureArticlesLiaison.class, fetch = FetchType.LAZY)
    private List<FactureArticlesLiaison> listeArticlesFactures;

    @ManyToOne(targetEntity = Paiement.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paiement", referencedColumnName = "id_paiement", foreignKey = @ForeignKey(name = "FK_paiement_facture"))
    private Paiement paiement;

    //_____________________________GETTERS/SETTERS_____________________________

    public Long getIdFacture() {
        return idFacture;
    }

    public LocalDateTime getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(LocalDateTime dateHeure) {
        this.dateHeure = dateHeure;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<FactureArticlesLiaison> getArticlesList() {
        return listeArticlesFactures;
    }

    public String getRefFacture() {
        return refFacture;
    }

    public boolean isActiveFacture() {
        return isActiveFacture;
    }

    public void setActiveFacture(boolean isActiveFacture) {
        this.isActiveFacture = isActiveFacture;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Paiement getPaiement() {
        return paiement;
    }

    public void setListeArticlesFactures(List<FactureArticlesLiaison> listeArticlesFactures) {
        this.listeArticlesFactures = listeArticlesFactures;
    }

    //_____________________________CONSTRUCTEURS_____________________________

    public Facture(Client client, Paiement paiement) {
        this();
        this.client = client;
        this.paiement = paiement;
        this.refFacture = dateHeure.toString() + "" + client.getNomClient().substring(0,4).toUpperCase() + "" + client.getPrenomClient().substring(0,4);
    }

    public Facture() {
        this.dateHeure = LocalDateTime.now();
        this.listeArticlesFactures = new ArrayList<>();
    }
//_____________________________EQUALS/HASHCODE/TOSTRING_____________________________

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Facture facture = (Facture) o;
        return  dateHeure.equals(facture.dateHeure) &&
                client.equals(facture.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFacture, refFacture, dateHeure, client, listeArticlesFactures);
    }

    @Override
    public String toString() {
        return "Facture{" +
                "idFacture=" + idFacture +
                ", refFacture='" + refFacture + '\'' +
                ", dateHeure=" + dateHeure +
                ", client=" + client +
                ", paiement=" + paiement +
                ", articlesList=" + listeArticlesFactures +
                '}';
    }
}
