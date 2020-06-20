package renting.rentingservice.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class RentStatement implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rentstatement_id", nullable = false, unique = true)
    private long id;

    @Column(nullable=false)
    private double travelledKm;

    @Column
    private String additionalInformation;

    public RentStatement() {
    }

    public RentStatement(double travelledKm, String additionalInformation) {
        this.travelledKm = travelledKm;
        this.additionalInformation = additionalInformation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTravelledKm() {
        return travelledKm;
    }

    public void setTravelledKm(double travelledKm) {
        this.travelledKm = travelledKm;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }
}
