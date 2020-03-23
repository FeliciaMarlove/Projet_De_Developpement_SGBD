package be.iramps.florencemary.devsgbd.dto;

import java.time.LocalDate;

public class ClientDtoGet {
    private Long idClient;
    private String nomClient;
    private String prenomClient;
    private String telephoneClient;
    private LocalDate dateNaissanceClient;
    private boolean isActifClient;

    public ClientDtoGet(Long idClient, String nomClient, String prenomClient, String telephoneClient, LocalDate dateNaissanceClient, boolean isActifClient) {
        this.idClient = idClient;
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.telephoneClient = telephoneClient;
        this.dateNaissanceClient = dateNaissanceClient;
        this.isActifClient = isActifClient;
    }

    public ClientDtoGet() {
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
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
        this.telephoneClient = telephoneClient;
    }

    public LocalDate getDateNaissanceClient() {
        return dateNaissanceClient;
    }

    public void setDateNaissanceClient(LocalDate dateNaissanceClient) {
        this.dateNaissanceClient = dateNaissanceClient;
    }

    public boolean isActifClient() {
        return isActifClient;
    }

    public void setActifClient(boolean isActifClient) {
        this.isActifClient = isActifClient;
    }
}
