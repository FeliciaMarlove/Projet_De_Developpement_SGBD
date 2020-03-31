package be.iramps.florencemary.devsgbd.dto;

/**
 * DTO POST pour l'entite Facture
 */
public class FactureDtoPost {
    private Long idClient;
    private Long idPaiement;

    /**
     * Constructeur
     * @param idClient (Long)
     * @param idPaiement (Long)
     */
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
