package be.iramps.florencemary.devsgbd.dto;

public class ConnectionMessenger {
    private Long idUser;
    private String message;
    private boolean success;
    private int codeErreur;

    public ConnectionMessenger(Long idUser, String message, boolean success, int codeErreur) {
        this.idUser = idUser;
        this.message = message;
        this.success = success;
        this.codeErreur = codeErreur;
    }

    public ConnectionMessenger() {
    }

    public int getCodeErreur() {
        return codeErreur;
    }

    public void setCodeErreur(int codeErreur) {
        this.codeErreur = codeErreur;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
