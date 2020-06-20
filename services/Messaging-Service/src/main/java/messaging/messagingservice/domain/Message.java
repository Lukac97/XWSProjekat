package messaging.messagingservice.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Message implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id", nullable = false, unique = true)
    private long id;

    @Column(nullable = false, unique = true)
    private long senderId;

    @Column(nullable = false)
    private long receiverId;

    @Column
    private String msgContent;

    @Column
    private Timestamp timeSent;

    public Message() {
    }

    public Message(long id, long senderId, long receiverId, String msgContent, Timestamp timeSent) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.msgContent = msgContent;
        this.timeSent = timeSent;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(long receiverId) {
        this.receiverId = receiverId;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public Timestamp getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(Timestamp timeSent) {
        this.timeSent = timeSent;
    }
}
