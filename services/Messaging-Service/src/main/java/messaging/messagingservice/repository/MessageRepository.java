package messaging.messagingservice.repository;

import messaging.messagingservice.domain.Message;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

    Message findById(long id);
    Message findBySenderId(long senderId);
    Message findByReceiverId(long receiverId);
    List<Message> findAll();

    Message save(Message message);

    @Modifying
    void deleteById(long id);

}
