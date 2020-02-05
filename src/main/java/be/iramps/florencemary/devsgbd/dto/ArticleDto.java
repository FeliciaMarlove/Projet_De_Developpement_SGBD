package be.iramps.florencemary.devsgbd.dto;

public class ArticleDto {
    private String nomArticle;
    private String descArticle;
    private int stock;
    private double prixUnitaire;
    private Long codeEAN;
    private Long idTva;

    public ArticleDto(String nomArticle, String descArticle, int stock, double prixUnitaire, Long codeEAN, Long idTva) {
        this.nomArticle = nomArticle;
        this.descArticle = descArticle;
        this.stock = stock;
        this.prixUnitaire = prixUnitaire;
        this.codeEAN = codeEAN;
        this.idTva = idTva;
    }

    public String getNomArticle() {
        return nomArticle;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    public String getDescArticle() {
        return descArticle;
    }

    public void setDescArticle(String descArticle) {
        this.descArticle = descArticle;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Long getCodeEAN() {
        return codeEAN;
    }

    public void setCodeEAN(Long codeEAN) {
        this.codeEAN = codeEAN;
    }

    public Long getIdTva() {
        return idTva;
    }

    public void setIdTva(Long idTva) {
        this.idTva = idTva;
    }
}
