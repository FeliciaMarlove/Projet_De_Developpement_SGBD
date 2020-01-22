package be.iramps.florencemary.devsgbd.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Adresse", schema = "public", catalog = "brico")
public class Adresse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adresse_generator")
    @SequenceGenerator(name = "adresse_generator", allocationSize = 1, initialValue = 1)
    @Column(name = "id_adresse")
    private Long idAdresse;

    @Column(name = "rue")
    private String rue;

    @Column(name = "numero")
    private int numero;

    //jointures
    /*@Column(name = "code_postal")
    private CodePostal codePostal;

    @Column(name = "ville")
    private Ville ville;*/
}
