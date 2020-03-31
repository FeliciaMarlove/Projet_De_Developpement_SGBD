package be.iramps.florencemary.devsgbd.dto;

public class DepartementDto {
    private Long idDepartement;
    private String nomDepartement;

    public DepartementDto(String nomDepartement) {
        this.nomDepartement = nomDepartement;
    }

    public DepartementDto(Long idDepartement, String nomDepartement) {
        this.idDepartement = idDepartement;
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

    public Long getIdDepartement() {
        return idDepartement;
    }

    public void setIdDepartement(Long idDepartement) {
        this.idDepartement = idDepartement;
    }
}
