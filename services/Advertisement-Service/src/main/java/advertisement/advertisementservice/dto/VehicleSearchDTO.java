package advertisement.advertisementservice.dto;

import advertisement.advertisementservice.domain.AvailableDates;
import advertisement.advertisementservice.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleSearchDTO {
    private long vehicleId;
    private String model;
    private String fuelType;
    private String transmissionType;
    private String vehicleClass;
    private double mileage;
    private boolean cdwProtection;
    private String location;
    private long seats;
    private long childrenSeats;
    private double maxKm;
    private List<String> startDates;
    private List<String> endDates;
    private String pricelistId;

    public VehicleSearchDTO() {
    }

    public VehicleSearchDTO(Vehicle v){
        this.vehicleId = v.getId();
        this.model = v.getVehicleBrandModel().getModel();
        this.fuelType = v.getFuelType().getFuelType();
        this.transmissionType = v.getTransmissionType().getTransmissionType();
        this.vehicleClass = v.getVehicleClass().getVehicleClass();
        this.mileage = v.getMileage();
        this.cdwProtection = v.isCdwProtection();
        this.location = v.getLocation();
        this.seats = v.getSeats();
        this.childrenSeats = v.getChildrenSeats();
        this.maxKm = v.getMaxKm();
        this.startDates = new ArrayList<String>();
        this.endDates = new ArrayList<String>();
        for(AvailableDates avda : v.getVehicleAvailability()){
            this.startDates.add(avda.getStartDate().toString());
            this.endDates.add(avda.getEndDate().toString());
        }
        if(v.getVehiclePricelist() != null) {
            this.pricelistId = v.getVehiclePricelist().getId() + "";
        }
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public String getVehicleClass() {
        return vehicleClass;
    }

    public void setVehicleClass(String vehicleClass) {
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

    public List<String> getStartDates() {
        return startDates;
    }

    public void setStartDates(List<String> startDates) {
        this.startDates = startDates;
    }

    public List<String> getEndDates() {
        return endDates;
    }

    public void setEndDates(List<String> endDates) {
        this.endDates = endDates;
    }

    public String getPricelistId() {
        return pricelistId;
    }

    public void setPricelistId(String pricelistId) {
        this.pricelistId = pricelistId;
    }
}
