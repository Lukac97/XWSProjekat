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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_model", referencedColumnName = "brandmodel_id", nullable = false)
    private BrandModel vehicleBrandModel;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fuel_type", referencedColumnName = "fueltype_id", nullable = false)
    private FuelType fuelType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transmission_type", referencedColumnName = "transmissiontype_id", nullable = false)
    private TransmissionType transmissionType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_class", referencedColumnName = "vehicleclass_id", nullable = false)
    private VehicleClass vehicleClass;

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
    @JoinTable(name = "vehicleAvailability", joinColumns = @JoinColumn(name = "vehicle_id"),
            inverseJoinColumns = @JoinColumn(name = "availabledates_id"))
    private List<AvailableDates> vehicleAvailability;

    @ManyToOne
    @JoinColumn(name = "vehicle_pricelist", referencedColumnName = "pricelist_id", nullable = true)
    private Pricelist vehiclePricelist;

    public Vehicle() {
    }

    public Vehicle(long id, long agentId, BrandModel vehicleBrandModel, FuelType fuelType, TransmissionType transmissionType, VehicleClass vehicleClass, double mileage, boolean cdwProtection, String location, long seats, long childrenSeats, double maxKm, List<AvailableDates> vehicleAvailability, Pricelist vehiclePricelist) {
        this.id = id;
        this.agentId = agentId;
        this.vehicleBrandModel = vehicleBrandModel;
        this.fuelType = fuelType;
        this.transmissionType = transmissionType;
        this.vehicleClass = vehicleClass;
        this.mileage = mileage;
        this.cdwProtection = cdwProtection;
        this.location = location;
        this.seats = seats;
        this.childrenSeats = childrenSeats;
        this.maxKm = maxKm;
        this.vehicleAvailability = vehicleAvailability;
        this.vehiclePricelist = vehiclePricelist;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAgentId() {
        return agentId;
    }

    public void setAgentId(long agentId) {
        this.agentId = agentId;
    }

    public BrandModel getVehicleBrandModel() {
        return vehicleBrandModel;
    }

    public void setVehicleBrandModel(BrandModel vehicleBrandModel) {
        this.vehicleBrandModel = vehicleBrandModel;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    public VehicleClass getVehicleClass() {
        return vehicleClass;
    }

    public void setVehicleClass(VehicleClass vehicleClass) {
        this.vehicleClass = vehicleClass;
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

    public double getMaxKm() {
        return maxKm;
    }

    public void setMaxKm(double maxKm) {
        this.maxKm = maxKm;
    }

    public List<AvailableDates> getVehicleAvailability() {
        return vehicleAvailability;
    }

    public void setVehicleAvailability(List<AvailableDates> vehicleAvailability) {
        this.vehicleAvailability = vehicleAvailability;
    }

    public Pricelist getVehiclePricelist() {
        return vehiclePricelist;
    }

    public void setVehiclePricelist(Pricelist vehiclePricelist) {
        this.vehiclePricelist = vehiclePricelist;
    }
}
