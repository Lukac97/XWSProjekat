package renting.rentingservice.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class RentHistory implements Serializable{

    enum StatusHist{
        PAID,
        CANCELED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "renthistory_id", nullable = false, unique = true)
    private long id;

    @ManyToOne
    @JoinColumn(name="bundle_hist", referencedColumnName = "bundlerent_id", nullable = true)
    private BundleRent bundle;

    @Column(nullable = false, unique = true)
    private StatusHist status;

    @Column(nullable = false)
    private long customerId;

    @Column(nullable = false)
    private long agentId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_period_hist", referencedColumnName = "time_id", nullable = false)
    private StartEnd rentalPeriod;

    @OneToOne
    @JoinColumn(name = "rentstatement_id", referencedColumnName = "rentstatement_id")
    private RentStatement rentStatement;

    public RentHistory() {
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

    public StatusHist getStatus() {
        return status;
    }

    public void setStatus(StatusHist status) {
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

    public RentStatement getRentStatement() {
        return rentStatement;
    }

    public void setRentStatement(RentStatement rentStatement) {
        this.rentStatement = rentStatement;
    }
}
