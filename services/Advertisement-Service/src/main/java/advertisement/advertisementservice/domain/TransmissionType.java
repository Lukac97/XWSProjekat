package advertisement.advertisementservice.domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class TransmissionType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transmissiontype_id", nullable = false, unique = true)
    private long id;

    @Column(nullable = false, unique = true)
    private String fuelType;

    public TransmissionType() {
    }

    public TransmissionType(long id, String fuelType) {
        this.id = id;
        this.fuelType = fuelType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
}
