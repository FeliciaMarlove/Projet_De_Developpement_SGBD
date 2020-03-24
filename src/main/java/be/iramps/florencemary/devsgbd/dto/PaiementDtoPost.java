package be.iramps.florencemary.devsgbd.dto;

public class PaiementDtoPost {
    private String nomPaiement;
    private String descPaiement;

    public PaiementDtoPost(String nomPaiement, String descPaiement) {
        this.nomPaiement = nomPaiement;
        this.descPaiement = descPaiement;
    }

    public PaiementDtoPost() {
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
