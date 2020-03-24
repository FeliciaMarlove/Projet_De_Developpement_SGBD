package be.iramps.florencemary.devsgbd.dto;

public class FactureDtoGet {
    private Long idFacture;
    private Long idClient;
    private Long idPaiement;

    public FactureDtoGet(Long idFacture, Long idClient, Long idPaiement) {
        this.idFacture = idFacture;
        this.idClient = idClient;
        this.idPaiement = idPaiement;
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
}
