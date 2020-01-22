package be.iramps.florencemary.devsgbd.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Pays", schema = "public", catalog = "brico")
public class Pays implements Serializable {

    @Id
    // load liste sql pays v. format
    private Long idPays_TEMPORAIRE;
}
