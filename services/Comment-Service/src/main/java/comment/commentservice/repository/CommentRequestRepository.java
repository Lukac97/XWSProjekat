package comment.commentservice.repository;

import comment.commentservice.domain.CommentRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRequestRepository extends CrudRepository<CommentRequest, Long> {
    CommentRequest findById(long id);
    List<CommentRequest> findAll();

    CommentRequest save(CommentRequest commentRequest);

    @Modifying
    void deleteById(long id);

}
