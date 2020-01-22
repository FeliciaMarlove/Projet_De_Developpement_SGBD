package be.iramps.florencemary.devsgbd.model;

import javax.persistence.*;

@Entity
@Table(name = "Code_postal", schema = "public", catalog = "brico")
public class CodePostal {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "code_postal_generator")
    @SequenceGenerator(name = "code_postal_generator", allocationSize = 1, initialValue = 1)
    @Column(name = "id_code_postal")
    private Long idCodePostal;

    @Column(name = "code_postal")
    private int codePostal;
}
