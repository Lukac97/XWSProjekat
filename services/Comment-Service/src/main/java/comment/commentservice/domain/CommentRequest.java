package comment.commentservice.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class CommentRequest implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id", nullable = false, unique = true)
    private long id;

    @Column(nullable = false)
    private long customerId;

    @Column(nullable = false)
    private long vehicleId;

    @Column
    private String commentText;

    @Column(nullable = false)
    private Timestamp timePosted;

    @Column(nullable = false)
    private double rating;

    public CommentRequest() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Timestamp getTimePosted() {
        return timePosted;
    }

    public void setTimePosted(Timestamp timePosted) {
        this.timePosted = timePosted;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }
}
