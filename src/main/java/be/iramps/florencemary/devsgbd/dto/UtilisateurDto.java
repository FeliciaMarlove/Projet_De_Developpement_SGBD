package be.iramps.florencemary.devsgbd.dto;


public class UtilisateurDto {
    private String nomUtilisateur;
    private String prenomUtilisateur;
    private String login;
    private String motDePasse;
    private String poste;
    private Long idDepartement;

    public UtilisateurDto(String nomUtilisateur, String prenomUtilisateur, String login, String motDePasse, String poste, Long idDepartement) {
        this.nomUtilisateur = nomUtilisateur;
        this.prenomUtilisateur = prenomUtilisateur;
        this.login = login;
        this.motDePasse = motDePasse;
        this.poste = poste;
        this.idDepartement = idDepartement;
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

    public Long getIdDepartement() {
        return idDepartement;
    }

    public void setIdDepartement(Long idDepartement) {
        this.idDepartement = idDepartement;
    }
}
