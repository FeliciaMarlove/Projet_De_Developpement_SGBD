package be.iramps.florencemary.devsgbd.model;

import javax.persistence.*;
import java.io.Serializable;

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
}
