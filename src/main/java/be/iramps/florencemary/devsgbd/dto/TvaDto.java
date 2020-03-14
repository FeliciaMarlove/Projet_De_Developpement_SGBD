package be.iramps.florencemary.devsgbd.dto;

public class TvaDto {
    private Integer tauxTva;

    public TvaDto(Integer tauxTva) {
        this.tauxTva = tauxTva;
    }

    public TvaDto() {
    }

    public Integer getTauxTva() {
        return tauxTva;
    }

    public void setTauxTva(Integer tauxTva) {
        if (tauxTva >= 0 && tauxTva < 22)
            this.tauxTva = tauxTva;
    }
}
