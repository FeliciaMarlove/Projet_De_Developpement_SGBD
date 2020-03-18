package be.iramps.florencemary.devsgbd.dto;

public class ArticleDto {
    private String nomArticle;
    private String descArticle;
    private int stock;
    private double prixUnitaire;
    private String codeEAN;
    private Long idTva;

    public ArticleDto(String nomArticle, String descArticle, int stock, double prixUnitaire, String codeEAN, Long idTva) {
        this.nomArticle = nomArticle;
        this.descArticle = descArticle;
        this.stock = stock;
        this.prixUnitaire = prixUnitaire;
        this.codeEAN = codeEAN;
        this.idTva = idTva;
    }

    public ArticleDto() {
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
        if (stock >= 0)
            this.stock = stock;
    }
    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        if (prixUnitaire >= 0.00)
            this.prixUnitaire = prixUnitaire;
    }
    public String getCodeEAN() {
        return codeEAN;
    }

    public void setCodeEAN(String codeEAN) {
            this.codeEAN = codeEAN;
    }

    public Long getIdTva() {
        return idTva;
    }

    public void setIdTva(Long idTva) {
        this.idTva = idTva;
    }

    @Override
    public String toString() {
        return "ArticleDto{" +
                "nomArticle='" + nomArticle + '\'' +
                ", descArticle='" + descArticle + '\'' +
                ", stock=" + stock +
                ", prixUnitaire=" + prixUnitaire +
                ", codeEAN='" + codeEAN + '\'' +
                ", idTva=" + idTva +
                '}';
    }
}
