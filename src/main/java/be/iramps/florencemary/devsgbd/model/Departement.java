package be.iramps.florencemary.devsgbd.model;

import javax.persistence.*;

@Entity
@Table(name = "Departement", schema = "public", catalog = "brico")
public class Departement {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "departement_generator")
    @SequenceGenerator(name = "departement_generator", allocationSize = 1, initialValue = 1)
    @Column(name = "id_departement")
    private Long idDepartement;

    @Column(name = "nom_departement")
    private String nomDepartement;
}
