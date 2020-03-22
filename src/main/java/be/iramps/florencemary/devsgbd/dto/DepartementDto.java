package be.iramps.florencemary.devsgbd.dto;

public class DepartementDto {
    private String nomDepartement;

    public DepartementDto(String nomDepartement) {
        this.nomDepartement = nomDepartement;
    }

    public DepartementDto() {
    }

    public String getNomDepartement() {
        return nomDepartement;
    }

    public void setNomDepartement(String nomDepartement) {
        this.nomDepartement = nomDepartement;
    }

}
