package comment.commentservice.dto;

public class PostDTO {
    private long customerId;
    private long vehicleId;
    private String commentText;
    private double rating;

    public PostDTO() {
    }

    public PostDTO(long customerId, long vehicleId, String commentText, double rating) {
        this.customerId = customerId;
        this.vehicleId = vehicleId;
        this.commentText = commentText;
        this.rating = rating;
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

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
