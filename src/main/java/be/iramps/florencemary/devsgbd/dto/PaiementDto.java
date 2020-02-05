package be.iramps.florencemary.devsgbd.dto;

public class PaiementDto {
    private String nomPaiement;
    private String descPaiement;

    public PaiementDto(String nomPaiement, String descPaiement) {
        this.nomPaiement = nomPaiement;
        this.descPaiement = descPaiement;
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
