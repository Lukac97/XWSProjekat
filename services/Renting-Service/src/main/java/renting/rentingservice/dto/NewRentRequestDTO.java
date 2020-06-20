package renting.rentingservice.dto;

import java.util.List;

public class NewRentRequestDTO {
    private long customerId;
    private List<Long> vehicleId;
    private List<Long> agentId;
    private boolean separate;
    private String startDate;
    private String endDate;


    public NewRentRequestDTO() {
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public List<Long> getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(List<Long> vehicleId) {
        this.vehicleId = vehicleId;
    }

    public List<Long> getAgentId() {
        return agentId;
    }

    public void setAgentId(List<Long> agentId) {
        this.agentId = agentId;
    }

    public boolean isSeparate() {
        return separate;
    }

    public void setSeparate(boolean separate) {
        this.separate = separate;
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
}
