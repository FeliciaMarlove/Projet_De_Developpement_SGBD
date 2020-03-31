package be.iramps.florencemary.devsgbd.dto;

import java.time.LocalDate;

/**
 * DTO POST pour l'entite Client
 */
public class ClientDtoPost {
    private String nomClient;
    private String prenomClient;
    private String telephoneClient;
    private LocalDate dateNaissanceClient;

    /**
     * Constructeur
     * @param nomClient (String)
     * @param prenomClient (String)
     * @param telephoneClient (String) length {@literal>} 8 {@literal&&} nombres uniquement
     * @param dateNaissanceClient (LocalDate)
     */
    public ClientDtoPost(String nomClient, String prenomClient, String telephoneClient, LocalDate dateNaissanceClient) {
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.telephoneClient = telephoneClient;
        this.dateNaissanceClient = dateNaissanceClient;
    }

    public ClientDtoPost() {
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }

    public String getTelephoneClient() {
        return telephoneClient;
    }

    public void setTelephoneClient(String telephoneClient) {
        if ((telephoneClient.length() > 8) && (telephoneClient.matches("-?\\d+(\\.\\d+)?")))
            this.telephoneClient = telephoneClient;
    }

    public LocalDate getDateNaissanceClient() {
        return dateNaissanceClient;
    }

    public void setDateNaissanceClient(LocalDate dateNaissanceClient) {
            this.dateNaissanceClient = dateNaissanceClient;
    }

}
