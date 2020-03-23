package be.iramps.florencemary.devsgbd.dto;

public class AdresseDtoGet {
    private Long idAdresse;
    private String rue;
    private int numero;
    private String complementNumero;
    private int codePostal;
    private String ville;
    private String pays;
    private Long idClient;

    public AdresseDtoGet(Long idAdresse, String rue, int numero, String complementNumero, int codePostal, String ville, String pays, Long idClient) {
        this.idAdresse = idAdresse;
        this.rue = rue;
        this.numero = numero;
        this.complementNumero = complementNumero;
        this.codePostal = codePostal;
        this.ville = ville;
        this.pays = pays;
        this.idClient = idClient;
    }

    public AdresseDtoGet() {
    }

    public Long getIdAdresse() {
        return idAdresse;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplementNumero() {
        return complementNumero;
    }

    public void setComplementNumero(String complementNumero) {
        this.complementNumero = complementNumero;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }
}
