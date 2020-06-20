package renting.rentingservice.dto;

public class MessageCommentDTO {
    private long customerId;
    private long vehicleId;
    private long agentId;

    public MessageCommentDTO() {
    }

    public MessageCommentDTO(long customerId, long vehicleId, long agentId) {
        this.customerId = customerId;
        this.vehicleId = vehicleId;
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

    public long getAgentId() {
        return agentId;
    }

    public void setAgentId(long agentId) {
        this.agentId = agentId;
    }
}
