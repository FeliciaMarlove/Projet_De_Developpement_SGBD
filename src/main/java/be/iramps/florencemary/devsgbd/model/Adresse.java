package be.iramps.florencemary.devsgbd.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

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

    @Column(name = "code_postal")
    private int codePostal;

    @Column(name = "ville")
    private String ville;

    @Column(name = "pays")
    private String pays;

    public Adresse(String rue, int numero, int codePostal, String ville, String pays) {
        this.rue = rue;
        this.numero = numero;
        this.codePostal = codePostal;
        this.ville = ville;
        this.pays = pays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adresse adresse = (Adresse) o;
        return numero == adresse.numero &&
                codePostal == adresse.codePostal &&
                rue.equals(adresse.rue) &&
                ville.equals(adresse.ville) &&
                pays.equals(adresse.pays);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rue, numero, codePostal, ville, pays);
    }
}
