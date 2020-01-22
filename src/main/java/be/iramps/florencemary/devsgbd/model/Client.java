package be.iramps.florencemary.devsgbd.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "Client", schema = "public", catalog = "brico")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_generator")
    @SequenceGenerator(name = "client_generator", allocationSize = 1, initialValue = 00_001)
    @Column(name = "id_client")
    private Long idClient;

    @Column(name = "nom_client")
    private String nomClient;

    @Column(name = "prenom_client")
    private String prenomClient;

   // JOINTURE A FAIRE
    //private List<Adresse> adressesDuClient;

    @Column(name = "telephone")
    private Long telephoneClient;

    @Column(name = "actif_client")
    private boolean isActifClient;

    @Column(name = "date_naissance")
    private LocalDate dateNaissanceClient;
}
