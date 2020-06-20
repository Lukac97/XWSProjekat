package comment.commentservice.repository;

import comment.commentservice.domain.Comment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    Comment findById(long id);
    List<Comment> findAll();
    List<Comment> findByCustomerId(long customerId);
    List<Comment> findByVehicleId(long vehicleId);

    Comment save(Comment comment);

    @Modifying
    void deleteById(long id);

}
