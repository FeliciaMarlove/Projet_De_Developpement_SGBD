package be.iramps.florencemary.devsgbd.dto;

public class FactureDto {
    private Long idClient;
    private Long idPaiement;

    public FactureDto(Long idClient, Long idPaiement) {
        this.idClient = idClient;
        this.idPaiement = idPaiement;
    }

    public FactureDto() {
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
