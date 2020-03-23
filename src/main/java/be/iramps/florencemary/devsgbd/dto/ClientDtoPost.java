package be.iramps.florencemary.devsgbd.dto;

import java.time.LocalDate;

public class ClientDtoPost {
    private String nomClient;
    private String prenomClient;
    private String telephoneClient;
    private LocalDate dateNaissanceClient;

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
        if (dateNaissanceClient.getYear() <= (LocalDate.now().getYear() - 18))
            this.dateNaissanceClient = dateNaissanceClient;
    }

}
