package be.iramps.florencemary.devsgbd.dto;

public class AdresseDto {
    private String rue;
    private int numero;
    private String complementNumero;
    private int codePostal;
    private String ville;
    private String pays;
    private Long idClient;

    public AdresseDto(String rue, int numero, String complementNumero, int codePostal, String ville, String pays, Long idClient) {
        this.rue = rue;
        this.numero = numero;
        this.complementNumero = complementNumero;
        this.codePostal = codePostal;
        this.ville = ville;
        this.pays = pays;
        this.idClient = idClient;
    }

    public AdresseDto(String rue, int numero, int codePostal, String ville, String pays, Long idClient) {
        this.rue = rue;
        this.numero = numero;
        this.codePostal = codePostal;
        this.ville = ville;
        this.pays = pays;
        this.idClient = idClient;
    }

    public AdresseDto() {
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
        if (numero > 0)
            this.numero = numero;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        if (codePostal > 0 && codePostal < 99999)
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
        if (pays.equalsIgnoreCase("Belgique") || pays.equalsIgnoreCase("France"))
            this.pays = pays;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public String getComplementNumero() {
        return complementNumero;
    }

    public void setComplementNumero(String complementNumero) {
        this.complementNumero = complementNumero;
    }
}
