package renting.rentingservice.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class RentRequest implements Serializable{

    public enum StatusReq{
        PENDING,
        RESERVED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rentrequest_id", nullable = false, unique = true)
    private long id;

    @ManyToOne
    @JoinColumn(name="bundle_req", referencedColumnName = "bundlerent_id", nullable = true)
    private BundleRent bundle;

    @Column(nullable = false)
    private StatusReq status;

    @Column(nullable = false)
    private long customerId;

    @Column(nullable = false)
    private long agentId;

    @Column(nullable = false)
    private long vehicleId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_period_req", referencedColumnName = "time_id", nullable = false)
    private StartEnd rentalPeriod;

    public RentRequest() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BundleRent getBundle() {
        return bundle;
    }

    public void setBundle(BundleRent bundle) {
        this.bundle = bundle;
    }

    public StatusReq getStatus() {
        return status;
    }

    public void setStatus(StatusReq status) {
        this.status = status;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getAgentId() {
        return agentId;
    }

    public void setAgentId(long agentId) {
        this.agentId = agentId;
    }

    public StartEnd getRentalPeriod() {
        return rentalPeriod;
    }

    public void setRentalPeriod(StartEnd rentalPeriod) {
        this.rentalPeriod = rentalPeriod;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }
}
