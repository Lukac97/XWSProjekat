package advertisement.advertisementservice.domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class VehicleClass implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicleclass_id", nullable = false, unique = true)
    private long id;

    @Column(nullable = false, unique = true)
    private String vehicleClass;

    public VehicleClass() {
    }

    public VehicleClass(long id, String vehicleClass) {
        this.id = id;
        this.vehicleClass = vehicleClass;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVehicleClass() {
        return vehicleClass;
    }

    public void setVehicleClass(String vehicleClass) {
        this.vehicleClass = vehicleClass;
    }
}
