package advertisement.advertisementservice.dto;

import java.util.List;

public class UpdateAvailabiltyDTO {
    private long vehicleId;
    private boolean available;
    private String startDate;
    private String endDate;

    public UpdateAvailabiltyDTO() {
    }

    public UpdateAvailabiltyDTO(long vehicleId, boolean available, String startDate, String endDate) {
        this.vehicleId = vehicleId;
        this.available = available;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
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
