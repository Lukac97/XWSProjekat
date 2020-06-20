package renting.rentingservice.dto;

public class CancelRequestsDTO {
    private long vehicleId;
    private String startDate;
    private String endDate;
    private long reqId;

    public CancelRequestsDTO() {
    }

    public CancelRequestsDTO(long vehicleId, String startDate, String endDate, long reqId) {
        this.vehicleId = vehicleId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reqId = reqId;
    }

    public CancelRequestsDTO(long vehicleId, String startDate, String endDate) {
        this.vehicleId = vehicleId;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public long getReqId() {
        return reqId;
    }

    public void setReqId(long reqId) {
        this.reqId = reqId;
    }
}
