package be.iramps.florencemary.devsgbd.dto;

/**
 * DTO GET pour l'entite Article
 */
public class ArticleDtoGet {
    private Long idArticle;
    private String nomArticle;
    private String descArticle;
    private int stock;
    private double prixUnitaire;
    private String codeEAN;
    private Long idTva;

    /**
     * Constructeur
     * @param idArticle (Long)
     * @param nomArticle (String)
     * @param descArticle (String)
     * @param stock (int)
     * @param prixUnitaire (double)
     * @param codeEAN (String)
     * @param idTva (Long)
     */
    public ArticleDtoGet(Long idArticle, String nomArticle, String descArticle, int stock, double prixUnitaire, String codeEAN, Long idTva) {
        this.idArticle = idArticle;
        this.nomArticle = nomArticle;
        this.descArticle = descArticle;
        this.stock = stock;
        this.prixUnitaire = prixUnitaire;
        this.codeEAN = codeEAN;
        this.idTva = idTva;
    }

    public ArticleDtoGet() {
    }

    public Long getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(Long idArticle) {
        this.idArticle = idArticle;
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
}
