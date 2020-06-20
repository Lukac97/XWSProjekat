package comment.commentservice.service;

import comment.commentservice.domain.Comment;
import comment.commentservice.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    public CommentService(
            CommentRepository commentRepository
    ) {
        this.commentRepository = commentRepository;
    }

    public Comment save(Comment comment) {
        return this.commentRepository.save(comment);
    }

    public Comment findById(long id){
        return this.commentRepository.findById(id);
    }

    public List<Comment> findByVehicleId(long vehicleId){
        return this.commentRepository.findByVehicleId(vehicleId);
    }

    public List<Comment> findByCustomerId(long customerId){
        return this.commentRepository.findByCustomerId(customerId);
    }

    public List<Comment> findAll(){
        return this.commentRepository.findAll();
    }

    public void deleteById(long id){
        this.commentRepository.deleteById(id);
    }

}
