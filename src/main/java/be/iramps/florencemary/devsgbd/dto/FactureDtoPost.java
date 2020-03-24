package be.iramps.florencemary.devsgbd.dto;

public class FactureDtoPost {
    private Long idClient;
    private Long idPaiement;

    public FactureDtoPost(Long idClient, Long idPaiement) {
        this.idClient = idClient;
        this.idPaiement = idPaiement;
    }

    public FactureDtoPost() {
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
