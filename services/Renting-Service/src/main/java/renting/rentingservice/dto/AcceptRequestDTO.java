package renting.rentingservice.dto;

import java.util.List;

public class AcceptRequestDTO {
    private long agentId;
    private long rentreqId;

    public AcceptRequestDTO() {
    }

    public AcceptRequestDTO(long agentId, long rentreqId) {
        this.agentId = agentId;
        this.rentreqId = rentreqId;
    }

    public long getAgentId() {
        return agentId;
    }

    public void setAgentId(long agentId) {
        this.agentId = agentId;
    }

    public long getRentreqId() {
        return rentreqId;
    }

    public void setRentreqId(long rentreqId) {
        this.rentreqId = rentreqId;
    }
}
