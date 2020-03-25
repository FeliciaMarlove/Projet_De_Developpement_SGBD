package be.iramps.florencemary.devsgbd.dto;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.time.LocalDateTime;

public class FactureDtoGet {
    private Long idFacture;
    private Long idClient;
    private Long idPaiement;
    private String refFacture;
    private LocalDateTime dateHeure;
    private boolean isActiveFacture;
    private boolean isValidee;
    private Double total;
    private Double totalTva;
    private Double totalTTC;

    public FactureDtoGet(Long idFacture, Long idClient, Long idPaiement, String refFacture, LocalDateTime dateHeure, boolean isActiveFacture, boolean isValidee, Double total, Double totalTva, Double totalTTC) {
        this.idFacture = idFacture;
        this.idClient = idClient;
        this.idPaiement = idPaiement;
        this.refFacture = refFacture;
        this.dateHeure = dateHeure;
        this.isActiveFacture = isActiveFacture;
        this.isValidee = isValidee;
        this.total = total;
        this.totalTva = totalTva;
        this.totalTTC = totalTTC;
    }



    public FactureDtoGet() {
    }

    public Long getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(Long idFacture) {
        this.idFacture = idFacture;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public Long getIdPaiement() {
        return idPaiement;
    }

    public void setIdPaiement(Long idPaiement) {
        this.idPaiement = idPaiement;
    }

    public String getRefFacture() {
        return refFacture;
    }

    public void setRefFacture(String refFacture) {
        this.refFacture = refFacture;
    }

    public LocalDateTime getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(LocalDateTime dateHeure) {
        this.dateHeure = dateHeure;
    }

    public boolean isActiveFacture() {
        return isActiveFacture;
    }

    public void setActiveFacture(boolean isActiveFacture) {
        this.isActiveFacture = isActiveFacture;
    }

    public boolean isValidee() {
        return isValidee;
    }

    public void setValidee(boolean isValidee) {
        this.isValidee = isValidee;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getTotalTva() {
        return totalTva;
    }

    public void setTotalTva(Double totalTva) {
        this.totalTva = totalTva;
    }

    public Double getTotalTTC() {
        return totalTTC;
    }

    public void setTotalTTC(Double totalTTC) {
        this.totalTTC = totalTTC;
    }

    @Override
    public String toString() {
        return "FactureDtoGet{" +
                "idFacture=" + idFacture +
                ", idClient=" + idClient +
                ", idPaiement=" + idPaiement +
                ", refFacture='" + refFacture + '\'' +
                ", dateHeure=" + dateHeure +
                ", isActiveFacture=" + isActiveFacture +
                ", isValidee=" + isValidee +
                ", total=" + total +
                ", totalTva=" + totalTva +
                ", totalTTC=" + totalTTC +
                '}';
    }
}
