package be.iramps.florencemary.devsgbd.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Utilisateur", schema = "public", catalog = "brico")
public class Utilisateur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "utilisateur_generator")
    @SequenceGenerator(name = "utlisateur_generator", allocationSize = 1, initialValue = 1)
    @Column(name = "id_utilisateur")
    private Long idUtilisateur;

    @Column(name = "nom_utilisateur")
    private String nomUtilisateur;

    @Column(name = "prenom_utilisateur")
    private String prenomUtilisateur;

    @Column(name = "login")
    private String login; // génération auto v. notes

    // Bcrypt avant de faire des méthodes d'utilisation du mdp !
    @Column(name = "mot_de_passe")
    private String motDePasse;

    @Column(name = "poste")
    private String poste;

    // département jointure
}
