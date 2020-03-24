package be.iramps.florencemary.devsgbd.dto;

public class PaiementDtoGet {
    private Long idPaiement;
    private String nomPaiement;
    private String descPaiement;

    public PaiementDtoGet(Long idPaiement, String nomPaiement, String descPaiement) {
        this.idPaiement = idPaiement;
        this.nomPaiement = nomPaiement;
        this.descPaiement = descPaiement;
    }

    public PaiementDtoGet() {
    }

    public Long getIdPaiement() {
        return idPaiement;
    }

    public void setIdPaiement(Long idPaiement) {
        this.idPaiement = idPaiement;
    }

    public String getNomPaiement() {
        return nomPaiement;
    }

    public void setNomPaiement(String nomPaiement) {
        this.nomPaiement = nomPaiement;
    }

    public String getDescPaiement() {
        return descPaiement;
    }

    public void setDescPaiement(String descPaiement) {
        this.descPaiement = descPaiement;
    }
}
