package advertisement.advertisementservice.dto;

import java.util.List;

public class CheckAvailabiltyDTO {
    private List<Long> vehicleId;
    private String startDate;
    private String endDate;

    public CheckAvailabiltyDTO() {
    }

    public CheckAvailabiltyDTO(List<Long> vehicleId, String startDate, String endDate) {
        this.vehicleId = vehicleId;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public List<Long> getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(List<Long> vehicleId) {
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
}
