package advertisement.advertisementservice.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class BrandModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brandmodel_id", nullable = false, unique = true)
    private long id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false, unique = true)
    private String model;

    public BrandModel() {
    }

    public BrandModel(long id, String brand, String model) {
        this.id = id;
        this.brand = brand;
        this.model = model;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
