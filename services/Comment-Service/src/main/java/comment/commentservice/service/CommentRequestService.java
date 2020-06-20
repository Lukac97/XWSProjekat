package comment.commentservice.service;

import comment.commentservice.domain.CommentRequest;
import comment.commentservice.repository.CommentRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentRequestService {

    private CommentRequestRepository commentRequestRepository;

    public CommentRequestService(
            CommentRequestRepository commentRequestRepository
    ) {
        this.commentRequestRepository = commentRequestRepository;
    }

    public CommentRequest save(CommentRequest commentRequest) {
        return this.commentRequestRepository.save(commentRequest);
    }

    public CommentRequest findById(long id){
        return this.commentRequestRepository.findById(id);
    }

    public List<CommentRequest> findAll(){
        return this.commentRequestRepository.findAll();
    }

    public void deleteById(long id){
        this.commentRequestRepository.deleteById(id);
    }

}
