package be.iramps.florencemary.devsgbd.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Tva", schema = "public", catalog = "brico")
public class Tva implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tva_generator")
    @SequenceGenerator(name = "tva_generator", allocationSize = 1, initialValue = 1)
    @Column(name = "id_tva")
    private Long idTva;

    @Column(name = "taux_tva")
    private Integer tauxTva;

    @Transient
    private String nom;

    @Transient
    private List<Integer> listeTaux = new ArrayList<>();

    //const nom = tauxTva.toString() + "%"
}
