package messaging.messagingservice.service;

import org.springframework.stereotype.Service;
import messaging.messagingservice.domain.Message;
import messaging.messagingservice.repository.MessageRepository;

import java.util.List;

@Service
public class MessageService {

    private MessageRepository messageRepository;

    public MessageService(
            MessageRepository messageRepository
    ) {
        this.messageRepository = messageRepository;
    }

    public Message save(Message message) {
        return this.messageRepository.save(message);
    }

    public Message findById(long id){
        return this.messageRepository.findById(id);
    }

    public Message findBySenderId(long senderId){
        return this.messageRepository.findBySenderId(senderId);
    }

    public Message findByReceiverId(long receiverId){
        return this.messageRepository.findByReceiverId(receiverId);
    }

    public List<Message> findAll(){
        return this.messageRepository.findAll();
    }

    public void deleteById(long id){
        this.messageRepository.deleteById(id);
    }

}
