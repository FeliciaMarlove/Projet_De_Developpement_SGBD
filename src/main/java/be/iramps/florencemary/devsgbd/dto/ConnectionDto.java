package be.iramps.florencemary.devsgbd.dto;

/**
 * DTO pour la connexion
 */
public class ConnectionDto {
    private String login;
    private String motDePasse;

    /**
     * Constructeur
     * @param login (String)
     * @param motDePasse (String)
     */
    public ConnectionDto(String login, String motDePasse) {
        this.login = login;
        this.motDePasse = motDePasse;
    }

    public ConnectionDto() {
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
}
