package comment.commentservice.dto;

public class ReviewDTO {
    private long reqId;
    private boolean accept;

    public ReviewDTO() {
    }

    public ReviewDTO(long reqId, boolean accept) {
        this.reqId = reqId;
        this.accept = accept;
    }

    public long getReqId() {
        return reqId;
    }

    public void setReqId(long reqId) {
        this.reqId = reqId;
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }
}
