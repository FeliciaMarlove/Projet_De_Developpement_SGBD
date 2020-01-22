package be.iramps.florencemary.devsgbd.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class PK_Facture implements Serializable {

    @Column(name = "idClient")
    private Long idClient;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    public PK_Facture(Long idClient) {
        this.idClient = idClient;
        this.dateTime = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PK_Facture that = (PK_Facture) o;
        return idClient.equals(that.idClient) &&
                dateTime.equals(that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idClient, dateTime);
    }
}
