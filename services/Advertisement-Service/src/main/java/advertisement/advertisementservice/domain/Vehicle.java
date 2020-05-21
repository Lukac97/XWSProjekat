package advertisement.advertisementservice.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Vehicle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id", nullable = false, unique = true)
    private long id;

    @Column(nullable = false)
    private long agentId;

    @Column(nullable = false)
    private String vehicleBrand;

    @Column(nullable = false)
    private String vehicleModel;

    @Column(nullable = false)
    private String fuelType;

    @Column(nullable = false)
    private String transmission;

    @Column(nullable = false)
    private String vehicleType;

    @Column(nullable = false)
    private double mileage;

    @Column(nullable = false)
    private boolean cdwProtection;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private long seats;

    @Column(nullable = false)
    private long childrenSeats;

    @Column
    private double maxKm;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "vehicleAvailability", joinColumns = @JoinColumn(name="vehicle_id"),
            inverseJoinColumns = @JoinColumn(name="availabledates_id"))
    private List<AvailableDates> vehicleAvailability;

    @ManyToOne
    @JoinColumn(name = "vehicle_pricelist", referencedColumnName = "pricelist_id", nullable = true)
    private Pricelist vehicle_pricelist;

    public Vehicle() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public boolean isCdwProtection() {
        return cdwProtection;
    }

    public void setCdwProtection(boolean cdwProtection) {
        this.cdwProtection = cdwProtection;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getSeats() {
        return seats;
    }

    public void setSeats(long seats) {
        this.seats = seats;
    }

    public long getChildrenSeats() {
        return childrenSeats;
    }

    public void setChildrenSeats(long childrenSeats) {
        this.childrenSeats = childrenSeats;
    }

    public List<AvailableDates> getVehicleAvailability() {
        return vehicleAvailability;
    }

    public void setVehicleAvailability(List<AvailableDates> vehicleAvailability) {
        this.vehicleAvailability = vehicleAvailability;
    }

    public Pricelist getVehicle_pricelist() {
        return vehicle_pricelist;
    }

    public void setVehicle_pricelist(Pricelist vehicle_pricelist) {
        this.vehicle_pricelist = vehicle_pricelist;
    }

    public double getMaxKm() {
        return maxKm;
    }

    public void setMaxKm(double maxKm) {
        this.maxKm = maxKm;
    }

    public long getAgentId() {
        return agentId;
    }

    public void setAgentId(long agentId) {
        this.agentId = agentId;
    }
}
