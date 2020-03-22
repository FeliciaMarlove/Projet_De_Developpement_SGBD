package be.iramps.florencemary.devsgbd.dto;


public class UtilisateurDto {
    private String nomUtilisateur;
    private String prenomUtilisateur;
    private String login;
    private String motDePasse;
    private String poste;
    private String nomDepartement;

    public UtilisateurDto(String nomUtilisateur, String prenomUtilisateur, String login, String motDePasse, String poste, String nomDepartement) {
        this.nomUtilisateur = nomUtilisateur;
        this.prenomUtilisateur = prenomUtilisateur;
        this.login = login;
        this.motDePasse = motDePasse;
        this.poste = poste;
        this.nomDepartement = nomDepartement;
    }

    public UtilisateurDto() {
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getPrenomUtilisateur() {
        return prenomUtilisateur;
    }

    public void setPrenomUtilisateur(String prenomUtilisateur) {
        this.prenomUtilisateur = prenomUtilisateur;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getNomDepartement() {
        return nomDepartement;
    }

    public void setNomDepartement(String nomDepartement) {
        this.nomDepartement = nomDepartement;
    }

    @Override
    public String toString() {
        return "UtilisateurDto{" +
                "nomUtilisateur='" + nomUtilisateur + '\'' +
                ", prenomUtilisateur='" + prenomUtilisateur + '\'' +
                ", login='" + login + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", poste='" + poste + '\'' +
                ", nomDepartement='" + nomDepartement + '\'' +
                '}';
    }
}
