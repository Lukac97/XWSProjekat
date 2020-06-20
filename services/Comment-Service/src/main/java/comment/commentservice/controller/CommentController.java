package comment.commentservice.controller;

import comment.commentservice.domain.Comment;
import comment.commentservice.domain.CommentRequest;
import comment.commentservice.dto.PostDTO;
import comment.commentservice.dto.ReviewDTO;
import comment.commentservice.service.CommentRequestService;
import comment.commentservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.sql.Timestamp;

@RestController
public class CommentController {

    private CommentService commentService;
    private CommentRequestService commentRequestService;

    @Autowired
    public CommentController(
            CommentService commentService,
            CommentRequestService commentRequestService
    ) {
        this.commentService = commentService;
        this.commentRequestService = commentRequestService;
    }

    @GetMapping("/commentRequests")
    public ResponseEntity<?> getAllCommentRequests(){
        return ResponseEntity.ok(commentRequestService.findAll());
    }

    @GetMapping("/comments")
    public ResponseEntity<?> getAllComments(){
        return ResponseEntity.ok(commentService.findAll());
    }

    @GetMapping("/comments/vehicle/{id}")
    public ResponseEntity<?> getVehicelsComments(@PathVariable("id") long vehicleId){
        return ResponseEntity.ok(commentService.findByVehicleId(vehicleId));
    }

    @PostMapping("/review")
    public ResponseEntity<?> acceptComment(@RequestBody ReviewDTO dto){
        if((Long) dto.getReqId() == null){
            return ResponseEntity.ok("Please enter which request you want to review!");
        }
        if((Boolean) dto.isAccept() == null){
            return ResponseEntity.ok("Please enter wheter you would like to accept or decline a comment!");
        }
        if(dto.isAccept()) {
            Comment cmnt = new Comment(commentRequestService.findById(dto.getReqId()));
            cmnt.setTimeApproved(new Timestamp(System.currentTimeMillis()));
            commentService.save(cmnt);
            commentRequestService.deleteById(dto.getReqId());
            return ResponseEntity.ok("Successfully accepted selected comment!");
        }else{
            commentRequestService.deleteById(dto.getReqId());
            return ResponseEntity.ok("Successfully deleted selected comment!");
        }
    }

    @PostMapping("/commentRequest")
    public ResponseEntity<?> postCommentRequest(@RequestBody PostDTO dto){
        if(dto.getCommentText() == null){
            return ResponseEntity.ok("Please enter text of your comment!");
        }
        if((Long) dto.getCustomerId() == null){
            return ResponseEntity.ok("You are not logged in!");
        }
        if((Double) dto.getRating() == null){
            return ResponseEntity.ok("Please enter your rating!");
        }
        if((Long) dto.getVehicleId() == null){
            return ResponseEntity.ok("Please enter for which vehicle you would like to post a comment!");
        }
        CommentRequest req = new CommentRequest();
        req.setCustomerId(dto.getCustomerId());
        req.setVehicleId(dto.getVehicleId());
        req.setCommentText(dto.getCommentText());
        req.setRating(dto.getRating());
        req.setTimePosted(new Timestamp(System.currentTimeMillis()));

        commentRequestService.save(req);
        return ResponseEntity.ok("Successfully posted comment request!");
    }
}
