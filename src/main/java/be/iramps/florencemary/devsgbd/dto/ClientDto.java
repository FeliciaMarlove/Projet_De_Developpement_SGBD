package be.iramps.florencemary.devsgbd.dto;

import java.time.LocalDate;

public class ClientDto {
    private String nomClient;
    private String prenomClient;
    private Long telephoneClient;
    private LocalDate dateNaissanceClient;

    public ClientDto(String nomClient, String prenomClient, Long telephoneClient, LocalDate dateNaissanceClient) {
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.telephoneClient = telephoneClient;
        this.dateNaissanceClient = dateNaissanceClient;
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

    public Long getTelephoneClient() {
        return telephoneClient;
    }

    public void setTelephoneClient(Long telephoneClient) {
        this.telephoneClient = telephoneClient;
    }

    public LocalDate getDateNaissanceClient() {
        return dateNaissanceClient;
    }

    public void setDateNaissanceClient(LocalDate dateNaissanceClient) {
        this.dateNaissanceClient = dateNaissanceClient;
    }
}
