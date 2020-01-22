package be.iramps.florencemary.devsgbd.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Facture", schema = "public", catalog = "brico")
public class Facture implements Serializable {

    @EmbeddedId
    private PK_Facture pkFacture;

    // génération auto du numero de facture

    // lien vers client

    // lien vers articles - qté ! persistence du prix du jour

    // LocalDate.now

    //constructeur instancier PK

    //jointure  - pays etc
    /*@Column(name = "tva")
    private Tva tauxTva;*/


}
