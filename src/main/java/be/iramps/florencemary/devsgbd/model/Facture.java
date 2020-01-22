package be.iramps.florencemary.devsgbd.model;

import javax.persistence.*;

@Entity
@Table(name = "Facture", schema = "public", catalog = "brico")
public class Facture {

    @Id
   // embedded id -> auto génération d'un id unique sur base des infos
    private Long idFacture_TEMPORAIRE;
    // génération auto du numero de facture

    // lien vers client

    // lien vers articles - qté ! persistence du prix du jour

    // LocalDate.now


}
