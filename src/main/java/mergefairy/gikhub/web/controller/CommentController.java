package mergefairy.gikhub.web.controller;

import lombok.AllArgsConstructor;
import mergefairy.gikhub.domain.Comment;
import mergefairy.gikhub.repository.CommentRepository;
import mergefairy.gikhub.service.Dto.CommentCreateDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/comment/")
@AllArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;

    //댓글 생성
    @PostMapping("/add")
    public ResponseEntity<Comment> createComment(@Validated CommentCreateDto commentCreateDto){
        Comment createComment = commentRepository.save(commentCreateDto.toEntity());
        return new ResponseEntity(createComment, HttpStatus.CREATED);
    }

    //댓글 조회
    @GetMapping("{talktalk}")
    public ResponseEntity<List<Comment>> getComments(@PathVariable(name = "talktalk") Long talkTalkNo){
        List<Comment> commentList = commentRepository.findAllByTalkTalk(talkTalkNo);
        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }


    //댓글 삭제
    @DeleteMapping("/delete/{comment}}")
    public void deleteComment(@PathVariable(name = "comment") Long talkTalkNo){
        commentRepository.deleteById(talkTalkNo);
    }

    //댓글 수정
    @PatchMapping("/update/{comment}")
    public ResponseEntity updateComment(@PathVariable(name = "comment") Long commentId, String content){
        Comment updateComment = commentRepository.findByTalkTalkNo(commentId);
        updateComment.updateComment(content);
        return new ResponseEntity(updateComment, HttpStatus.OK);
    }
}
