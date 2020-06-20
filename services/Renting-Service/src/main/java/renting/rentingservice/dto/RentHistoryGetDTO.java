package renting.rentingservice.dto;

import renting.rentingservice.domain.RentHistory;

public class RentHistoryGetDTO {
    private long renthistId;
    private long agentId;
    private long customerId;
    private long vehicleId;
    private String startDate;
    private String endDate;
    private double travelledKm;
    private String statement;

    public RentHistoryGetDTO() {
    }

    public RentHistoryGetDTO(RentHistory rh) {
        this.renthistId = rh.getId();
        this.agentId = rh.getAgentId();
        this.customerId = rh.getCustomerId();
        this.vehicleId = rh.getVehicleId();
        this.startDate = rh.getRentalPeriod().getStartTime().toString();
        this.endDate = rh.getRentalPeriod().getEndTime().toString();
        if(rh.getRentStatement()!=null) {
            this.travelledKm = rh.getRentStatement().getTravelledKm();
            this.statement = rh.getRentStatement().getAdditionalInformation();
        }
    }

    public long getRenthistId() {
        return renthistId;
    }

    public void setRenthistId(long renthistId) {
        this.renthistId = renthistId;
    }

    public long getAgentId() {
        return agentId;
    }

    public void setAgentId(long agentId) {
        this.agentId = agentId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public double getTravelledKm() {
        return travelledKm;
    }

    public void setTravelledKm(double travelledKm) {
        this.travelledKm = travelledKm;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }
}
