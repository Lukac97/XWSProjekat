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
    private String transmissionType;

    public TransmissionType() {
    }

    public TransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }
}
