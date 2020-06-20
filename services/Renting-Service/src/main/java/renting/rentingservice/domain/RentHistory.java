package renting.rentingservice.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class RentHistory implements Serializable{

    public enum StatusHist{
        PAID,
        CANCELED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "renthistory_id", nullable = false, unique = true)
    private long id;

    @Column(nullable = false)
    private StatusHist status;

    @Column(nullable = false)
    private long customerId;

    @Column(nullable = false)
    private long agentId;

    @Column(nullable = false)
    private long vehicleId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_period_hist", referencedColumnName = "time_id", nullable = false)
    private StartEnd rentalPeriod;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rentstatement_id", referencedColumnName = "rentstatement_id")
    private RentStatement rentStatement;

    public RentHistory() {
    }

    public RentHistory(RentRequest rq){
        this.customerId = rq.getCustomerId();
        this.agentId = rq.getAgentId();
        this.vehicleId = rq.getVehicleId();
        this.rentalPeriod = new StartEnd(rq.getRentalPeriod().getStartTime(), rq.getRentalPeriod().getEndTime());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }
}
