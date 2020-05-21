package advertisement.advertisementservice.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Pricelist implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pricelist_id", nullable = false, unique = true)
    private long id;

    @Column(nullable = false)
    private long agentId;

    @Column(nullable = false)
    private double priceMonday;

    @Column(nullable = false)
    private double priceTuesday;

    @Column(nullable = false)
    private double priceWednesday;

    @Column(nullable = false)
    private double priceThursday;

    @Column(nullable = false)
    private double priceFriday;

    @Column(nullable = false)
    private double priceSaturday;

    @Column(nullable = false)
    private double priceSunday;

    @Column(nullable = false)
    private double pricePerKm;

    @Column(nullable = false)
    private double priceCDW;

    @Column(nullable = false)
    private double discount;

    public Pricelist() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPriceMonday() {
        return priceMonday;
    }

    public void setPriceMonday(double priceMonday) {
        this.priceMonday = priceMonday;
    }

    public double getPriceTuesday() {
        return priceTuesday;
    }

    public void setPriceTuesday(double priceTuesday) {
        this.priceTuesday = priceTuesday;
    }

    public double getPriceWednesday() {
        return priceWednesday;
    }

    public void setPriceWednesday(double priceWednesday) {
        this.priceWednesday = priceWednesday;
    }

    public double getPriceThursday() {
        return priceThursday;
    }

    public void setPriceThursday(double priceThursday) {
        this.priceThursday = priceThursday;
    }

    public double getPriceFriday() {
        return priceFriday;
    }

    public void setPriceFriday(double priceFriday) {
        this.priceFriday = priceFriday;
    }

    public double getPriceSaturday() {
        return priceSaturday;
    }

    public void setPriceSaturday(double priceSaturday) {
        this.priceSaturday = priceSaturday;
    }

    public double getPriceSunday() {
        return priceSunday;
    }

    public void setPriceSunday(double priceSunday) {
        this.priceSunday = priceSunday;
    }

    public double getPricePerKm() {
        return pricePerKm;
    }

    public void setPricePerKm(double pricePerKm) {
        this.pricePerKm = pricePerKm;
    }

    public double getPriceCDW() {
        return priceCDW;
    }

    public void setPriceCDW(double priceCDW) {
        this.priceCDW = priceCDW;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public long getAgentId() {
        return agentId;
    }

    public void setAgentId(long agentId) {
        this.agentId = agentId;
    }
}
