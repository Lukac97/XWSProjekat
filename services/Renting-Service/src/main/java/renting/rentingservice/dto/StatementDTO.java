package renting.rentingservice.dto;

public class StatementDTO {
    private long renthistId;
    private double travelledKm;
    private String statement;

    public StatementDTO() {
    }

    public StatementDTO(long renthistId, double travelledKm, String statement) {
        this.renthistId = renthistId;
        this.travelledKm = travelledKm;
        this.statement = statement;
    }

    public long getRenthistId() {
        return renthistId;
    }

    public void setRenthistId(long renthistId) {
        this.renthistId = renthistId;
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
