package mergefairy.gikhub.web.controller;

import lombok.AllArgsConstructor;
import mergefairy.gikhub.domain.Comment;
import mergefairy.gikhub.service.CommentServiceImpl;
import mergefairy.gikhub.service.Dto.CommentCreateRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/comment/")
@AllArgsConstructor
public class CommentController {
    private final CommentServiceImpl commentService;

    //댓글 생성
    @PostMapping("/add")
    public ResponseEntity<Comment> createComment(@Validated CommentCreateRequestDto commentCreateDto){
        Comment createComment =  commentService.save(commentCreateDto.toEntity());
        return new ResponseEntity(createComment, HttpStatus.CREATED);
    }

    //댓글 조회
    @GetMapping("{talktalk}")
    public ResponseEntity<List<Comment>> getComments(@PathVariable(name = "talktalk") Long talkTalkNo){
        List<Comment> commentList = commentService.getComments(talkTalkNo);
        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }


    //댓글 삭제
    @DeleteMapping("/delete/{comment}")
    public void deleteComment(@PathVariable(name = "comment") Long commentId){
        commentService.deleteComment(commentId);
    }

    //댓글 수정
    @PatchMapping("/update/{comment}")
    public ResponseEntity updateComment(@PathVariable(name = "comment") Long commentId, String content){
        Comment updateComment = commentService.updateComment(commentId, content);
        return new ResponseEntity(updateComment, HttpStatus.OK);
    }

    //대댓글 생성
    @PostMapping("/add/reComment")
    public ResponseEntity createReComment(@Validated CommentCreateRequestDto commentCreateDto){
        Comment createReComment = commentService.saveReComment(commentCreateDto);
        return new ResponseEntity(createReComment, HttpStatus.CREATED);
    }
}
