package be.iramps.florencemary.devsgbd.model;

import javax.persistence.*;

@Entity
@Table(name = "Pays", schema = "public", catalog = "brico")
public class Pays {

    @Id
    // load liste sql pays v. format
    private Long idPays_Temporaire;
}
